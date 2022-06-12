package com.dicoding.budayai.location

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.dicoding.budayai.R
import com.dicoding.budayai.databinding.FragmentLocationBinding
import com.dicoding.budayai.viewModel.FactoryModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class LocationFragment : Fragment() {

    private lateinit var binding: FragmentLocationBinding
    private lateinit var factoryModel: FactoryModel
    private val locationModel: LocationModel by activityViewModels{ factoryModel }

    private fun fetch(){
        locationModel.getDataLocation()
    }

    private fun getLocation(){
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationModel.setLocationPermission(true)
        } else {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            isPerm : Boolean -> if (isPerm){
                getLocation()
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        factoryModel = FactoryModel.getInstance(requireActivity())

        getLocation()

        val mapFragment = childFragmentManager.findFragmentById(R.id.maps_location) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        fetch()

        binding.toolbarMaps.inflateMenu(R.menu.map_option)
        binding.toolbarMaps.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.map_style_option -> {
                    view.findNavController().navigate(R.id.action_locationFragment2_to_styleMapFragment)
                }
            }
            true
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        OnMapReadyCallback {
            it.clear()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setStyleMap(mMap: GoogleMap, styleMap: StyleMap){
        try {
            when(styleMap){
                StyleMap.NIGHT -> mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireActivity(), R.raw.style_night))
                StyleMap.NORMAL -> mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireActivity(), R.raw.style_normal))
            }
        } catch (e: Resources.NotFoundException){
            Log.e("MapsStoryFragment", "Error find style")
        }
    }

    private fun setTypeMap(mMap: GoogleMap, typeMap: TypeMap){
        when(typeMap){
            TypeMap.SATELLITE -> {
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }
            TypeMap.NORMAL -> {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            }
        }
    }

    private val callback = OnMapReadyCallback{googleMap ->
        googleMap.setPadding(0, 160, 0, 160)
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.uiSettings.isMapToolbarEnabled = true
        googleMap.uiSettings.isCompassEnabled = true
        googleMap.uiSettings.isIndoorLevelPickerEnabled = true

        googleMap.setOnMarkerClickListener { m ->
            val dataModel = locationModel.data.value?.find {
                it.id == m.tag
            }
            dataModel?.let {
                val latLog = LatLng(it.lat, it.lon)
                val toDetail = LocationFragmentDirections.actionLocationFragment2ToDetailLocationFragment(
                    it.type,
                    it.detail,
                    it.photoUrl
                )
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLog, 10f))
                findNavController().navigate(toDetail)
            }
            true
        }

        locationModel.getStyleMap().observe(viewLifecycleOwner){
            when(it){
                StyleMap.NIGHT -> setStyleMap(googleMap, StyleMap.NIGHT)
                StyleMap.NORMAL -> setStyleMap(googleMap, StyleMap.NORMAL)
                else -> setStyleMap(googleMap, StyleMap.NORMAL)
            }
        }

        locationModel.getTypeMap().observe(viewLifecycleOwner){
            when(it){
                TypeMap.SATELLITE -> setTypeMap(googleMap, TypeMap.SATELLITE)
                TypeMap.NORMAL -> setTypeMap(googleMap, TypeMap.NORMAL)
                else -> setTypeMap(googleMap, TypeMap.NORMAL)
            }
        }

        locationModel.data.observe(viewLifecycleOwner){
            it?.let {
                for (data in it){
                    val lat: Double = data.lat
                    val lon: Double = data.lon
                    val latLng = LatLng(lat, lon)
                    googleMap.addMarker(MarkerOptions().position(latLng).title(data.type))?.tag = data.id
                }

                val latLng = LatLng(it[0].lat, it[0].lon)
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5f))
            }
        }

        locationModel.locationPermission.observe(viewLifecycleOwner){
            googleMap.isMyLocationEnabled
        }
    }
}