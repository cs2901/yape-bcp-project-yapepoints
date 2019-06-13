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

    @RequestMapping(path = "/restorePassword", method = RequestMethod.POST)
    public String restorePassword(@Valid @ModelAttribute("user")User user,
                        BindingResult result, ModelMap model
            , HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            return "error";
        }
        mail.PasswordRectification(user.getEmail());
        return "restoreProcess";
    }

    @RequestMapping(path = "/restoreProcess", method = RequestMethod.POST)
    public String restoreProcess(@Valid @ModelAttribute("code")int code,
                                  BindingResult result, ModelMap model
            , HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            return "error";
        }
        if (code == 42069){
            return "restoreFinal";
        }
        return "restoreProcess";
    }

    //TODO: Set the new password!
    @RequestMapping(path = "/restoreFinal", method = RequestMethod.POST)
    public String restoreFinal(@Valid @ModelAttribute("newPassword")String newPassword,
                               @Valid @ModelAttribute("againNewPassword")String againNewPassword,
                                 BindingResult result, ModelMap model
            , HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            return "error";
        }
        if (newPassword.equals(againNewPassword)){
            return "login";
        }
        return "restoreFinal";
    }
}
