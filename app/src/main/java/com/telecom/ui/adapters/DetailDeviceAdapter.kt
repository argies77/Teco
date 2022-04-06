package com.telecom.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.telecom.databinding.ItemDeviceBinding
import com.telecom.model.Images

class DetailDeviceAdapter(
    private var listDevice: List<Images>,
    private val context: Context
) :
    RecyclerView.Adapter<DetailDeviceAdapter.ViewHolder>() {

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.create(parent)

    override fun getItemCount(): Int {
        return listDevice.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listDevice[position], context)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemDeviceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(images: Images, context: Context) {
            val isVisible = binding.layoutDetail.isVisible
            binding.layoutDetail.isVisible = !isVisible

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

}