package YapeCoupons.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CouponRepository extends MongoRepository<Coupon, String> {
    // TODO : Rename method: Use lowerCamelCase
    public Coupon findBy_id (String id_);
}