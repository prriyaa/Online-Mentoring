package com.example.demo_user.controller;



import com.example.demo_user.bean.User;
import com.example.demo_user.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value={"/user"})
public class UsersController {
	@Autowired
	UsersService usersService;

	@Value("${pqr.xyz}")
	String str1;

	@Value("${abcd.efgh}")
	String str;


	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy)
	{
		List<User> list = usersService.getAllUser(pageNo, pageSize, sortBy);
		System.out.println("String is: "+str);
		System.out.println("Message from company: "+str1);

		return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        User user = usersService.findById(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
	 @PostMapping(value="/create_user",headers="Accept=application/json")
	 public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder){
	     System.out.println("Creating User "+user.getFirstname());
	     usersService.createUser(user);
	     HttpHeaders headers = new HttpHeaders();
	     headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
	     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }

	 @GetMapping(value="/get", headers="Accept=application/json")
	 public List<User> getAllUser() {
	  List<User> tasks=usersService.getUser();
	  return tasks;
	
	 }

	 
	/* @GetMapping(value="/getnamecountry/{name}/{country}", headers="Accept=application/json")
	 public List<User> getByNameAndCountry(@PathVariable String name, @PathVariable String country) {
	  List<User> tasks=userService.findByNameAndCountry(name, country);
	  return tasks;
	
	 }
	@GetMapping(value="/getname/{name}", headers="Accept=application/json")
	public List<User> getByName(@PathVariable String name) {
		List<User> lm=userService.findByName(name);
		return lm;

	}
    @GetMapping(value="/getabc/{country}", headers="Accept=application/json")
    public List<User> getAbc(@PathVariable String country) {
        List<User> lm=userService.Abc("india");
        return lm;

    }
    @GetMapping(value="/getxyz", headers="Accept=application/json")
    public List<Object[]> getXyz() {
	     return userService.findXyz();
    }*/

	@PutMapping(value="/update", headers="Accept=application/json")
	public ResponseEntity<String> updateUser(@RequestBody User currentUser)
	{
		System.out.println("sd");
	User users = usersService.findById(currentUser.getId());
	if (users==null) {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	usersService.update(currentUser, currentUser.getId());
	return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}", headers ="Accept=application/json")
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id){
		User user = usersService.findById(id);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		usersService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping(value="/{id}", headers="Accept=application/json")
	public ResponseEntity<User> updateUserPartially(@PathVariable("id") long id, @RequestBody User currentUser){
		User user = usersService.findById(id);
		if(user ==null){
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		User user1 =	usersService.updatePartially(currentUser, id);
		return new ResponseEntity<User>(user1, HttpStatus.OK);
	}
}
