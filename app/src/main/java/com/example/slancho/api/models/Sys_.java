
package com.example.slancho.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys_ {

    @SerializedName("pod")
    @Expose
    public String pod;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Sys_() {
    }

    /**
     * 
     * @param pod
     */
    public Sys_(String pod) {
        super();
        this.pod = pod;
    }

}
