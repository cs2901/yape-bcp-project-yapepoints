package YapeCoupons.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
    public User findByDni(String dni);
    public User findByEmail (String email);
}
