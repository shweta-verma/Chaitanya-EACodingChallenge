package com.test.energyaustralia.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.energyaustralia.data.model.BandsWithFestivalName
import com.test.energyaustralia.databinding.FestivalsListItemBinding

class FestivalsAdapter(
    private val bandsList: List<BandsWithFestivalName>,
) : RecyclerView.Adapter<FestivalsAdapter.FestivalsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FestivalsViewHolder {
        val binding = FestivalsListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FestivalsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FestivalsViewHolder, position: Int) {
        holder.bind(bandsList.get(position))
    }

    override fun getItemCount(): Int {
        return bandsList.size
    }

    inner class FestivalsViewHolder(
        val binding: FestivalsListItemBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bands: BandsWithFestivalName) {
            Log.i("MYTAG", "band name ${bands.name}")
            binding.tvFestivalName.text = bands.festivalName
            binding.tvBandName.text = bands.name
            binding.tvRecordLabel.text = bands.recordLabel
        }
    }
}









