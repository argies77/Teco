package com.telecom.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.ImageListener
import com.telecom.R
import com.telecom.databinding.ItemDeviceBinding
import com.telecom.model.Device
import com.telecom.util.load


class SearchDeviceAdapter(
    private val listener: Listener,
    private var listDevice: List<Device>,
    private val context: Context
) :
    RecyclerView.Adapter<SearchDeviceAdapter.ViewHolder>(), Filterable {

    var deviceFilterList = ArrayList<Device>()
    var characterFilterListOriginal = ArrayList<Device>()

    init {
        deviceFilterList = listDevice as ArrayList<Device>
        characterFilterListOriginal = listDevice as ArrayList<Device>
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    deviceFilterList = listDevice as ArrayList<Device>
                } else {
                    val resultList = ArrayList<Device>()
                    for (row in characterFilterListOriginal) {
                        row.name.let {
                            if (it.contains(charSearch, ignoreCase = true)) {
                                resultList.add(row)
                            }
                        }
                    }
                    listDevice = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = listDevice
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                deviceFilterList = results?.values as ArrayList<Device>
                notifyDataSetChanged()
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.create(parent)

    override fun getItemCount(): Int {
        return listDevice.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(listDevice[position], listener, context)

    class ViewHolder(private val binding: ItemDeviceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(device: Device, listener: Listener, context: Context) {
            binding.deviceImage.load(device.mainImage.url)
            binding.name.text = device.name
            binding.installmentsTag.text = device.installmentsTag
            binding.topTag.text = device.topTag
            binding.legal.text = context.getString(R.string.legal) + device.legal
            val imagesDevice :ArrayList<String> =ArrayList<String>()
            for ((index, value) in device.images.withIndex()) {
                imagesDevice.add(device.images[index].thumbnailUrl)
            }
            val imageListener: ImageListener =
                ImageListener { position, imageView ->
                    Picasso.get().load(imagesDevice[position]).into(imageView)
                }
            binding.carousel.pageCount = imagesDevice.size
            binding.carousel.setImageListener(imageListener)

            binding.layoutDevice.setOnClickListener {
                val isVisible = binding.layoutDetail.isVisible
                binding.layoutDetail.isVisible = !isVisible
                listener.onClick(device, absoluteAdapterPosition)
            }

        }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val binding = ItemDeviceBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewHolder(binding)
            }
        }
    }

    interface Listener {
        fun onClick(device: Device, position: Int)
    }
}