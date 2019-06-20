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

    // TODO : ENCRYPT PASSWORD
    public void createUser (String email, String given_name, String family_name, String dni, String password) throws Exception {
        try {
            if (findByDni(dni) != null) {
                throw new Exception("DNI en uso");
            }
            if (findByEmail(email) != null) {
                throw new Exception("Email en uso");
            }
            User user = new User();
            user.setEmail(email);
            user.setGiven_name(given_name);
            user.setFamily_name(family_name);
            user.setDni(dni);
            user.setPassword(password);
            users.save(user);
        } catch (Exception e) {
            throw new Exception("Error creando cuenta. " + e.getMessage());
        }
    }

    public void setBankInformation (String dni, String bank_account) throws Exception {
        try {
            User user = findByDni(dni);
            user.setBank_account(bank_account);
            users.save(user);
        } catch (Exception e) {
            throw new Exception("Error vinculando la información bancaria");
        }
    }

    public void setBusiness (String dni, String business_name, String business_description,
                             String business_map_url, String business_ruc) throws Exception {
        try {
            User user = findByDni(dni);
            user.setBusiness_name(business_name);
            user.setBusiness_description(business_description);
            user.setBusiness_map_url(business_map_url);
            user.setBusiness_ruc(business_ruc);
            users.save(user);
        } catch (Exception e) {
            throw new Exception("Error vinculando la información del negocio");
        }
    }

    public boolean filledBankAccountInformation (String dni) throws Exception {
        try {
            User user = findByDni(dni);
            if (user == null || user.getBank_account() == null) return false;
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean filledBusinessInformation (String dni) throws Exception {
        try {
            User user = findByDni(dni);
            if (user == null) return false;
            if (user.getBusiness_name() == null) return false;
            if (user.getBusiness_description() == null) return false;
            if (user.getBusiness_map_url() == null) return false;
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public User findByDni (String dni) throws Exception {
        try {
            return users.findByDni(dni);
        } catch (Exception e) {
            throw new Exception("Error intentando obtener usuario con dni " + dni);
        }
    }

    public User findByEmail (String email) throws Exception {
        try {
            return users.findByEmail(email);
        } catch (Exception e) {
            throw new Exception("Error intentando obtener usuario con email " + email);
        }
    }

    public void setPassword (String dni, String password) throws Exception {
        try {
             User user = findByDni(dni);
             user.setPassword(password);
        } catch (Exception e) {
            throw new Exception("Error cambiando contraseña");
        }
    }

    // For testing
    public List<User> findAll () { return users.findAll(); }
    public void deleteAll () { users.deleteAll(); }
}