package YapeCoupons.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class User {
    @Id
    private ObjectId _id;

    private String given_name;
    private String family_name;
    @Indexed(unique = true)
    private String dni;
    private String password;

    public User () {}

    public String getGiven_name() { return given_name;  }
    public void setGiven_name(String given_name) { this.given_name = given_name; }

    public String getFamily_name() { return family_name; }
    public void setFamily_name(String family_name) { this.family_name = family_name; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString () {
        return String.format(
                "User[id=%s, given_name=%s, family_name=%s, dni=%s, password=%s]",
                _id, given_name, family_name, dni, password
        );
    }
}
