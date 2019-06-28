package YapeCoupons.controller;

import YapeCoupons.services.CouponService;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;


@RestController
public class RestCoupon {
    @Autowired
    private CouponService coupons;

    @RequestMapping(path = "/getAllCoupons", method = RequestMethod.GET)
    public HashMap<String, List<BasicDBObject>> getAllCoupons() {
        final List<BasicDBObject> allActiveCoupons = coupons.getAllActiveCoupons();
        HashMap<String, List <BasicDBObject>> response = new HashMap<>();
        response.put("coupons", allActiveCoupons);
        return response;
    }

}
