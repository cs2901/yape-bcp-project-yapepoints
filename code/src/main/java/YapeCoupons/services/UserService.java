package YapeCoupons.services;

import YapeCoupons.model.User;
import YapeCoupons.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository users;

    public void createUser (String given_name, String family_name, String dni, String password) {
        User user = new User();
        user.setGiven_name(given_name);
        user.setFamily_name(family_name);
        user.setDni(dni);
        user.setPassword(password);
        users.save(user);
    }

    public User findByDni (String dni) throws Exception {
        try {
            return users.findByDni(dni);
        } catch (Exception e) {
            throw new Exception("El DNI ya se encuentra en uso");
        }
    }

    public List<User> findAll () { return users.findAll(); }
}
