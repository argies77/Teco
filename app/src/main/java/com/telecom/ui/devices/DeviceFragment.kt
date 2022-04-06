package com.telecom.ui.devices

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.telecom.R
import com.telecom.databinding.DeviceFragmentBinding
import com.telecom.model.Device
import com.telecom.model.Status
import com.telecom.ui.adapters.DetailDeviceAdapter
import com.telecom.ui.adapters.SearchDeviceAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeviceFragment : Fragment(), SearchDeviceAdapter.Listener {
    private val viewModel by viewModel<DeviceViewModel>()
    private var adapterDevice: SearchDeviceAdapter? = null
    private var adapterDetailsDevice: DetailDeviceAdapter? = null
    private lateinit var binding: DeviceFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DeviceFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerDevices.layoutManager = LinearLayoutManager(context)
        binding.recyclerDevices.hasFixedSize()

        setAdapterDevices()

        binding.device.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapterDevice!!.filter.filter(s)
                binding.recyclerDevices.isVisible = true
            }
        })
    }

    private fun setAdapterDevices() {
        viewModel.getDeviceList().observe(
            viewLifecycleOwner
        ) { resource ->
            when (resource.status) {
                Status.LOADING -> binding.loading.progress.visibility = View.VISIBLE
                Status.ERROR -> {
                    binding.loading.progress.visibility = View.GONE
                    resource.message?.let {
                        Toast.makeText(
                            context,
                            R.string.error_server,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                Status.SUCCESS -> {
                    binding.loading.progress.visibility = View.GONE
                    resource.data?.let { deviceList ->
                    adapterDevice = SearchDeviceAdapter(this,deviceList,requireContext())
                    binding.recyclerDevices.adapter = adapterDevice
                    }
                }
            }
        }
    }

    override fun onClick(device: Device, position: Int) {
        getDetailDevice(position+1)
    }

    private fun getDetailDevice(position:Int) {
        viewModel.getDeviceDetail(position.toString()).observe(
            viewLifecycleOwner
        ) { resource ->
            when (resource.status) {
                Status.LOADING -> binding.loading.progress.visibility = View.VISIBLE
                Status.ERROR -> {
                    binding.loading.progress.visibility = View.GONE
                    resource.message?.let {
                        Toast.makeText(
                            context,
                            R.string.error_server,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                Status.SUCCESS -> {
                    binding.loading.progress.visibility = View.GONE
                    resource.data?.let { deviceDetail ->
                    }
                }
            }
        }
    }
}