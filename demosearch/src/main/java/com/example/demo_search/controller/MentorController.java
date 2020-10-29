package com.example.demo_search.controller;


import com.example.demo_search.Model.Technology;
import com.example.demo_search.Model.User;
import com.example.demo_search.bean.Mentor;
import com.example.demo_search.bean.MentorCalender;
import com.example.demo_search.bean.MentorSkills;
import com.example.demo_search.service.MentorCalenderService;
import com.example.demo_search.service.MentorService;
import com.example.demo_search.service.MentorSkillsService;
//import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
//import java.util.logging.Logger;

@RestController
@RequestMapping(value={"/search"})
public class MentorController {
	@Autowired
	MentorService mentorService;

	@Autowired
	MentorSkillsService mentorSkillsService;

	@Autowired
	MentorCalenderService mentorCalenderService;

	private final Logger Log = LoggerFactory.getLogger(getClass());

	
    @GetMapping(value = "/{mentor_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mentor> getMentorById(@PathVariable("id") long id) {
        System.out.println("Fetching Mentor with id " + id);
        Mentor mentor = mentorService.findById(id);
        if (mentor == null) {
        	Log.error("given mentor with "+id+"is not present");
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
        User u=result.getBody();
        if(u==null)
		{
			Log.warn("fetching the user which is not present");
		}

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
	     Log.info("creating mentor with mentorid"+mentor.getId());
	     mentorService.createMentor(mentor);
	     HttpHeaders headers = new HttpHeaders();
	     headers.setLocation(ucBuilder.path("/create_mentor/{id}").buildAndExpand(mentor.getId()).toUri());
	     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }

	 @GetMapping(value="/get_mentor", headers="Accept=application/json")
	 public List<Mentor> getAllMentor() {
	  List<Mentor> tasks=mentorService.getMentor();
	  Log.info("The list of all mentors persent in mentor table is"+tasks);
	  return tasks;
	
	 }

	@PutMapping(value="/update_mentor", headers="Accept=application/json")
	public ResponseEntity<String> updateMentor(@RequestBody Mentor currentMentor)
	{
		System.out.println("sd");
	Mentor mentor = mentorService.findById(currentMentor.getId());
	if (mentor==null) {
	    Log.warn("mentor with "+currentMentor.getId()+"is not present");
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	mentorService.update(currentMentor, currentMentor.getId());
	return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{mentor_id}/{id}", headers ="Accept=application/json")
	public ResponseEntity<Mentor> deleteMentor(@PathVariable("id") long id){
		Mentor mentor = mentorService.findById(id);
		if (mentor == null) {
		    Log.warn("deleting a mentor which is not present");
			return new ResponseEntity<Mentor>(HttpStatus.NOT_FOUND);
		}
		mentorService.deleteMentorById(id);
		Log.info("mentor with id "+id+ "is deleted ");
		return new ResponseEntity<Mentor>(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping(value="/{mentor_id}", headers="Accept=application/json")
	public ResponseEntity<Mentor> updateUserPartially(@PathVariable("id") long id, @RequestBody Mentor currentMentor){
		Mentor mentor = mentorService.findById(id);
		if(mentor ==null){
		    Log.error("mentor with given id is not present");
			return new ResponseEntity<Mentor>(HttpStatus.NOT_FOUND);
		}
		Mentor usr =	mentorService.updatePartially(currentMentor, id);
		Log.info("updating mentor");
		return new ResponseEntity<Mentor>(usr, HttpStatus.OK);
	}
	//get all mentor skills using mentor_id
    @GetMapping(value="/get/{id}", headers="Accept=application/json")
    public List<MentorSkills> getByMid(@PathVariable long id) {
        List<MentorSkills> lm=mentorSkillsService.findByMid(id);
        return lm;

    }

//Rest end Point to add mentor calender

	@PostMapping(value="/addmentorcalender",headers="Accept=application/json")
	public ResponseEntity<Void> addMentorCalender(@RequestBody MentorCalender mentorCalender, UriComponentsBuilder ucBuilder){
	    Log.info("updating mentor calender");
		System.out.println("Creating MentorCalender with id  "+mentorCalender.getId());
		mentorCalenderService.createMentorCalender(mentorCalender);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/create_mentor/{id}").buildAndExpand(mentorCalender.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	//Rest end point to get mentor calender


    @GetMapping(value="/getmentorcalender", headers="Accept=application/json")
    public List<MentorCalender> getAllMentorCalender() {
		Log.info("fetching mentor calender");
        List<MentorCalender> tasks=mentorCalenderService.getMentorCalender();
        return tasks;

    }
}
