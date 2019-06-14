package com.example.yapecupones;

import org.json.JSONObject;

public class CouponsList {

    private JSONObject _id;
    private String title;
    private String description;
    private String image_path;
    private String business_name;

    public JSONObject get_id() { return _id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getImage_path() { return image_path; }
    public String getBusiness_name() { return business_name; }

    public CouponsList(JSONObject id, String title, String description, String image_path, String business_name) {
        this._id = id;
        this.title = title;
        this.description = description;
        this.image_path = image_path;
        this.business_name = business_name;
    }

}