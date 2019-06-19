package YapeCoupons.controller;

import YapeCoupons.model.Coupon;
import YapeCoupons.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

@Controller
public class CouponControl {

    @Autowired
    private CouponService coupons;

    public static final String UPLOAD_DIRECTORY = System.getProperty("user.home") + "/uploads/";
    public static final String URI = "http://178.128.216.229:8080/images/";

    // TODO : Adjust to CRUD notation: /coupon with POST method
    @RequestMapping("/add-coupon")
    public String createCoupon(Model model,
                               @RequestParam("title") String title,
                               @RequestParam("description") String description,
                               @RequestParam("image") MultipartFile file) {
        Path file_path = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        String image_path = URI + file.getOriginalFilename();
        try {
            Files.write(file_path, file.getBytes());
            coupons.createCoupon("12345678", title, description, image_path);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("error", e.getMessage());
        }
        return "home";
    }

    @GetMapping("/coupon")
    @ResponseBody
    public List<Coupon> getCoupons() {

        List<Coupon> coupons = new LinkedList<>();

        return coupons;
    }

    @GetMapping("/coupon/{id}")
    @ResponseBody
    public Coupon getCoupon(@PathVariable String id) throws Exception {
        Coupon coupon = new Coupon();

        coupon = coupons.findById(id);

        return coupon;
    }

    @PutMapping("/update_coupon/{id}")
    @ResponseBody
    public void updateCoupon(@PathVariable String id) throws Exception {
        Coupon coupon = coupons.findById(id);

        String title = "test";
        String description = "Hello, World!";

        coupons.update(id, title, description);
    }

    @DeleteMapping("/coupon/{id}")
    @ResponseBody
    public void deleteCoupon(@PathVariable String id) throws Exception{
        coupons.toggleState(id);

    }

}
