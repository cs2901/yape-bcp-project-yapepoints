package YapeCoupons.controller;

import YapeCoupons.model.Coupon;
import YapeCoupons.services.CouponService;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class RestCoupon {
    @Autowired
    private CouponService coupons;

    // TODO : Integrate into /coupons CRUD
    @GetMapping("/getAllCoupons")
    public HashMap<String, List<BasicDBObject>> getAllCoupons() {
        final List<BasicDBObject> allActiveCoupons = coupons.getAllActiveCoupons();
        HashMap<String, List <BasicDBObject>> response = new HashMap<>();
        response.put("coupons", allActiveCoupons);
        return response;
    }

}
