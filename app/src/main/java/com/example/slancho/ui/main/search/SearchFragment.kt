package com.example.slancho.ui.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.slancho.R
import com.example.slancho.databinding.FragmentSearchBinding
import com.example.slancho.db.model.User
import com.example.slancho.ui.main.BaseMainFragment

class SearchFragment : BaseMainFragment() {
    lateinit var binding: FragmentSearchBinding
    lateinit var viewModel: SearchFragmentViewModel

    companion object {
        fun newInstance(user: User): SearchFragment {
            val searchFragment = SearchFragment()
            searchFragment.currentUser = user
            return searchFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search, container, false
        )
        initFields()
        initViews()
        initListeners()
        viewModel.onScreenReady(currentUser)
        return binding.root
    }

    override fun initFields() {
        viewModel = getViewModel(SearchFragmentViewModel::class.java)
    }

    override fun initViews() {}

    override fun initListeners() {}
}


