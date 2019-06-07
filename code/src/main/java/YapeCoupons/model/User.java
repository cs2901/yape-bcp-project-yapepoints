package YapeCoupons.model;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    public String id;

    public String givenName;
    public String familyName;

    public User () {}

    public User (String givenName, String familyName) {
        this.givenName = givenName;
        this.familyName = familyName;
    }

    @Override
    public String toString () {
        return String.format(
                "User[id=%s, givenName=%s, familyName=%s]",
                id, givenName, familyName
        );
    }
}
