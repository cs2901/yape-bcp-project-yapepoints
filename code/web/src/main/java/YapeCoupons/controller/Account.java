package YapeCoupons.controller;

import YapeCoupons.model.User;
import YapeCoupons.services.CustomUserDetailsService;
import YapeCoupons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class Account {
    private CustomUserDetailsService userService;

    @Autowired
    private UserService users;

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String getRegister() { return "register"; }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String postRegister(@Valid @ModelAttribute("user")User user,
                               ModelMap model,
                               HttpServletRequest request) throws Exception {
        try {
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
            return "register";
        }
    }

    @RequestMapping(path = "/register-local", method = RequestMethod.GET)
    public String getRegisterLocal () { return "register_local"; }

    @RequestMapping(path = "/register-local", method = RequestMethod.POST)
    public String postRegisterLocal () {
        return "redirect:/home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() { return "login"; }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String postLogin(@RequestParam("dni") String dni,
                            @RequestParam("password") String password,
                            HttpServletRequest request) throws Exception {
        try {
            User expected_user = users.findByDni(dni);
            if (expected_user.getPassword().equals(password)) {
                request.getSession().setAttribute("dni", expected_user.getDni());
                return "redirect:/home";
            }
            // Add flash message here
            return "login";
        } catch (Exception e) {
            // Add flash message here
            return "login";
        }
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
