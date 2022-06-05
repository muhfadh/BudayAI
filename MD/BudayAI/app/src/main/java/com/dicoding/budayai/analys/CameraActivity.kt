package com.dicoding.budayai.analys

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.dicoding.budayai.MainActivity
import com.dicoding.budayai.databinding.ActivityCameraBinding
import com.dicoding.budayai.util.uriToFile
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding
    private var capture: ImageCapture? = null
    private var getImage: File? = null
    private lateinit var camera: ExecutorService
    private var selector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tookImg.setOnClickListener {
            take()
        }

        camera = Executors.newSingleThreadExecutor()
        binding.switchCam.setOnClickListener {
            selector = if (selector == CameraSelector.DEFAULT_BACK_CAMERA) CameraSelector.DEFAULT_FRONT_CAMERA else CameraSelector.DEFAULT_BACK_CAMERA
            val cameraProv = ProcessCameraProvider.getInstance(this)
            cameraProv.addListener({
                val camProvider: ProcessCameraProvider = cameraProv.get()
                val previewPhoto = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(binding.vCamera.surfaceProvider)
                    }
                capture = ImageCapture.Builder().build()
                try {
                    camProvider.unbindAll()
                    camProvider.bindToLifecycle(
                        this, selector, previewPhoto, capture
                    )
                } catch (e: Exception){
                    Toast.makeText(this, "Tidak Berhasil Mengambil Gambar", Toast.LENGTH_SHORT).show()
                }
            }, ContextCompat.getMainExecutor(this))
        }

        binding.tookGallery.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            val pilih = Intent.createChooser(intent, "Choose a Picture")
            gallery.launch(pilih)
        }
    }

    private val gallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if (result.resultCode == RESULT_OK){
            val img: Uri = result.data?.data as Uri
            val myFile = uriToFile(img, this)
            getImage = myFile
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        camera.shutdown()
    }

    private fun take(){

    }
}