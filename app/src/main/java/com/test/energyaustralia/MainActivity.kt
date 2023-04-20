package com.test.energyaustralia

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.energyaustralia.data.model.BandsWithFestivalName
import com.test.energyaustralia.data.util.Resource
import com.test.energyaustralia.databinding.ActivityMainBinding
import com.test.energyaustralia.presentation.adapter.FestivalsAdapter
import com.test.energyaustralia.presentation.viewModel.FestivalsViewModel
import com.test.energyaustralia.presentation.viewModel.FestivalsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var festivalsAdapter: FestivalsAdapter

    @Inject
    lateinit var festivalsViewModelFactory: FestivalsViewModelFactory
    lateinit var viewModel: FestivalsViewModel
    private lateinit var binding: ActivityMainBinding
    private var isLoading = false
    var bandsWithFestivalNameList: ArrayList<BandsWithFestivalName> = arrayListOf();
    lateinit var bandWithFestival: BandsWithFestivalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, festivalsViewModelFactory)
            .get(FestivalsViewModel::class.java)

        initRecyclerView()
        viewFestivalsList()
    }

    private fun initRecyclerView() {
        binding.rvFestivals.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun viewFestivalsList() {
        viewModel.getFestivals()
        bandWithFestival = BandsWithFestivalName()
        viewModel.festivalDataList.observe(this, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    for (item in response.data!!.toList()) {
                        for (band in item.bands) {
                            bandWithFestival.festivalName = item.festivalName
                            bandWithFestival.name = band.name
                            bandWithFestival.recordLabel = band.recordLabel
                            bandsWithFestivalNameList.add(bandWithFestival)
                            bandWithFestival = BandsWithFestivalName()
                        }
                    }

                    response.data.let {
//                        Log.i("MYTAG", "came here ${it.bands.toList().size}")
                        festivalsAdapter = FestivalsAdapter(bandsWithFestivalNameList);
                        binding.rvFestivals.adapter = festivalsAdapter
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(this, "An error occurred : $it", Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }

            }
        })
    }

    private fun showProgressBar() {
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        binding.progressBar.visibility = View.INVISIBLE
    }
}