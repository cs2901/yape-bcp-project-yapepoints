package YapeCoupons.controller;

import YapeCoupons.model.User;
import YapeCoupons.services.UserService;
import YapeCoupons.mail.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

// TODO : Remove this example class

@Controller
public class RestorePassword {
    private Mail mail;

    @Autowired
    private UserService users;

    @RequestMapping(path = "/restorePassword", method = RequestMethod.POST)
    public String restorePassword(@Valid @ModelAttribute("email") String email,
                        BindingResult result, ModelMap model
            , HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            return "error";
        }
        try {
            System.out.println(email);
            User user = users.findByEmail(email);
            if (user == null) {
                model.addAttribute("error", "El correo no está asociado a ninguna cuenta");
                return "redirect:/restorePassword";
            }
            mail.PasswordRectification(user.getEmail());
            return "restoreProcess";
        } catch (Exception e){
            model.addAttribute("error", "Error enviando el correo");
            return "restorePassword";
        }
    }

    @RequestMapping(path = "/restoreProcess", method = RequestMethod.POST)
    public String restoreProcess(@Valid @ModelAttribute("code")int code,
                                  BindingResult result, ModelMap model
            , HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            return "error";
        }
        if (code == 42069){
            // Hacer aqui que el usuario se loguee y redireccionarlo a que cambie su contraseña
            return "redirect:/changePassword";
        }
        return "restoreProcess";
    }

    //TODO: Set the new password!
    @RequestMapping(path = "/restoreFinal", method = RequestMethod.POST)
    public String restoreFinal(@Valid @ModelAttribute("newPassword")String new_password,
                               @Valid @ModelAttribute("againNewPassword")String again_new_password,
                                 BindingResult result, ModelMap model
            , HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            return "redirect:/error";
        }
        if (new_password.equals(again_new_password)){
            return "redirect:/login";
        }
        users.setPassword("12345678", new_password);
        return "changePassword";
    }
}
