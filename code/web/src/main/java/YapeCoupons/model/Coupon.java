package YapeCoupons.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class Coupon {
    @Id
    private ObjectId _id;

    private String dni_user;
    private String title;
    private String description;
    private String cost;
    private String image_path;
    private Boolean active;
    private Date update_at;

    public Coupon () {}

    public ObjectId getId() {
        return _id;
    }
    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public String getDni_user() { return dni_user; }
    public void setDni_user(String dni_user) { this.dni_user = dni_user; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCost() { return cost; }
    public void setCost(String cost) { this.cost = cost; }

    public String getImage_path() { return image_path; }
    public void setImage_path(String image_path) { this.image_path = image_path; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Date getUpdate_at() { return update_at; }
    public void setUpdate_at(Date update_at) { this.update_at = update_at; }

}
