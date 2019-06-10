package com.example.yapecupones;

public class CouponsList {

    private String _id;
    private String title;
    private String description;
    private String image_path;
    private String business_name;

    public String get_id() { return _id; }
    public String getDescription() { return description; }
    public String getTitle() { return title; }
    public String getImage_path() { return image_path; }
    public String getBusiness_name() { return business_name; }

    public CouponsList(String _id, String description, String title, String image_path, String business_name) {
        this._id = _id;
        this.description = description;
        this.title = title;
        this.image_path = image_path;
        this.business_name = business_name;
    }

}
