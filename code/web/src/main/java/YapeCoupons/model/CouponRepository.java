package YapeCoupons.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CouponRepository extends MongoRepository<Coupon, String> {
    public Coupon findBy_id (String id_);
}