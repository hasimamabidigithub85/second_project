
package com.gropse.epub.Activity.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetTitleModel {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("Tittle")
    @Expose
    public String tittle;
    @SerializedName("ViewType")
    @Expose
    public String viewType;
    @SerializedName("Message")
    @Expose
    public String message;
    @SerializedName("Url")
    @Expose
    public String url;



}
