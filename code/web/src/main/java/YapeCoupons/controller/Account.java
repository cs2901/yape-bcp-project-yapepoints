package YapeCoupons.controller;

import YapeCoupons.mail.Mail;
import YapeCoupons.model.User;
import YapeCoupons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import static YapeCoupons.middleware.Middleware.isLogged;

@Controller
public class Account {

   private Mail mail;

    @Autowired
    private UserService users;

    @RequestMapping(path = "/restore_password", method = RequestMethod.GET)
    public String restorePasswordGet() { return "restore_password"; }

    @RequestMapping(path = "/restore_password", method = RequestMethod.POST)
    public String restorePasswordPost(@RequestParam("email") String email,
                                  HttpServletRequest request,
                                  RedirectAttributes redirectAttributes) throws Exception {
        try {
            User user = users.findByEmail(email);
            if (user == null) {
                redirectAttributes.addFlashAttribute("error", "El correo no está asociado a ninguna cuenta");
                return "redirect:restore_password";
            }
            String code = mail.PasswordRectification(email);
            request.getSession().setAttribute("code", code);
            request.getSession().setAttribute("code_email", email);
            return "restore_process";
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Error enviando el correo");
            return "redirect:restore_Password";
        }
    }

    @RequestMapping(path = "/restore_process", method = RequestMethod.GET)
    public String restoreProcessGet() { return "restore_process"; }

    @RequestMapping(path = "/restore_process", method = RequestMethod.POST)
    public String restoreProcessPost(@RequestParam("code") String code,
                                 HttpServletRequest request,
                                 RedirectAttributes redirectAttributes) throws Exception {
        try {
            String expected_code = request.getSession().getAttribute("code").toString();
            if (code.equals(expected_code)) {
                String email = request.getSession().getAttribute("code_email").toString();
                User user = users.findByEmail(email);
                request.getSession().setAttribute("dni", user.getDni());
                request.getSession().setAttribute("given_name", user.getGiven_name());
                return "redirect:/change_password";
            }
            return "redirect:restore_process";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Algo salió mal");
            return "redirect:/restore_password";
        }
    }

    @RequestMapping(path = "/change_password", method = RequestMethod.GET)
    public String changePasswordGet(HttpServletRequest request,
                                    RedirectAttributes redirectAttributes) {
        if (!isLogged(request)) {
            redirectAttributes.addFlashAttribute("error", "Necesitas logearte");
            return "redirect:login";
        }
        return "change_password";
    }

    @RequestMapping(path = "/change_password", method = RequestMethod.POST)
    public String changePasswordPost(@RequestParam("newPassword") String password,
                                     @RequestParam("againNewPassword") String confirm_password,
                                     HttpServletRequest request,
                                     RedirectAttributes redirectAttributes) {
        try {
            if (!isLogged(request)) {
                redirectAttributes.addFlashAttribute("error", "Necesitas logearte");
                return "redirect:login";
            }
            if (!password.equals(confirm_password)) {
                redirectAttributes.addFlashAttribute("error", "Las contraseñas no coinciden");
                return "redirect:change_password";
            }
            String dni = request.getSession().getAttribute("dni").toString();
            users.setPassword(dni, password);
            redirectAttributes.addFlashAttribute("success", "Su contraseña fue cambiado exitosamente");
            return "redirect:home";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage().toString());
            return "redirect:change_password";
        }
    }

    @RequestMapping(path = "/update_information", method = RequestMethod.GET)
    public String updateInformationGet(HttpServletRequest request,
                                       ModelMap map,
                                       RedirectAttributes redirectAttributes) throws Exception {
        try {
            if (!isLogged(request)) {
                redirectAttributes.addFlashAttribute("error", "Necesitas logearte");
                return "redirect:login";
            }
            String dni = request.getSession().getAttribute("dni").toString();
            User user = users.findByDni(dni);
            map.addAttribute("given_name", user.getGiven_name());
            map.addAttribute("family_name", user.getFamily_name());
            map.addAttribute("email", user.getEmail());
            return "update_information";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage().toString());
            return "redirect:home";
        }
    }

    @RequestMapping(path = "/update_information", method = RequestMethod.POST)
    public String updateInformationPost(@RequestParam("given_name") String given_name,
                                        @RequestParam("family_name") String family_name,
                                        @RequestParam("email") String email,
                                        HttpServletRequest request,
                                        RedirectAttributes redirectAttributes) throws Exception {
        try {
            if (!isLogged(request)) {
                redirectAttributes.addFlashAttribute("error", "Necesitas logearte");
                return "redirect:login";
            }
            String dni = request.getSession().getAttribute("dni").toString();
            users.updateUserInformation(dni, given_name, family_name, email);
            redirectAttributes.addFlashAttribute("success", "Información actualizada exitosamente");
            return "redirect:/home";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage().toString());
            return "redirect:update_information";
        }
    }

}
