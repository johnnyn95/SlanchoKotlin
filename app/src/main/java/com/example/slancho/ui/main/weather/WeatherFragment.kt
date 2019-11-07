package com.example.slancho.ui.main.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.slancho.R
import com.example.slancho.api.ForecastType
import com.example.slancho.common.weatherForecastModels.CurrentWeatherForecast
import com.example.slancho.common.weatherForecastModels.WeatherForecast
import com.example.slancho.databinding.FragmentWeatherBinding
import com.example.slancho.db.model.Forecast
import com.example.slancho.db.model.User
import com.example.slancho.di.GlideApp
import com.example.slancho.ui.main.BaseMainFragment
import com.example.slancho.ui.main.weather.adapterDelegates.CurrentWeatherForecastAdapterDelegate
import com.example.slancho.utils.SharedPreferencesManager
import com.example.slancho.utils.WeatherFormatUtils
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import timber.log.Timber
import javax.inject.Inject

class WeatherFragment : BaseMainFragment(), (CurrentWeatherForecast) -> Unit {
    override fun invoke(p1: CurrentWeatherForecast) {
        Timber.d("Current weather forecast clicked!")
    }

    lateinit var binding: FragmentWeatherBinding
    lateinit var viewModel: WeatherFragmentViewModel
    @Inject lateinit var sharedPreferencesManager: SharedPreferencesManager

    companion object {
        fun newInstance(user: User): WeatherFragment {
            val weatherFragment = WeatherFragment()
            weatherFragment.currentUser = user
            return weatherFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_weather, container, false
        )
        initFields()
        initViews()
        initListeners()
        initDataListeners()
        viewModel.onScreenReady(currentUser)
        return binding.root
    }

    override fun initFields() {
        viewModel = getViewModel(WeatherFragmentViewModel::class.java)
    }

    override fun initViews() {}

    override fun initListeners() {
        binding.grpRadioFilters.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.btn_filter_current -> viewModel.onFilterButtonClicked(ForecastType.Current)
                R.id.btn_filter_daily -> viewModel.onFilterButtonClicked(ForecastType.Daily)
                R.id.btn_filter_three_hour -> viewModel.onFilterButtonClicked(ForecastType.ThreeHour)
            }
        }
    }

    private fun initDataListeners() {
        viewModel.currentForecast.observe(this, Observer {
            initWeatherBanner(it)
            val weatherFormatUtils = WeatherFormatUtils(sharedPreferencesManager)
            val adapter = ListDelegationAdapter<List<WeatherForecast>>(
                CurrentWeatherForecastAdapterDelegate().init(weatherFormatUtils, this)
            )
            val listItems = ArrayList<CurrentWeatherForecast>()
            it.forecastInfo.listIterator().forEach { forecastInfo ->
                listItems.add(CurrentWeatherForecast.createFromForecastInfo(forecastInfo))
            }
            adapter.items = listItems
            binding.rvForecast.adapter = adapter
            binding.rvForecast.layoutManager = LinearLayoutManager(context)
            Timber.d("Fetched currentForecast")
        })
    }

    private fun initWeatherBanner(forecast: Forecast) {
        binding.txtBannerTitle.text = forecast.cityName
        val imageUrl = forecast.city!!.cityImageUrl
        if (imageUrl!!.isNotEmpty()) {
            GlideApp.with(this)
                .load(imageUrl)
                .transform(
                    CenterCrop(),
                    RoundedCorners(resources.getInteger(R.integer.glide_rounded_corners_radius))
                )
                .error(resources.getDrawable(R.drawable.ic_wallpaper_placeholder, context!!.theme))
                .into(binding.ivBannerImage)
        }
    }
}


