
package com.example.slancho.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpenWeatherMapForecastResponse {

    @SerializedName("cod")
    @Expose
    public String cod;
    @SerializedName("message")
    @Expose
    public Double message;
    @SerializedName("city")
    @Expose
    public City city;
    @SerializedName("cnt")
    @Expose
    public Long cnt;
    @SerializedName("list")
    @Expose
    public java.util.List<com.example.slancho.api.models.List> list = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OpenWeatherMapForecastResponse() {
    }

    /**
     * 
     * @param message
     * @param cnt
     * @param cod
     * @param list
     * @param city
     */
    public OpenWeatherMapForecastResponse(String cod, Double message, City city, Long cnt, java.util.List<com.example.slancho.api.models.List> list) {
        super();
        this.cod = cod;
        this.message = message;
        this.city = city;
        this.cnt = cnt;
        this.list = list;
    }

}
