package com.dicoding.budayai.location.option

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.dicoding.budayai.R
import com.dicoding.budayai.databinding.FragmentStyleMapBinding
import com.dicoding.budayai.location.LocationModel
import com.dicoding.budayai.location.StyleMap
import com.dicoding.budayai.location.TypeMap
import com.dicoding.budayai.viewModel.FactoryModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class StyleMapFragment : BottomSheetDialogFragment() {

    private lateinit var factoryModel: FactoryModel
    private val locationModel: LocationModel by activityViewModels { factoryModel }
    private lateinit var binding: FragmentStyleMapBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStyleMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun styleMapSwitch(styleMap: StyleMap){
        when(styleMap){
            StyleMap.NIGHT -> {
                binding.imgStyleNight.setPadding(1,1,1,1)
                binding.tvvStyleNight.setTextColor(change(PRIMARY_COLOR))

                binding.imgStyleNormal.setPadding(0,0,0,0)
                binding.tvvStyleNormal.setTextColor(change(SECONDARY_COLOR))
            }
            StyleMap.NORMAL -> {
                binding.imgStyleNight.setPadding(0,0,0,0)
                binding.tvvStyleNight.setTextColor(change(SECONDARY_COLOR))

                binding.imgStyleNormal.setPadding(1,1,1,1)
                binding.tvvStyleNormal.setTextColor(change(PRIMARY_COLOR))
            }
        }
    }

    private fun typeMapSwitch(typeMap: TypeMap){
        when(typeMap){
            TypeMap.SATELLITE -> {
                binding.imgTypeSatellite.setPadding(0,0,0,0)
                binding.tvSatellite.setTextColor(change(PRIMARY_COLOR))

                binding.imgTypeNormal.setPadding(0,0,0,0)
                binding.tvNormal.setTextColor(change(SECONDARY_COLOR))
            }

            TypeMap.NORMAL -> {
                binding.imgTypeSatellite.setPadding(0,0,0,0)
                binding.tvSatellite.setTextColor(change(SECONDARY_COLOR))

                binding.imgTypeNormal.setPadding(0,0,0,0)
                binding.tvNormal.setTextColor(change(PRIMARY_COLOR))
            }
        }
    }

    private fun hideStyleMap(hide: Boolean = true){
        if (hide){
            binding.groupStyle.visibility = View.GONE
        } else {
            binding.groupStyle.visibility = View.VISIBLE
        }
    }

    private fun change(name: String): Int{
        return when(name){
            PRIMARY_COLOR -> ContextCompat.getColor(requireContext(), R.color.teal_200)
            else -> ContextCompat.getColor(requireContext(), R.color.black)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        factoryModel = FactoryModel.getInstance(requireActivity())

        locationModel.getTypeMap().observe(viewLifecycleOwner){
            when(it){
                TypeMap.SATELLITE -> typeMapSwitch(TypeMap.SATELLITE)
                TypeMap.NORMAL -> {
                    typeMapSwitch(TypeMap.NORMAL)
                    hideStyleMap(false)

                }
                else -> typeMapSwitch(TypeMap.NORMAL)
            }
        }

        locationModel.getStyleMap().observe(viewLifecycleOwner){
            when(it){
                StyleMap.NIGHT -> styleMapSwitch(StyleMap.NIGHT)
                StyleMap.NORMAL -> styleMapSwitch(StyleMap.NORMAL)
                else -> styleMapSwitch(StyleMap.NORMAL)
            }
        }

        binding.typeSatellite.setOnClickListener {
            locationModel.saveTypeMap(TypeMap.SATELLITE)
            dismiss()
        }

        binding.typeNormal.setOnClickListener {
            locationModel.saveTypeMap(TypeMap.NORMAL)
            dismiss()
        }

        binding.styleNormal.setOnClickListener {
            locationModel.saveStyleMap(StyleMap.NORMAL)
            dismiss()
        }

        binding.styleNight.setOnClickListener {
            locationModel.saveStyleMap(StyleMap.NIGHT)
            dismiss()
        }

        hideStyleMap()
    }

    companion object {
        private const val SECONDARY_COLOR = "SECONDARY_COLOR"
        private const val PRIMARY_COLOR = "PRIMARY_COLOR"
    }

}