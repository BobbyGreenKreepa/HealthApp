package com.example.healthapp.jogs.ui.jogConstrcutor

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.healthapp.R
import com.example.healthapp.core.foundation.map.fromDrawable
import com.example.healthapp.databinding.FragmentJogBinding
import com.google.android.gms.location.LocationServices
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polyline
import com.yandex.mapkit.map.*
import com.yandex.mapkit.map.Map
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class JogFragment : Fragment(), CameraListener {

    private val viewModel: JogViewModel by viewModels()

    private var _binding: FragmentJogBinding? = null
    private val binding get() = _binding!!

    private val mapView get() = binding.mapView
    private val mapKit = MapKitFactory.getInstance()

    private var previousPolyline: PolylineMapObject? = null
    private var previousPolylinePhantom: PolylineMapObject? = null
    private var userLocation: Point? = null

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
                    userLocation = Point(it.latitude, it.longitude)
                    createUserView(userLocation!!)
                }
            }
        }

        mapView.map.addCameraListener(this)

        registerForActivityLauncher.launch(locationPermission)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeViewState()
        initButtons()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeViewState() {
        viewModel.polyline.onEach {
            drawPolylinePath(it)
        }.launchIn(lifecycleScope)

        viewModel.distance.onEach {
            binding.rangeCounter.text = it
        }.launchIn(lifecycleScope)
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
    }

    private fun removePhantomPolyline() {
        if (previousPolylinePhantom != null) mapView.map.mapObjects.remove(previousPolylinePhantom!!)
        previousPolylinePhantom = null
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
        if (viewModel.lastAddedPoint != null) {
            removePhantomPolyline()
            previousPolylinePhantom = mapView.map.mapObjects.addPolyline(Polyline(listOf(viewModel.lastAddedPoint, p1.target)))
        }
    }
}