
package com.gropse.epub.Activity.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookModel {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("Class")
    @Expose
    public String _class;
    @SerializedName("Medium")
    @Expose
    public String medium;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
