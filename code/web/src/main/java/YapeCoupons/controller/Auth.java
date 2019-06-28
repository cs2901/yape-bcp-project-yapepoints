package YapeCoupons.controller;

import YapeCoupons.model.User;
import YapeCoupons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static YapeCoupons.middleware.Middleware.isLogged;

@Controller
public class Auth {

    @Autowired
    private UserService users;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        if (isLogged(request)) {
            return "redirect:home";
        }
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String postLogin(@RequestParam("dni") String dni,
                            @RequestParam("password") String password,
                            HttpServletRequest request,
                            RedirectAttributes redirectAttributes) throws Exception {
        try {
            User expected_user = users.findByDni(dni);
            if (expected_user == null) {
                redirectAttributes.addFlashAttribute("error", "Usuario no existente");
                return "redirect:login";
            }
            if (expected_user.getPassword().equals(password)) {
                request.getSession().setAttribute("dni", expected_user.getDni());
                request.getSession().setAttribute("given_name", expected_user.getGiven_name());
                redirectAttributes.addFlashAttribute("success", "Bienvenido");
                return "redirect:/home";
            }
            redirectAttributes.addFlashAttribute("error", "Constraseña incorrecta");
            return "redirect:login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error en el registro");
            return "redirect:login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session,
                         RedirectAttributes redirectAttributes) throws Exception {
        session.removeAttribute("dni");
        session.removeAttribute("given_name");
        redirectAttributes.addFlashAttribute("success", "Vuelve pronto!");
        return "redirect:login";
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String getRegister() { return "register"; }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String postRegister(@RequestParam("given_name") String given_name,
                               @RequestParam("family_name") String family_name,
                               @RequestParam("dni") String dni,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("confirm_password") String confirm_password,
                               HttpServletRequest request,
                               RedirectAttributes redirectAttributes) throws Exception {
        try {
            if (!password.equals(confirm_password)) {
                redirectAttributes.addFlashAttribute("error", "Las contraseñas son distintas");
                return "redirect:register";
            }
            users.createUser(email, given_name, family_name, dni, password);
            request.getSession().setAttribute("dni", dni);
            request.getSession().setAttribute("given_name", given_name);
            redirectAttributes.addFlashAttribute("success", "Registro exitoso");
            return "redirect:login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:register";
        }
    }

    @RequestMapping(path = "/register_local", method = RequestMethod.GET)
    public String getRegisterLocal (HttpServletRequest request,
                                    RedirectAttributes redirectAttributes) throws Exception {
        try {
            if (!isLogged(request)) {
                redirectAttributes.addFlashAttribute("error", "Necesitas logearte");
                return "redirect:login";
            }
            return "register_local";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage().toString());
            return "redirect:login";
        }
    }

    @RequestMapping(path = "/register_local", method = RequestMethod.POST)
    public String postRegisterLocal (@RequestParam("business_ruc") String business_ruc,
                                     @RequestParam("business_name") String business_name,
                                     @RequestParam("business_description") String business_description,
                                     @RequestParam("business_address") String business_address,
                                     @RequestParam("business_region") String business_region,
                                     @RequestParam("business_celular") String business_celular,
                                     @RequestParam("business_latitud") String business_latitud,
                                     @RequestParam("business_longitud") String business_longitud,
                                     HttpServletRequest request,
                                     RedirectAttributes redirectAttributes) throws Exception {
        try {
            if (!isLogged(request)) {
                redirectAttributes.addFlashAttribute("error", "Necesitas logearte");
                return "redirect:login";
            }
            String dni = request.getSession().getAttribute("dni").toString();
            System.out.println(dni);
            users.setBusiness(dni, business_name, business_description, business_address, business_region, business_celular, business_ruc);
            redirectAttributes.addFlashAttribute("success", "Información del negocio registrada");
            return "redirect:home";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage().toString());
            return "redirect:login";
        }
    }
}
