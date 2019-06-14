package YapeCoupons.controller;

import YapeCoupons.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class Coupon {

    @Autowired
    private CouponService coupons;

    public static final String UPLOAD_DIRECTORY = System.getProperty("user.home") + "/uploads/";
    public static final String URI = "http://localhost:9000/images/";

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
    public String getCoupons() {
        return "TODO : Return list of coupons\n";
    }

    @GetMapping("/coupon/{id}")
    @ResponseBody
    public String getCoupon(@PathVariable String id) {
        return "TODO : Return coupon " + id + "\n";
    }

    @PutMapping("/coupon/{id}")
    @ResponseBody
    public String updateCoupon(@PathVariable String id) {
        return "TODO : Update coupon " + id + "\n";
    }

    @DeleteMapping("/coupon/{id}")
    @ResponseBody
    public String deleteCoupon(@PathVariable String id) {
        return "TODO : Delete coupon " + id + "\n";
    }

}
