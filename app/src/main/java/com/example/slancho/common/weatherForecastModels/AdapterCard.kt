package com.example.slancho.common.weatherForecastModels

abstract class AdapterCard {
    abstract fun isCollapsable(): Boolean
    abstract fun getCardType(): AdapterCardType
}