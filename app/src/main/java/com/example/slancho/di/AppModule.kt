/*
 * Copyright (C) 2017 The Android Open Source Project
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

import android.app.Application
import androidx.room.Room
import androidx.work.WorkManager
import com.example.slancho.api.OpenWeatherMapService
import com.example.slancho.api.PexelsService
import com.example.slancho.api.RapidApiOpenWeatherMapService
import com.example.slancho.api.interceptors.OpenWeatherMapInterceptor
import com.example.slancho.api.interceptors.PexelsInterceptor
import com.example.slancho.api.interceptors.RapidApiOpenWeatherMapInterceptor
import com.example.slancho.common.FlavorConstants
import com.example.slancho.db.SlanchoDb
import com.example.slancho.db.dao.*
import com.example.slancho.utils.LocationManager
import com.example.slancho.utils.PermissionsManager
import com.example.slancho.utils.SharedPreferencesManager
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideOpenWeatherMapService(
        client: OkHttpClient,
        gson: Gson,
        openWeatherMapInterceptor: OpenWeatherMapInterceptor
    ): OpenWeatherMapService {

        val newClient = client.newBuilder()
            .addInterceptor(openWeatherMapInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(FlavorConstants.ENV.getOpenWeatherMapApiUrl())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(newClient)
            .build()
            .create(OpenWeatherMapService::class.java)
    }

    @Singleton
    @Provides
    fun providePexelsService(
        client: OkHttpClient,
        gson: Gson,
        pexelsInterceptor: PexelsInterceptor
    ): PexelsService {

        val newClient = client.newBuilder()
            .addInterceptor(pexelsInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(FlavorConstants.ENV.getPexelsApiUrl())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(newClient)
            .build()
            .create(PexelsService::class.java)
    }

    @Singleton
    @Provides
    fun provideRapidApiOpenWeatherMapService(
        client: OkHttpClient,
        gson: Gson,
        rapidApiOpenWeatherMapInterceptor: RapidApiOpenWeatherMapInterceptor
    ): RapidApiOpenWeatherMapService {

        val newClient = client.newBuilder()
            .addInterceptor(rapidApiOpenWeatherMapInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(FlavorConstants.ENV.getRapidApiOpenWeatherMapApiUrl())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(newClient)
            .build()
            .create(RapidApiOpenWeatherMapService::class.java)
    }

    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun provideOpenWeatherMapInterceptor(sharedPreferencesManager: SharedPreferencesManager):
            OpenWeatherMapInterceptor = OpenWeatherMapInterceptor().setup(sharedPreferencesManager)

    @Singleton
    @Provides
    fun provideRapidApiOpenWeatherMapInterceptor(sharedPreferencesManager: SharedPreferencesManager):
            RapidApiOpenWeatherMapInterceptor =
        RapidApiOpenWeatherMapInterceptor().setup(sharedPreferencesManager)

    @Singleton
    @Provides
    fun providePexelInterceptor(sharedPreferencesManager: SharedPreferencesManager):
            PexelsInterceptor = PexelsInterceptor().setup(sharedPreferencesManager)

    @Singleton
    @Provides
    fun provideDb(app: Application): SlanchoDb {
        return Room
            .databaseBuilder(app, SlanchoDb::class.java, "slanchoDb.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideWorkManager() = WorkManager.getInstance()

    @Singleton
    @Provides
    fun provideUserDao(db: SlanchoDb): UserDao = db.userDao()

    @Singleton
    @Provides
    fun provideLastKnownLocationDao(db: SlanchoDb): LastKnownLocationDao = db.lastKnownLocationDao()

    @Singleton
    @Provides
    fun providesForecastDao(db: SlanchoDb): ForecastDao = db.forecastDao()

    @Singleton
    @Provides
    fun providesCityDao(db: SlanchoDb): CityDao = db.cityDao()

    @Singleton
    @Provides
    fun providesForecastInfoDao(db: SlanchoDb): ForecastInfoDao = db.forecastInfoDao()

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideLocationManager(app: Application): LocationManager = LocationManager(app)

    @Singleton
    @Provides
    fun providePermissionsManager(app: Application): PermissionsManager = PermissionsManager(app)

    @Singleton
    @Provides
    fun provideSharedPreferencesManager(app: Application) = SharedPreferencesManager(app)
}