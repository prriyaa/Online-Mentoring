package com.example.demo_search.controller;


import com.example.demo_search.Model.Technology;
import com.example.demo_search.Model.User;
import com.example.demo_search.bean.Mentor;
import com.example.demo_search.bean.MentorSkills;
import com.example.demo_search.service.MentorCalenderService;
import com.example.demo_search.service.MentorService;
import com.example.demo_search.service.MentorSkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value={"/search_skill"})
public class MentorSkillsController {
	@Autowired
	MentorService mentorService;

	@Autowired
	MentorSkillsService mentorSkillsService;

	@Autowired
	MentorCalenderService mentorCalenderService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MentorSkills> getMentorSkillsById(@PathVariable("id") long id) {
		System.out.println("Fetching MentorSkills with id " + id);
		MentorSkills mentorSkills = mentorSkillsService.findById(id);
		if (mentorSkills == null) {
			return new ResponseEntity<MentorSkills>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MentorSkills>(mentorSkills, HttpStatus.OK);
	}

	//get all mentor skills using mentor id
	@GetMapping(value = "/getmid/{mid}", headers = "Accept=application/json")
	public List<MentorSkills> getByMid(@PathVariable long mid) {
		List<MentorSkills> lm = mentorSkillsService.findByMid(mid);
		return lm;

	}

	@PostMapping(value = "/create_mentorskills", headers = "Accept=application/json")
	public ResponseEntity<Void> createMentorSkills(@RequestBody MentorSkills mentorSkills, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating MentorSkills with id  " + mentorSkills.getId());
		mentorSkillsService.createMentorSkills(mentorSkills);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/create_mentorskills/{id}").buildAndExpand(mentorSkills.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	//if a mentor wants to add a skill in a table
	@PostMapping(value = "/addskill/{mid}", headers = "Accept=application/json")
	public ResponseEntity<Void> addMentorSkills(@RequestBody MentorSkills mentorSkills, UriComponentsBuilder ucBuilder,@PathVariable long mid) {
		mentorSkills.setMid(mid);
		System.out.println("Creating MentorSkills with id  " + mentorSkills.getId());
		mentorSkillsService.createMentorSkills(mentorSkills);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/create_mentorskills/{id}").buildAndExpand(mentorSkills.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/get_mentorskills", headers = "Accept=application/json")
	public List<MentorSkills> getAllMentorSkills() {
		List<MentorSkills> tasks = mentorSkillsService.getMentorSkills();
		return tasks;

	}

	@GetMapping(value = "/get_skill/{sid}", headers = "Accept=application/json")
	public List<MentorSkills> getBySkillid(@PathVariable long sid) {
		List<MentorSkills> lm = mentorSkillsService.findBySid(sid);
		return lm;

	}

	//get mentor list from skill id
	@GetMapping("/get_mentor_list/{name}")
	public ArrayList<ArrayList<MentorSkills>> met2(@PathVariable String name) {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:8983/technology/getname/" + name;
		URI uri = null;
		try {
			uri = new URI(baseUrl);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResponseEntity<Technology[]> result = restTemplate.getForEntity(uri, Technology[].class);

		System.out.println("Status code: " + result.getStatusCodeValue());
		//System.out.println("class type is :"+tech1.getClass().getName());
		System.out.println("result body class is " + result.getBody().getClass().getName());
		System.out.println("result: " + result.getBody());

		ArrayList<ArrayList<MentorSkills>> list = new ArrayList<ArrayList<MentorSkills>>();
		for (Technology t : result.getBody()) {
			//Technology tech=( Technology) t;
			long skill_id = t.getId();
			ArrayList<MentorSkills> mentor = mentorSkillsService.findBySid(skill_id);
			list.add(mentor);
		}
		return list;
	}


	 
	 /*@GetMapping(value="/getnamecountry/{name}/{country}", headers="Accept=application/json")
	 public List<User> getByNameAndCountry(@PathVariable String name, @PathVariable String country) {
	  List<User> tasks=userService.findByNameAndCountry(name, country);
	  return tasks;
	
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

	@PutMapping(value = "/update_mentorskills", headers = "Accept=application/json")
	public ResponseEntity<String> updateMentorSkills(@RequestBody MentorSkills currentMentorSkills) {
		System.out.println("sd");
		MentorSkills mentorSkills = mentorSkillsService.findById(currentMentorSkills.getId());
		if (mentorSkills == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		mentorSkillsService.update(currentMentorSkills, currentMentorSkills.getId());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{mentorskills_id}", headers = "Accept=application/json")
	public ResponseEntity<MentorSkills> deleteMentorSkills(@PathVariable("id") long id) {
		MentorSkills mentorSkills = mentorSkillsService.findById(id);
		if (mentorSkills == null) {
			return new ResponseEntity<MentorSkills>(HttpStatus.NOT_FOUND);
		}
		mentorSkillsService.deleteMentorSkillsById(id);
		return new ResponseEntity<MentorSkills>(HttpStatus.NO_CONTENT);
	}

	@PatchMapping(value = "/{mentorskills_id}", headers = "Accept=application/json")
	public ResponseEntity<MentorSkills> updateUserPartially(@PathVariable("id") long id, @RequestBody MentorSkills currentMentorSkills) {
		MentorSkills mentorSkills = mentorSkillsService.findById(id);
		if (mentorSkills == null) {
			return new ResponseEntity<MentorSkills>(HttpStatus.NOT_FOUND);
		}
		MentorSkills usr = mentorSkillsService.updatePartially(currentMentorSkills, id);
		return new ResponseEntity<MentorSkills>(usr, HttpStatus.OK);
	}

	//get all fields from technology table by using search query using skill_name
	@GetMapping("/getsearch/{name}")
	public Technology[] met1(@PathVariable String name) {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:8983/technology/getname/" + name;
		URI uri = null;
		try {
			uri = new URI(baseUrl);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResponseEntity<Technology[]> result = restTemplate.getForEntity(uri, Technology[].class);
		System.out.println("Status code: " + result.getStatusCodeValue());
		//System.out.println("class type is :"+tech1.getClass().getName());
		System.out.println("result body class is " + result.getBody().getClass().getName());
		System.out.println("result: " + result.getBody());
		for (Technology t : result.getBody()) {
			//Technology tech=( Technology) t;
			long skill_id = t.getId();
			System.out.println("skill id for" + t.getName() + "skill is : " + skill_id);
		}
		return result.getBody();
	}

	//get first_name last_name from user microservice
	@GetMapping("/get_mentor_list9/{name}")
	public ArrayList<User> met299(@PathVariable String name) {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:8983/technology/getname/" + name;
		URI uri = null;
		try {
			uri = new URI(baseUrl);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseEntity<Technology[]> result = restTemplate.getForEntity(uri, Technology[].class);
		ArrayList<ArrayList<MentorSkills>> list = new ArrayList<ArrayList<MentorSkills>>();
		for (Technology t : result.getBody()) {
			long skill_id = t.getId();
			ArrayList<MentorSkills> mentor = mentorSkillsService.findBySid(skill_id);
			list.add(mentor);
		}
        ArrayList<User> list1 = new ArrayList<>();
		return list1;
	}
}

