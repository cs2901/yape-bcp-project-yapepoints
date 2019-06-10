package com.example.yapecupones;

public class CouponsList {

    private String business_name;
    private String business_description;
    private String business_map_url;
    private String title;
    private String description;
    private String image_path;

    public String getBusiness_name() { return business_name; }
    public String getBusiness_description() { return business_description; }
    public String getBusiness_map_url() { return business_map_url; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getImage_path() { return image_path; }

    public CouponsList(String business_name, String business_description, String business_map_url, String title, String description, String image_path) {
        this.business_name = business_name;
        this.business_description = business_description;
        this.business_map_url = business_map_url;
        this.title = title;
        this.description = description;
        this.image_path = image_path;
    }

}
