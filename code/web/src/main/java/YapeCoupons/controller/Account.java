package YapeCoupons.controller;

import YapeCoupons.model.User;
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
public class Account {

    @Autowired
    private UserService users;

    @RequestMapping("/home")
    public String home() { return "home"; }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String getRegister() { return "register"; }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String postRegister(@Valid @ModelAttribute("user")User user,
                             BindingResult result, ModelMap model,
                             HttpServletRequest request) throws Exception {
        try {
            if (result.hasErrors()) {
                model.addAttribute("error", "Algo salio mal");
                return "redirect:/register";
            }
            String password2 = request.getParameter("password2");
            if (!user.getPassword().equals(password2)) {
                model.addAttribute("error", "Las contraseñas son distintas");
                return "redirect:/register";
            }
            users.createUser(
                    user.getEmail(),
                    user.getGiven_name(),
                    user.getFamily_name(),
                    user.getDni(),
                    user.getPassword() // TO DO: encoder.encode(user.getPassword())
            );
            // TO DO: HACER QUE AQUI EL USUARIO SE LOGUEE Y
            // SE GUARDE SU DNI COMO SESION
            return "redirect:/register-local";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/register";
        }
    }

    @RequestMapping(path = "/register-local", method = RequestMethod.GET)
    public String getRegisterLocal () { return "register_local"; }

    @RequestMapping(path = "/register-local", method = RequestMethod.POST)
    public String postRegisterLocal () {
        return "redirect:/home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String getLogin() { return "login"; }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String postLogin(@Valid @ModelAttribute("user")User user,
                             BindingResult result, ModelMap model
            ,HttpServletRequest request) throws Exception {

        if (result.hasErrors()) {
            return "redirect:/error";
        }
        User _user = users.findByEmail(user.getEmail());
        if( _user.getPassword().equals(user.getPassword())){
            model.put("name", _user.getGiven_name());
            //model.put("name", _user.getGiven_name());
            return "redirect:/home";
        }
        return "redirect:/login";
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