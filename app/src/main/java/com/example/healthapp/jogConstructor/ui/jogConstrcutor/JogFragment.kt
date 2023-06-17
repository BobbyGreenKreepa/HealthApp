package com.example.healthapp.jogConstructor.ui.jogConstrcutor

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.healthapp.R
import com.example.healthapp.core.foundation.coroutines.launchOnResumed
import com.example.healthapp.core.foundation.map.fromDrawable
import com.example.healthapp.core.foundation.textViewUtils.emptyValidate
import com.example.healthapp.core.foundation.textViewUtils.onTextChange
import com.example.healthapp.core.foundation.viewUtils.collapse
import com.example.healthapp.core.foundation.viewUtils.expand
import com.example.healthapp.core.ui.fragementUtils.showToast
import com.example.healthapp.databinding.FragmentJogBinding
import com.example.healthapp.jogConstructor.ui.entities.UiDataPoint
import com.google.android.gms.location.LocationServices
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polyline
import com.yandex.mapkit.map.*
import com.yandex.mapkit.map.Map
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class JogFragment : Fragment(), CameraListener, MapObjectTapListener {

    private val viewModel: JogViewModel by viewModels()

    private var _binding: FragmentJogBinding? = null
    private val binding get() = _binding!!

    private val mapView get() = binding.mapView
    private val mapKit = MapKitFactory.getInstance()

    private var previousPolyline: PolylineMapObject? = null
    private var previousPolylinePhantom: PolylineMapObject? = null
    private var previousBreakPoints: List<PlacemarkMapObject> = emptyList()
    private var userLocation: Point? = null

    private val animDuration: Long = 200

    private val locationPermission = "android.permission.ACCESS_FINE_LOCATION"

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJogBinding.inflate(inflater, container, false)

        val registerForActivityLauncher = registerForActivityResult(RequestPermission()) { isGranted ->
            if (isGranted) {
                val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
                fusedLocationClient.lastLocation.addOnSuccessListener {
                    if (it == null) {
                        return@addOnSuccessListener
                    }

                    userLocation = Point(it.latitude, it.longitude)
                    createUserView(userLocation!!)
                }
            }
        }

        mapView.map.addCameraListener(this)
        mapView.map.mapObjects.addTapListener(this)

        registerForActivityLauncher.launch(locationPermission)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeViewState()
        initButtons()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeViewState() {
        viewModel.distance.onEach {
            binding.rangeCounter.text = it
        }.launchOnResumed(viewLifecycleOwner)

        viewModel.polyline.onEach {
            drawPolylinePath(it)
        }.launchOnResumed(viewLifecycleOwner)

        viewModel.phantomPolyline.onEach {
            drawPolylinePhantom(it)
        }.launchOnResumed(viewLifecycleOwner)

        viewModel.polylinePoints.onEach {
            drawPolylineBreakPoints(it)
        }.launchOnResumed(viewLifecycleOwner)

        viewModel.collapseInfoAlarm.onEach {
            if (binding.dataPointInfo.isVisible) {
                collapse(binding.dataPointInfo, animDuration)
            }
        }.launchOnResumed(viewLifecycleOwner)

        binding.jogNameLayout.onTextChange { viewModel.jogName.value = it }
    }

    private fun initButtons() {
        binding.apply {
            addPoint.setOnClickListener {
                viewModel.addPoint(mapView.map.cameraPosition.target)
            }

            popPoint.setOnClickListener {
                removePhantomPolyline()
                viewModel.popPoint()
            }

            save.setOnClickListener {
                if (viewModel.validate()) {
                    viewModel.save()
                    findNavController().popBackStack()
                } else {
                    jogNameLayout.emptyValidate()

                    if (viewModel.polylinePoints.value.size < 2) {
                        showToast(getString(R.string.empty_jog_warning))
                    }
                }
            }
        }
    }

    private fun createUserView(userLocation: Point) {
        val userIc = fromDrawable(R.drawable.user_ic, resources)
        val animation = Animation(Animation.Type.SMOOTH, 1f)
        mapView.map.mapObjects.addPlacemark(userLocation, userIc)
        mapView.map.move(CameraPosition(userLocation, 13.5f, 0f, 0f), animation, null)
    }

    private fun drawPolylinePath(polyline: Polyline) {
        if (previousPolyline != null) mapView.map.mapObjects.remove(previousPolyline!!)

        previousPolyline = mapView.map.mapObjects.addPolyline(polyline)
        previousPolyline!!.setStrokeColor(ResourcesCompat.getColor(resources, R.color.health_green, null))
    }

    private fun drawPolylinePhantom(polyline: Polyline) {
        val polylinePhantom = mapView.map.mapObjects.addPolyline(polyline)

        if (previousPolylinePhantom != null) mapView.map.mapObjects.remove(previousPolylinePhantom!!)
        previousPolylinePhantom = polylinePhantom
        previousPolylinePhantom!!.setStrokeColor(ResourcesCompat.getColor(resources, R.color.health_green, null))
    }

    private fun removePhantomPolyline() {
        if (previousPolylinePhantom != null) mapView.map.mapObjects.remove(previousPolylinePhantom!!)

        previousPolylinePhantom = null
    }

    private fun drawPolylineBreakPoints(dataPoints: List<UiDataPoint>) {
        val marksList: MutableList<PlacemarkMapObject> = mutableListOf()

        dataPoints.forEach {
            val mark = mapView.map.mapObjects.addPlacemark(it.point, fromDrawable(R.drawable.polyline_breakpoint_ic, resources))
            mark.userData = it.data
            marksList.add(mark)
        }

        previousBreakPoints.forEach {
            mapView.map.mapObjects.remove(it)
        }

        previousBreakPoints = marksList
    }

    override fun onStop() {
        mapView.onStop()
        mapKit.onStop()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
        mapKit.onStart()
    }

    override fun onCameraPositionChanged(
        p0: Map,
        p1: CameraPosition,
        p2: CameraUpdateReason,
        p3: Boolean
    ) {
        viewModel.updatePhantomPoint(cameraPoint = p1.target)
    }

    override fun onMapObjectTap(mapObj: MapObject, point: Point): Boolean {
        if (mapObj.userData != null) {
            viewModel.resetCollapseTimer()
            if (binding.dataPointInfo.isVisible) {
                binding.dataPointInfo.text = getString(R.string.data_point_info_text_format, mapObj.userData.toString())
            } else {
                binding.dataPointInfo.text = getString(R.string.data_point_info_text_format, mapObj.userData.toString())
                expand(binding.dataPointInfo, animDuration)
                return false
            }
        }

        return true
    }
}