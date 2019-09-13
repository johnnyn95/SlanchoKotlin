package com.example.slancho.ui.main.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.slancho.R
import com.example.slancho.common.BaseFragment
import com.example.slancho.databinding.FragmentMainBinding

class WeatherFragment : BaseFragment() {
    override val fragmentTag: String get() = WeatherFragment::class.toString()

    lateinit var binding: FragmentMainBinding

    companion object {
        fun newInstance(): WeatherFragment {
            return WeatherFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        initFields()
        initViews()
        initListeners()
        return binding.root
    }

    override fun initFields() {}

    override fun initViews() {}

    override fun initListeners() {}
}


