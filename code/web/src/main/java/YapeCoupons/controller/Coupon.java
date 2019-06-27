package YapeCoupons.controller;

import YapeCoupons.services.CouponService;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import static YapeCoupons.middleware.Middleware.isLogged;

@Controller
public class Coupon {

    @Autowired
    private CouponService coupons;

    public static final String UPLOAD_DIRECTORY = System.getProperty("user.home") + "/uploads/";
    public static final String URI = "http://178.128.216.229:8080/images/";

    @RequestMapping(path = "/getAllCoupons", method = RequestMethod.GET)
    public HashMap<String, List<BasicDBObject>> getAllCoupons() {
        final List<BasicDBObject> allActiveCoupons = coupons.getAllActiveCoupons();
        HashMap<String, List <BasicDBObject>> response = new HashMap<>();
        response.put("coupons", allActiveCoupons);
        return response;
    }

    @RequestMapping(path = "/create_coupon", method = RequestMethod.GET)
    public String createCouponGet(HttpServletRequest request,
                                  RedirectAttributes redirectAttributes) throws Exception {
        try {
            if (!isLogged(request)) {
                redirectAttributes.addFlashAttribute("error", "Necesitas logearte");
                return "redirect:login";
            }
            return "create_coupon";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage().toString());
            return "redirect:home";
        }
    }

    // TO DO: Encriptar nombre con que se guarda
    // AUn no guardo los atributos del cupon
    @RequestMapping(path = "/create_coupon", method = RequestMethod.POST)
    public String createCouponPost(@RequestParam("title") String title,
                                   @RequestParam("description") String description,
                                   @RequestParam("cost") String cost,
                                   @RequestParam("image") MultipartFile file,
                                   HttpServletRequest request,
                                   RedirectAttributes redirectAttributes) throws Exception {
        System.out.println("************ Hola ****************");

        try {
            Path file_path = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
            String image_path = URI + file.getOriginalFilename();
            Files.write(file_path, file.getBytes());
            String dni = request.getSession().getAttribute("dni").toString();
            coupons.createCoupon(dni, title, description, image_path);
            redirectAttributes.addFlashAttribute("success", "Promoción agregada exitosamente");
            return "redirect:home";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage().toString());
            return "redirect:home";
        }
    }

    @ResponseBody
    @RequestMapping("/coupon/edit/{id}")
    public String editCouponGet(@PathVariable("id") String id,
                                  HttpServletRequest request,
                                  RedirectAttributes redirectAttributes) throws Exception {
        return "TODO : Show edit view for coupon with id " + id;
    }

    // Las funciones de abajo hasta ahora no se usan para nada

    @RequestMapping(path = "/coupon/{id}", method = RequestMethod.GET)
    public YapeCoupons.model.Coupon getCoupon(@PathVariable String id) throws Exception {
        YapeCoupons.model.Coupon coupon = new YapeCoupons.model.Coupon();

        coupon = coupons.findById(id);

        return coupon;
    }

    @RequestMapping(path = "/update_coupon/{id}", method = RequestMethod.POST)
    public void updateCoupon(@PathVariable String id) throws Exception {
        YapeCoupons.model.Coupon coupon = coupons.findById(id);

        String title = "test";
        String description = "Hello, World!";

        coupons.update(id, title, description);
    }

    @RequestMapping(path = "/coupon/{id}", method = RequestMethod.DELETE)
    public void deleteCoupon(@PathVariable String id) throws Exception{
        coupons.toggleState(id);
    }

}
