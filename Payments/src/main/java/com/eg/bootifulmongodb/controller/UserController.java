package com.eg.bootifulmongodb.controller;

import java.util.List;

import com.eg.bootifulmongodb.dal.PaymentRepository;
import com.eg.bootifulmongodb.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eg.bootifulmongodb.dal.PaymentDAL;
import com.eg.bootifulmongodb.dal.UserRepository;
import com.eg.bootifulmongodb.model.User;

@RestController
@RequestMapping(value = "/")
public class UserController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final PaymentRepository paymentRepository;

	private final PaymentDAL paymentDAL;

	public UserController(PaymentRepository paymentRepository, PaymentDAL paymentDAL) {
		this.paymentRepository = paymentRepository;
		this.paymentDAL = paymentDAL;
	}

	/*
	 http://localhost:8102/user/create
	 {
  "name" : "Ramu",
  "userSettings" : {
    "bike" : "pulsar"
  }
}
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Payment addNewPayment(@RequestBody Payment payment) {
		LOG.info("Saving user.");
		return paymentRepository.save(payment);
	}

	/*
	 http://localhost:8102/user/
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Payment> getAllPayment() {
		LOG.info("Getting all users.");
		return paymentRepository.findAll();
	}
	@RequestMapping(value = "/customquery/{name}", method = RequestMethod.GET)
	public User getUser9(@PathVariable String name) {
		LOG.info("Getting user with ID: {}.", name);
		return userRepository.findByNameQuery(name);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable String userId) {
		LOG.info("Getting user with ID: {}.", userId);
		return userRepository.findOne(userId);
	}

	// @RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
	// public Object getAllUserSettings(@PathVariable String userId) {
	// User user = userRepository.findOne(userId);
	// if (user != null) {
	// return user.getUserSettings();
	// } else {
	// return "User not found.";
	// }
	// }

	@RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
	public Object getAllUserSettings(@PathVariable String userId) {
		User user = userRepository.findOne(userId);
		if (user != null) {
			return userDAL.getAllUserSettings(userId);
		} else {
			return "User not found.";
		}
	}

	@RequestMapping(value = "get/{name}/{address}", method = RequestMethod.GET)
	public Object getAllUsers(@PathVariable String name,@PathVariable String address) {
		User user1 = userRepository.findByNameQuery(name);
		if (user1!=null && user1.getAddress().equals(address)) {
            System.out.println("address is: "+user1.getAddress());
            System.out.println("address passed from command line is: "+address);
            return user1;
		}
		else {
			return "User not found.";
		}
	}

	// @RequestMapping(value = "/settings/{userId}/{key}", method =
	// RequestMethod.GET)
	// public String getUserSetting(@PathVariable String userId, @PathVariable
	// String key) {
	// //User user = userRepository.findOne(userId);
	// String setting = userDAL.getUserSetting(userId, key);
	// LOG.info("Setting = "+setting);
	// if (setting != null) {
	// return setting;
	// } else {
	// return "Setting not found.";
	// }
	// }

	@RequestMapping(value = "/settings/{userId}/{key}", method = RequestMethod.GET)
	public String getUserSetting(@PathVariable String userId, @PathVariable String key) {
		return userDAL.getUserSetting(userId, key);
	}


	@RequestMapping(value = "/address/{userId}", method = RequestMethod.GET)
	public String getUserAddress(@PathVariable String userId) {
		return userDAL.getUserAddress(userId);
	}

	@RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
	public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
		User user = userRepository.findOne(userId);
		if (user != null) {
			user.getUserSettings().put(key, value);
			userRepository.save(user);
			return "Key added";
		} else {
			return "User not found.";
		}
	}
}