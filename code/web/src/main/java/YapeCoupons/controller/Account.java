package YapeCoupons.controller;

import YapeCoupons.model.User;
import YapeCoupons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@SessionAttributes("name")
public class Account {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserService users;

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("userView", "userName", new User());
    }

    @RequestMapping(path = "/api/account", method = RequestMethod.POST)
    public String NewAccount(@Valid @ModelAttribute("user")User user,
                             BindingResult result, ModelMap model
                            ,HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            return "error";
        }

        String password2 = request.getParameter("password2");
        if(user.getPassword().equals(password2)) {

        }
        users.createUser(
                user.getEmail(),
                user.getGiven_name(),
                user.getFamily_name(),
                user.getDni(),
                encoder.encode(user.getPassword())

        );
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String Login(@Valid @ModelAttribute("user")User user,
                             BindingResult result, ModelMap model
            ,HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            return "error";
        }
        User _user = users.findByEmail(user.getEmail());
        System.out.println(user);
        System.out.println(_user);
        if( _user.getPassword().equals(user.getPassword())){
            model.put("name", _user.getGiven_name());
            //model.put("name", _user.getGiven_name());
            return "home";
        }
        return "login";
    }

}