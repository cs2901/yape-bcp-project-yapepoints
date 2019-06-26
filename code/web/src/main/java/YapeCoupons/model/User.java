package YapeCoupons.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "user")
public class User {
    @Id
    private ObjectId _id;

    @Indexed(unique = true)
    private String dni;

    @Indexed(unique = true)
    private String email;

    private String given_name;
    private String family_name;
    private String password;
    private String business_name;
    private String business_description;
    private String business_address;
    private String business_region;
    private String business_celular;
    private String business_ruc;

    private boolean enabled;
    @DBRef
    private Set<Role> roles;

    public User () {}

    // TODO : Rename methods: Use lowerCamelCase
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getGiven_name() { return given_name;  }
    public void setGiven_name(String given_name) { this.given_name = given_name; }

    public String getFamily_name() { return family_name; }
    public void setFamily_name(String family_name) { this.family_name = family_name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getBusiness_name() { return business_name; }
    public void setBusiness_name(String business_name) { this.business_name = business_name; }

    public String getBusiness_description() {  return business_description; }
    public void setBusiness_description(String business_description) { this.business_description = business_description;  }

    public String getBusiness_address() { return business_address; }
    public void setBusiness_address(String business_address) { this.business_address = business_address; }

    public String getBusiness_region() { return business_region; }
    public void setBusiness_region(String business_region) { this.business_region = business_region; }

    public String getBusiness_celular() { return business_celular; }
    public void setBusiness_celular(String business_celular) { this.business_celular = business_celular; }

    public String getBusiness_ruc() { return business_ruc; }
    public void setBusiness_ruc(String business_ruc) { this.business_ruc = business_ruc; }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
