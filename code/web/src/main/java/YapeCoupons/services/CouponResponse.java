package YapeCoupons.services;

public class CouponResponse implements java.io.Serializable {
    private String title;
    private String description;
    private String image_path;

    public CouponResponse () {}
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setImage_path(String image_path) { this.image_path = image_path; }

    // For testing
    @Override
    public String toString () {
        return String.format(
                "Coupon[title=%s, description=%s, image_path=%s]",
                title, description, image_path
        );
    }

}
