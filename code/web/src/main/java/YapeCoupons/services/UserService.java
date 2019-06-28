package YapeCoupons.services;

import YapeCoupons.model.User;
import YapeCoupons.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository users;

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

    public void setBusiness (String dni, String business_name, String business_description,
                             String business_region,
                             String business_celular, String business_ruc,
                             String business_latitud, String business_longitud
    ) throws Exception {
        try {
            User user = findByDni(dni);
            user.setBusiness_name(business_name);
            user.setBusiness_description(business_description);
            user.setBusiness_address("https://www.google.com/maps/search/?api=1&query=" + business_latitud + "," + business_longitud);
            user.setBusiness_region(business_region);
            user.setBusiness_celular(business_celular);
            user.setBusiness_ruc(business_ruc);
            user.setBusiness_latitud(business_latitud);
            user.setBusiness_longitud(business_longitud);
            users.save(user);
        } catch (Exception e) {
            throw new Exception("Error vinculando la información del negocio");
        }
    }

    public boolean filledBusinessInformation (String dni) throws Exception {
        try {
            User user = findByDni(dni);
            if (user == null) return false;
            if (user.getBusiness_name() == null) return false;
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

    public void updateUserInformation (String dni, String given_name, String family_name, String email) throws Exception {
         try {
             User user = findByDni(dni);
             user.setGiven_name(given_name);
             user.setFamily_name(family_name);
             user.setEmail(email);
             users.save(user);
        } catch (Exception e) {
            throw new Exception("Error actualizando la información");
        }
    }

    public void setPassword (String dni, String password) throws Exception {
        try {
             User user = findByDni(dni);
             user.setPassword(password);
             users.save(user);
        } catch (Exception e) {
            throw new Exception("Error cambiando contraseña");
        }
    }

}