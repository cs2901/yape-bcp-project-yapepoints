package YapeCoupons.controller;

import YapeCoupons.model.User;
import YapeCoupons.services.CustomUserDetailsService;
import YapeCoupons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@SessionAttributes("name")
public class Account {
    private CustomUserDetailsService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserService users;

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("userView", "userName", new User());
    }

    @RequestMapping(path = "/api/account", method = RequestMethod.POST)
    public String newAccount(@Valid @ModelAttribute("user")User user,
                             BindingResult result, ModelMap model,
                             HttpServletRequest request) throws Exception {

        try {
            if (result.hasErrors()) {
                model.addAttribute("error", "Algo salio mal");
                return "register";
            }

            String password2 = request.getParameter("password2");
            if (!user.getPassword().equals(password2)) {
                model.addAttribute("error", "Las contraseñas son distintas");
                return "register";
            }
            users.createUser(
                    user.getEmail(),
                    user.getGiven_name(),
                    user.getFamily_name(),
                    user.getDni(),
                    user.getPassword()
                    //encoder.encode(user.getPassword())
            );
            return "home";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute("user")User user,
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

    @RequestMapping(value = "/settings/profile", method = RequestMethod.GET)
    public String profileSettings(Model model) throws Exception {
        // TODO : Replace implicit declaration with session
        String dni = "12345678";

        User user;

        try {
            user = users.findByDni(dni);
        } catch (Exception e) {
            throw new Exception("Error intentando obtener usuario con dni " + dni);
        }

        model.addAttribute("user", user);

        return "update_account";
    }

    @PostMapping("/settings/profile/update")
    public String updateAccount(
            // TODO : Rename parameters: More descriptive, less redundant
        @RequestParam("new_name") String name,
        @RequestParam("new_lastname") String lastname,
        @RequestParam("new_email") String email) {

        System.out.println("Updated account " + name);

        // TODO : Create method that allows the update of only email, name and lastname attributes.
        try {
            users.createUser(email, name, lastname, "12345678", "");
        } catch (Exception e) {

        }

        return "redirect:/settings/profile";
    }
}