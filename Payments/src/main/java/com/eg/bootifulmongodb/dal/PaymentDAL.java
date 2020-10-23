package com.eg.bootifulmongodb.dal;

import java.util.List;

import com.eg.bootifulmongodb.model.Payment;

public interface PaymentDAL {

	List<Payment> getAllPayments();

	Payment getPaymentById(long id);

	Payment addNewPayment(Payment payment);


	//String getUserAddress(String userId);

	//String getPaymentSetting(long id, String key);

	//String addUserSetting(String userId, String key, String value);
}