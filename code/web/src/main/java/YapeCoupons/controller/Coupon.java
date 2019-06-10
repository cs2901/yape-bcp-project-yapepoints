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

    private static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/img/uploads";

    @RequestMapping("/add-coupon")
    public String createCoupon(Model model,
                               @RequestParam("title") String title,
                               @RequestParam("description") String description,
                               @RequestParam("image") MultipartFile file) {
        System.out.println(title);
        System.out.println(description);
        Path file_path = Paths.get(uploadDirectory, file.getOriginalFilename());
        String image_path = "http://localhost:9000/img/uploads/" + file.getOriginalFilename();
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
