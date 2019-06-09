package YapeCoupons.services;

import YapeCoupons.model.Coupon;
import YapeCoupons.model.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

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

    public List<Coupon> getActiveCoupons () {
        Query query = new Query();
        query.addCriteria(Criteria.where("active").is(true));
        query.with(new Sort(Sort.Direction.ASC, "update_at"));
        return mongoTemplate.find(query, Coupon.class);
    }

    // For testing
    public List<Coupon> findAll () { return coupons.findAll(); }
    public void deleteAll () { coupons.deleteAll(); }
}
