package com.example.slancho.ui.main.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.slancho.R
import com.example.slancho.api.ForecastType
import com.example.slancho.common.weatherForecastModels.*
import com.example.slancho.databinding.FragmentWeatherBinding
import com.example.slancho.db.model.Forecast
import com.example.slancho.db.model.User
import com.example.slancho.di.GlideApp
import com.example.slancho.ui.main.BaseMainFragment
import com.example.slancho.ui.main.weather.adapterDelegates.CurrentWeatherForecastAdapterDelegate
import com.example.slancho.ui.main.weather.adapterDelegates.DailyWeatherForecastAdapterDelegate
import com.example.slancho.ui.main.weather.adapterDelegates.ThreeHourWeatherForecastAdapterDelegate
import com.example.slancho.utils.SharedPreferencesManager
import com.example.slancho.utils.WeatherFormatUtils
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import timber.log.Timber
import javax.inject.Inject

class WeatherFragment : BaseMainFragment(), (AdapterCard) -> Unit {
    override fun invoke(adapterCard: AdapterCard) {
        when (adapterCard.getCardType()) {
            AdapterCardType.WeatherCard -> {
                Timber.d("Weather forecast clicked!")
                when ((adapterCard as WeatherForecast).getForecastType()) {
                    ForecastType.Current -> Timber.d("Current forecast clicked!")
                    ForecastType.Daily -> {
                        (adapterCard as DailyWeatherForecast).toggleCollapse()
                        Timber.d("Daily forecast clicked!")
                    }
                    ForecastType.ThreeHour -> Timber.d("Three hour forecast clicked!")
                }
            }
            else ->
                Timber.d("Other")
        }
        forecastAdapter.notifyDataSetChanged()
    }

    lateinit var binding: FragmentWeatherBinding
    lateinit var viewModel: WeatherFragmentViewModel
    lateinit var forecastAdapter: ListDelegationAdapter<List<AdapterCard>>
    private lateinit var weatherFormatUtils: WeatherFormatUtils

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

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
        weatherFormatUtils = WeatherFormatUtils(sharedPreferencesManager)
        forecastAdapter = ListDelegationAdapter<List<AdapterCard>>(
            CurrentWeatherForecastAdapterDelegate().init(weatherFormatUtils, this),
            DailyWeatherForecastAdapterDelegate().init(weatherFormatUtils, this),
            ThreeHourWeatherForecastAdapterDelegate().init(weatherFormatUtils, this)
        )
    }

    override fun initViews() {
        val decoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        decoration.setDrawable(context!!.getDrawable(R.drawable.weather_decoration_divider)!!)
        binding.rvForecast.addItemDecoration(decoration)
        binding.rvForecast.layoutManager = LinearLayoutManager(context)
    }

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
            when (it.forecastType) {
                ForecastType.Current.value -> {
                    forecastAdapter.items =
                        CurrentWeatherForecast.createFromForecastInfoList(it.forecastInfo)
                    Timber.d("Fetched currentForecast")
                }
                ForecastType.Daily.value -> {
                    forecastAdapter.items =
                        DailyWeatherForecast.createFromForecastInfoList(it.forecastInfo)
                    Timber.d("Fetched dailyForecast")
                }
                ForecastType.ThreeHour.value -> {
                    forecastAdapter.items =
                        ThreeHourWeatherForecast.createFromForecastInfoList(it.forecastInfo)
                    Timber.d("Fetched threeHourForecast")
                }
            }
            initForecastAdapter()
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

    private fun initForecastAdapter() {
        binding.rvForecast.adapter = forecastAdapter
    }
}


