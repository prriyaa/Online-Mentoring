package com.eg.bootifulmongodb.dal;

import com.eg.bootifulmongodb.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
    /*@Query("{name : ?0}")
    public User findByNameQuery(String name);*/
}
