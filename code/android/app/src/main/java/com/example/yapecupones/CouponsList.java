package com.example.yapecupones;

import org.json.JSONObject;

public class CouponsList {

    private JSONObject _id;
    private String title;
    private String description;
    private String cost;
    private String image_path;
    private String business_name;
    private String business_region;
    private String business_address;

    public JSONObject get_id() { return _id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCost() { return cost; }
    public String getImage_path() { return image_path; }
    public String getBusiness_name() { return business_name; }
    public String getBusiness_region() { return business_region; }
    public String getBusiness_address() { return business_address; }

    public CouponsList(JSONObject id, String title, String description, String cost,
                       String image_path, String business_name, String business_region,
                       String business_address) {
        this._id = id;
        this.title = title;
        this.cost = cost;
        this.description = description;
        this.image_path = image_path;
        this.business_name = business_name;
        this.business_region = business_region;
        this.business_address = business_address;
    }
}