package com.example.demo_search.controller;


import com.example.demo_search.Model.Technology;
import com.example.demo_search.Model.User;
import com.example.demo_search.bean.Mentor;
import com.example.demo_search.bean.MentorCalender;
import com.example.demo_search.bean.MentorSkills;
import com.example.demo_search.service.MentorCalenderService;
import com.example.demo_search.service.MentorService;
import com.example.demo_search.service.MentorSkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value={"/search"})
public class MentorController {
	@Autowired
	MentorService mentorService;

	@Autowired
	MentorSkillsService mentorSkillsService;

	@Autowired
	MentorCalenderService mentorCalenderService;
	
    @GetMapping(value = "/{mentor_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mentor> getMentorById(@PathVariable("id") long id) {
        System.out.println("Fetching Mentor with id " + id);
        Mentor mentor = mentorService.findById(id);
        if (mentor == null) {
            return new ResponseEntity<Mentor>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Mentor>(mentor, HttpStatus.OK);
    }

    //return first name last name from user by mid

    @GetMapping("/details/{mid}")
    public User getUser(@PathVariable long mid) {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:8987/user/" + mid;
        URI uri = null;
        try {
            uri = new URI(baseUrl);
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ResponseEntity <User> result = restTemplate.getForEntity(uri, User.class);

        //ResponseEntity <List> result = restTemplate.getForEntity(uri, List.class);
        //List <Technology> tech1=result.getBody();
        User u=result.getBody();

        //Verify request succeed
        System.out.println("Status code: " + result.getStatusCodeValue());
        //System.out.println("class type is :"+tech1.getClass().getName());
        System.out.println("result body class is " + result.getBody().getClass().getName());
        System.out.println("result: " + result.getBody());


        return u;
    }

	@PostMapping(value="/create_mentor",headers="Accept=application/json")
	 public ResponseEntity<Void> createMentor(@RequestBody Mentor mentor, UriComponentsBuilder ucBuilder){
	     System.out.println("Creating Mentor with id  "+mentor.getId());
	     mentorService.createMentor(mentor);
	     HttpHeaders headers = new HttpHeaders();
	     headers.setLocation(ucBuilder.path("/create_mentor/{id}").buildAndExpand(mentor.getId()).toUri());
	     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }

	 @GetMapping(value="/get_mentor", headers="Accept=application/json")
	 public List<Mentor> getAllMentor() {
	  List<Mentor> tasks=mentorService.getMentor();
	  return tasks;
	
	 }
	 
	 /*@GetMapping(value="/getnamecountry/{name}/{country}", headers="Accept=application/json")
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

	@PutMapping(value="/update_mentor", headers="Accept=application/json")
	public ResponseEntity<String> updateMentor(@RequestBody Mentor currentMentor)
	{
		System.out.println("sd");
	Mentor mentor = mentorService.findById(currentMentor.getId());
	if (mentor==null) {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	mentorService.update(currentMentor, currentMentor.getId());
	return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{mentor_id}", headers ="Accept=application/json")
	public ResponseEntity<Mentor> deleteMentor(@PathVariable("id") long id){
		Mentor mentor = mentorService.findById(id);
		if (mentor == null) {
			return new ResponseEntity<Mentor>(HttpStatus.NOT_FOUND);
		}
		mentorService.deleteMentorById(id);
		return new ResponseEntity<Mentor>(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping(value="/{mentor_id}", headers="Accept=application/json")
	public ResponseEntity<Mentor> updateUserPartially(@PathVariable("id") long id, @RequestBody Mentor currentMentor){
		Mentor mentor = mentorService.findById(id);
		if(mentor ==null){
			return new ResponseEntity<Mentor>(HttpStatus.NOT_FOUND);
		}
		Mentor usr =	mentorService.updatePartially(currentMentor, id);
		return new ResponseEntity<Mentor>(usr, HttpStatus.OK);
	}
	//get all mentor skills using mentor_id
    @GetMapping(value="/get/{id}", headers="Accept=application/json")
    public List<MentorSkills> getByMid(@PathVariable long id) {
        List<MentorSkills> lm=mentorSkillsService.findByMid(id);
        return lm;

    }
}
