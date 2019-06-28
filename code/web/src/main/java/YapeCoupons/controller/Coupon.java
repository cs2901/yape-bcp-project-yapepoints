package YapeCoupons.controller;

import YapeCoupons.services.CouponService;
import YapeCoupons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static YapeCoupons.helpers.helper.getExtension;
import static YapeCoupons.helpers.helper.getRandomCode;
import static YapeCoupons.middleware.Middleware.isLogged;

@Controller
public class Coupon {

    @Autowired
    private UserService users;

    @Autowired
    private CouponService coupons;

    public static final String UPLOAD_DIRECTORY = System.getProperty("user.home") + "/uploads/";
    // public static final String URI = "http://178.128.216.229:8080/images/";
    public static final String URI = "http://localhost:9000/images/";

    @RequestMapping(path = "/create_coupon", method = RequestMethod.GET)
    public String createCouponGet(HttpServletRequest request,
                                  RedirectAttributes redirectAttributes,
                                  ModelMap map
                                  ) throws Exception {
        try {
            if (!isLogged(request)) {
                redirectAttributes.addFlashAttribute("error", "Necesitas logearte");
                return "redirect:login";
            }
            String dni = request.getSession().getAttribute("dni").toString();
            if (!users.filledBusinessInformation(dni)) {
                redirectAttributes.addFlashAttribute("error", "Por favor, rellena la información de tu negocio");
                return "redirect:register_local";
            }
            map.addAttribute("business_name", "Agregar cupón");
            map.addAttribute("title", "YapeCupones - Agregar cupón");
            return "create_coupon";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage().toString());
            return "redirect:home";
        }
    }

    @RequestMapping(path = "/create_coupon", method = RequestMethod.POST)
    public String createCouponPost(@RequestParam("title") String title,
                                   @RequestParam("description") String description,
                                   @RequestParam("cost") String cost,
                                   @RequestParam("image") MultipartFile file,
                                   HttpServletRequest request,
                                   RedirectAttributes redirectAttributes) throws Exception {

        try {
            if (!isLogged(request)) {
                redirectAttributes.addFlashAttribute("error", "Necesitas logearte");
                return "redirect:login";
            }
            String dni = request.getSession().getAttribute("dni").toString();
            if (!users.filledBusinessInformation(dni)) {
                redirectAttributes.addFlashAttribute("error", "Por favor, rellena la información de tu negocio");
                return "redirect:register_local";
            }
            String extension = getExtension(file.getOriginalFilename());
            assert extension.length() > 0;
            String hash_name = getRandomCode(21) + '.' + extension;
            Path file_path = Paths.get(UPLOAD_DIRECTORY, hash_name);
            String image_path = URI + hash_name;
            Files.write(file_path, file.getBytes());
            coupons.createCoupon(dni, title, description, cost, image_path);
            redirectAttributes.addFlashAttribute("success", "Promoción agregada exitosamente");
            return "redirect:home";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage().toString());
            return "redirect:home";
        }
    }

    @RequestMapping("/coupon/edit/{id}")
    public String editCouponGet(@PathVariable("id") String id,
                                  ModelMap map,
                                  HttpServletRequest request,
                                  RedirectAttributes redirectAttributes) throws Exception {
        map.addAttribute("coupon", coupons.findById(id));
        
        return "edit_coupon";
    }

    // Las funciones de abajo hasta ahora no se usan para nada

    @RequestMapping(path = "/coupon/{id}", method = RequestMethod.GET)
    public String getCoupon(@PathVariable String id,
                            ModelMap map) throws Exception {
        map.addAttribute("coupon", coupons.findById(id));

        return "coupon";
    }

    @RequestMapping(path = "/update_coupon/{id}", method = RequestMethod.POST)
    public String updateCoupon(@PathVariable String id,
                               @RequestParam("title") String title,
                               @RequestParam("description") String description,
                               @RequestParam("cost") String precio
    ) throws Exception {
        coupons.update(id, title, description);

        return "redirect:/coupon/" + id;
    }

    @RequestMapping(path = "/coupon/delete/{id}")
    public String deleteCoupon(@PathVariable String id) throws Exception{
        coupons.toggleState(id);

        return "redirect:/home";
    }

}
