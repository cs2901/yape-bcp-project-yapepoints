package YapeCoupons.services;

import YapeCoupons.model.Coupon;
import YapeCoupons.model.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CouponService {
    @Autowired
    private CouponRepository coupons;

    @Autowired
    private UserService users;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void createCoupon (String dni_user, String title, String description, String image_path) throws Exception {
        try {
            if (users.findByDni(dni_user) == null) {
                throw new Exception("DNI invalido");
            }
            Coupon coupon = new Coupon();
            coupon.setDni_user(dni_user);
            coupon.setTitle(title);
            coupon.setDescription(description);
            coupon.setImage_path(image_path);
            coupon.setActive(true);
            coupon.setUpdate_at(new Date());
            coupons.save(coupon);
        } catch (Exception e) {
            throw new Exception("Error agregando cupón. " + e.getMessage());
        }
    }

    public Coupon findById (String coupon_id) throws Exception {
        try {
            return coupons.findBy_id(coupon_id);
        } catch (Exception e) {
            throw new Exception("Error obteniendo un cupon.");
        }
    }

    public void update (String coupon_id, String title, String description) throws Exception {
        try {
            Coupon coupon = findById(coupon_id);
            coupon.setTitle(title);
            coupon.setDescription(description);
            coupon.setUpdate_at(new Date());
            coupons.save(coupon);
        } catch (Exception e) {
            throw new Exception("Error actualizando la información del cupón");
        }
    }

    public void toggleState (String coupon_id) throws Exception {
        try {
            Coupon coupon = findById(coupon_id);
            if (coupon.getActive()) {
                coupon.setActive(false);
            } else {
                coupon.setActive(true);
            }
            coupon.setUpdate_at(new Date());
            coupons.save(coupon);
        } catch (Exception e) {
            throw new Exception("Error alternando estado del cupón");
        }
    }

    public void setImageUrl (String coupon_id, String image_path) throws Exception {
        try {
            Coupon coupon = findById(coupon_id);
            coupon.setImage_path(image_path);
            coupons.save(coupon);
        } catch (Exception e) {
            throw new Exception("Error guardando la imagen");
        }
    }

    public List<Coupon> getActiveCoupons (String dni_user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("active").is(true));
        query.addCriteria(Criteria.where("dni_user").is(dni_user));
        query.with(new Sort(Sort.Direction.ASC, "update_at"));
        return mongoTemplate.find(query, Coupon.class);
    }

    public List<Coupon> getAllActiveCoupons () {
        Query query = new Query();
        query.addCriteria(Criteria.where("active").is(true));
        query.with(new Sort(Sort.Direction.ASC, "update_at"));
        List<Coupon> activeCoupons = mongoTemplate.find(query, Coupon.class);
        return activeCoupons;
    }

    // For testing
    public List<Coupon> findAll () { return coupons.findAll(); }
    public void deleteAll () { coupons.deleteAll(); }
}
