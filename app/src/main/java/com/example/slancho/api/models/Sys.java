
package com.example.slancho.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys {

    @SerializedName("population")
    @Expose
    public Long population;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Sys() {
    }

    /**
     * 
     * @param population
     */
    public Sys(Long population) {
        super();
        this.population = population;
    }

}
