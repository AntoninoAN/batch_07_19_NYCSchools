package com.example.nycschools.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschools.databinding.SchoolItemLayoutBinding
import com.example.nycschools.model.local.SchoolSatEntity

class NYCAdapter(private val dataset: List<SchoolSatEntity>,
                 private val openDetails: (SchoolSatEntity) -> Unit):
    RecyclerView.Adapter<NYCAdapter.NYCViewHolder>() {

    class NYCViewHolder(private val binding: SchoolItemLayoutBinding): RecyclerView.ViewHolder(binding.root){

        fun onBind(dataItem: SchoolSatEntity, openDetails: (SchoolSatEntity) -> Unit){
            binding.tvSchoolName.text = dataItem.school_name
            binding.tvSchoolCity.text = dataItem.city
            binding.tvSchoolLocation.text = dataItem.location
            binding.tvSchoolFaxNumber.text = dataItem.fax_number
            binding.tvSchoolPhoneNumber.text = dataItem.phone_number

            binding.root.setOnClickListener { openDetails(dataItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NYCViewHolder(
            SchoolItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    override fun onBindViewHolder(holder: NYCViewHolder, position: Int) {
        holder.onBind(dataset[position], openDetails)
    }

    override fun getItemCount() = dataset.size

}