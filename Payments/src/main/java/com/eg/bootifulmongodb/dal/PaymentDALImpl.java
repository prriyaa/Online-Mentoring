package com.eg.bootifulmongodb.dal;

import com.eg.bootifulmongodb.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentDALImpl implements PaymentDAL {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Payment> getAllPayments() {
		return mongoTemplate.findAll(Payment.class);
	}

	@Override
	public Payment getPaymentById(long id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		return mongoTemplate.findOne(query, Payment.class);
	}

	@Override
	public Payment addNewPayment(Payment payment) {
		mongoTemplate.save(payment);
		// Now, user object will contain the ID as well
		return payment;
	}


	/*@Override
	public Object getPaymentSetting(long id, String key) {
		Query query = new Query();
		query.fields().include("userSettings");
		query.addCriteria(Criteria.where("id").is(id).andOperator(Criteria.where("userSettings." + key).exists(true)));
		Payment payment = mongoTemplate.findOne(query, Payment.class);
		return payment != null ? payment.getAmount(): "Not found.";
	}*/
	/*public String getUserAddress(String userId)
	{
		Query query=new Query();
		query.fields().include("address");
		query.addCriteria(Criteria.where("userId").is(userId));
		User user = mongoTemplate.findOne(query, User.class);
		return user.getAddress();
	}

	@Override
	public String addUserSetting(String userId, String key, String value) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(userId));
		User user = mongoTemplate.findOne(query, User.class);
		if (user != null) {
			user.getUserSettings().put(key, value);
			mongoTemplate.save(user);
			return "Key added.";
		} else {
			return "User not found.";
		}
	}*/
}
