package com.example.slancho.ui.main.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.slancho.R
import com.example.slancho.databinding.FragmentWeatherBinding
import com.example.slancho.db.model.User
import com.example.slancho.di.GlideApp
import com.example.slancho.ui.main.BaseMainFragment
import timber.log.Timber

class WeatherFragment : BaseMainFragment() {
    lateinit var binding: FragmentWeatherBinding
    lateinit var viewModel: WeatherFragmentViewModel

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
        viewModel.onScreenReady(currentUser)
        return binding.root
    }

    override fun initFields() {
        viewModel = getViewModel(WeatherFragmentViewModel::class.java)
    }

    override fun initViews() {}

    override fun initListeners() {
        viewModel.forecast.observe(this, Observer {
            binding.txtBannerTitle.text = it.cityName
            GlideApp.with(this)
                .load(it.city!!.cityImageUrl)
                .into(binding.ivBannerImage)
            Timber.d("Fetched forecast")
        })
    }
}


