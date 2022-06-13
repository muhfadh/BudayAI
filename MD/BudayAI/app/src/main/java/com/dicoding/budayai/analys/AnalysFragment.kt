package com.dicoding.budayai.analys

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.budayai.R
import com.dicoding.budayai.api.adapter.AnalysAdapter
import com.dicoding.budayai.databinding.FragmentAnalysBinding
import com.dicoding.budayai.util.reduceFileImage
import com.dicoding.budayai.util.rotateBitmap
import com.dicoding.budayai.util.uriToFile
import com.dicoding.budayai.viewModel.DetectModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class AnalysFragment : Fragment() {

    private lateinit var binding: FragmentAnalysBinding
    private lateinit var analysAdapter: AnalysAdapter
    private lateinit var result: Bitmap
    private var getImage: File? = null
    private lateinit var detectModel: DetectModel
    private val analysModel: AnalysModel by activityViewModels { detectModel }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnalysBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun addDetect(select_model: String){
        if (getImage != null){
            val reqDescription = select_model.toRequestBody("text/plain".toMediaType())
            val file = reduceFileImage(getImage as File)
            val reqImage = file.asRequestBody("image/*".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData("file", file.name, reqImage)
            analysModel.addDetect(imageMultipart, reqDescription)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analysAdapter = AnalysAdapter()
        detectModel = DetectModel.getInstance(requireActivity())

        binding.btnUpload.setOnClickListener {
            binding.progressBar2.visibility = View.VISIBLE
            val select_model = binding.tvSelectModel.text.toString()
            if (select_model.isNotEmpty()){
                addDetect(select_model)
            } else {
                Toast.makeText(activity, R.string.required, Toast.LENGTH_SHORT).show()
            }

            binding.rvResult.layoutManager = LinearLayoutManager(activity)

            analysModel.analys.observe(viewLifecycleOwner){
                if (it.error == false){
                    binding.progressBar2.visibility = View.INVISIBLE
                    analysAdapter.setAnalys(listOf(it))
                    Toast.makeText(activity, R.string.detect_success, Toast.LENGTH_SHORT).show()
                } else {
                    binding.progressBar2.visibility = View.INVISIBLE
                    Toast.makeText(activity, R.string.detect_failed, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(context, AnalysFragment::class.java))
                }
            }

            binding.rvResult.adapter = analysAdapter
        }

        binding.btnTookGalery.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            val pilih = Intent.createChooser(intent, R.string.choose_image.toString())
            gallery.launch(pilih)
        }

        binding.btnTookCamera.setOnClickListener {
            val intent = Intent(activity, CameraActivity::class.java)
            camera.launch(intent)
        }
    }

    private val camera = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == CAMERA){
            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBack = it.data?.getBooleanExtra("isBackCamera", true) as Boolean
            getImage = myFile
            result = rotateBitmap(BitmapFactory.decodeFile(myFile.path), isBack)
            binding.btnUpload.isEnabled = true
            binding.vImage.setImageBitmap(result)
        }
    }

    private val gallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK){
            val img: Uri = result.data?.data as Uri
            val myFile = uriToFile(img, requireActivity())
            getImage = myFile
            binding.btnUpload.isEnabled = true
            binding.vImage.setImageURI(img)
        }
    }

    companion object{
        const val CAMERA = 200
    }
}