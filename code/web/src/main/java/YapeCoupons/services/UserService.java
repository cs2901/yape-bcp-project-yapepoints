package YapeCoupons.services;

import YapeCoupons.model.User;
import YapeCoupons.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.springframework.util.Assert.*;

@Service
public class UserService {
    @Autowired
    private UserRepository users;


    // TO DO
    // ENCRYPT PASSWORD
    public void createUser (String given_name, String family_name, String dni, String password) throws Exception {
        try {
            if (findByDni(dni) != null) {
                throw new Exception("DNI en uso");
            }
            User user = new User();
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

    public void setBusiness (String dni, String business_name, String business_description, String business_map_url) throws Exception {
        try {
            User user = findByDni(dni);
            user.setBusiness_name(business_name);
            user.setBusiness_description(business_description);
            user.setBusiness_map_url(business_map_url);
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
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private User findByDni (String dni) throws Exception {
        try {
            return users.findByDni(dni);
        } catch (Exception e) {
            throw new Exception("Error intentando obtener usuario con dni " + dni);
        }
    }

    // For testing
    public List<User> findAll () { return users.findAll(); }
    public void deleteAll () { users.deleteAll(); }
}
