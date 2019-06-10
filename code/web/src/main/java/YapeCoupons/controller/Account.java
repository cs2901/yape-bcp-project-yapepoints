package YapeCoupons.controller;

import YapeCoupons.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class Account {
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("userView", "userName", new User());
    }

    @RequestMapping(path = "/api/account", method = RequestMethod.POST)
    public String NewAccount(@Valid @ModelAttribute("user")User user,
                             BindingResult result, ModelMap model
                            ,HttpServletRequest request) {

        if (result.hasErrors()) {
            return "error";
        }

        String password2 = request.getParameter("password2");
        if(user.getPassword().equals(password2)) {

        }
        return "home";
    }


}