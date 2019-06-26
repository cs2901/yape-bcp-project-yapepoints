package YapeCoupons.controller;

import YapeCoupons.model.User;
import YapeCoupons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import static YapeCoupons.middleware.Middleware.isLogged;

@Controller
public class Home {

    @Autowired
    private UserService users;

    @RequestMapping("/")
    public String index(ModelMap map) {
        return "index";
    }

    @RequestMapping("/home")
    public String home(HttpServletRequest request,
                       ModelMap map,
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
            String given_name = request.getSession().getAttribute("given_name").toString();
            map.addAttribute("given_name", given_name);
            User user = users.findByDni(dni);
            map.addAttribute("business_name", user.getBusiness_name());
            return "home";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage().toString());
            return "redirect:login";
        }
    }
}
