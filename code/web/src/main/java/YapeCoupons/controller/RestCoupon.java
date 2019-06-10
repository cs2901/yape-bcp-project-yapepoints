package YapeCoupons.controller;

import YapeCoupons.model.Coupon;
import YapeCoupons.model.CouponRepository;
import YapeCoupons.services.CouponResponse;
import YapeCoupons.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.zip.CheckedOutputStream;

@RestController
public class RestCoupon {
    @Autowired
    private CouponService coupons;

    @GetMapping("/getAllCoupons")
    public HashMap<String, List<Coupon>> getAllCoupons() {
        final List<Coupon> allActiveCoupons = coupons.getAllActiveCoupons();
        HashMap<String, List <Coupon>> response = new HashMap<>();
        response.put("coupons", allActiveCoupons);
        return response;
    }

}
