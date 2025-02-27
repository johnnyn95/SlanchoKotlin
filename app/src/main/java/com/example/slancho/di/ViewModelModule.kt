/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.slancho.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.slancho.ui.signIn.SignInActivityViewModel
import com.example.slancho.ui.main.MainActivityViewModel
import com.example.slancho.ui.main.news.NewsFragmentViewModel
import com.example.slancho.ui.main.search.SearchFragmentViewModel
import com.example.slancho.ui.main.weather.WeatherFragmentViewModel
import com.example.slancho.ui.signUp.SignUpActivityViewModel
import com.example.slancho.ui.splash.SplashActivityViewModel
import com.example.slancho.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashActivityViewModel::class)
    abstract fun bindSplashActivityViewModel(viewModel: SplashActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInActivityViewModel::class)
    abstract fun bindSignInActivityViewModel(viewModel: SignInActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpActivityViewModel::class)
    abstract fun bindSignUpActivityViewModel(viewModel: SignUpActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeatherFragmentViewModel::class)
    abstract fun bindWeatherFragmentViewModel(viewModel: WeatherFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchFragmentViewModel::class)
    abstract fun bindSearchFragmentViewModel(viewModel: SearchFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsFragmentViewModel::class)
    abstract fun bindNewsFragmentViewModel(viewModel: NewsFragmentViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}