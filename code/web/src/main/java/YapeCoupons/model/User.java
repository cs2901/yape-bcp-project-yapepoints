package YapeCoupons.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

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
    private String bank_account;
    private String business_name;
    private String business_description;
    private String business_map_url;

    public User () {}

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

    public String getBank_account() { return bank_account; }
    public void setBank_account(String bank_account) { this.bank_account = bank_account; }

    public String getBusiness_name() { return business_name; }
    public void setBusiness_name(String business_name) { this.business_name = business_name; }

    public String getBusiness_description() {  return business_description; }
    public void setBusiness_description(String business_description) { this.business_description = business_description;  }

    public String getBusiness_map_url() { return business_map_url; }
    public void setBusiness_map_url(String business_map_url) { this.business_map_url = business_map_url; }

    // For debugging
    @Override
    public String toString () {
        return String.format(
                "User[id=%s, given_name=%s, dni=%s, email=%s, password=%s, bank_account=%s, business_name=%s]",
                _id, given_name, dni, email, password, bank_account, business_name
        );
    }
}
