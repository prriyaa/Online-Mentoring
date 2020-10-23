package com.example_online.mentoring.demo_technology.controller;



import com.example_online.mentoring.demo_technology.bean.Technology;
import com.example_online.mentoring.demo_technology.service.TechnologyService;
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
@RequestMapping(value={"/technology"})
public class TechnologyController {
	@Autowired
	TechnologyService technologyService;

	@Value("${pqr.xyz}")
	String str1;

	@Value("${abcd.efgh}")
	String str;


	@GetMapping
	public ResponseEntity<List<Technology>> getAllTechnology(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy)
	{
		List<Technology> list = technologyService.getAllTechnology(pageNo, pageSize, sortBy);
		System.out.println("String is: "+str);
		System.out.println("Message from company: "+str1);

		return new ResponseEntity<List<Technology>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Technology> getSkill(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Technology technology = technologyService.findById(id);
        if (technology == null) {
            return new ResponseEntity<Technology>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Technology>(technology, HttpStatus.OK);
    }
    
	 @PostMapping(value="/create",headers="Accept=application/json")
	 public ResponseEntity<Void> createTechnology(@RequestBody Technology technology, UriComponentsBuilder ucBuilder){
	     System.out.println("Creating User "+technology.getName());
	     technologyService.createTechnology(technology);
	     HttpHeaders headers = new HttpHeaders();
	     headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(technology.getId()).toUri());
	     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }

	 @GetMapping(value="/get", headers="Accept=application/json")
	 public List<Technology> getAllTechnology() {
	  List<Technology> tasks=technologyService.getTechnology();
	  return tasks;
	
	 }

    @GetMapping(value="/getname/{name}", headers="Accept=application/json")
    public List<Technology> searchSkill(@PathVariable String name) {
        List<Technology> lm=technologyService.findByName(name);
        return lm;

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
	public ResponseEntity<String> updateTechnology(@RequestBody Technology currentTechnology)
	{
		System.out.println("sd");
	Technology technology = technologyService.findById(currentTechnology.getId());
	if (technology==null) {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	technologyService.update(currentTechnology, currentTechnology.getId());
	return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}", headers ="Accept=application/json")
	public ResponseEntity<Technology> deleteTechnology(@PathVariable("id") long id){
		Technology technology = technologyService.findById(id);
		if (technology == null) {
			return new ResponseEntity<Technology>(HttpStatus.NOT_FOUND);
		}
		technologyService.deleteTechnologyById(id);
		return new ResponseEntity<Technology>(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping(value="/{id}", headers="Accept=application/json")
	public ResponseEntity<Technology> updateTechnologyPartially(@PathVariable("id") long id, @RequestBody Technology currentTechnology){
		Technology technology = technologyService.findById(id);
		if(technology ==null){
			return new ResponseEntity<Technology>(HttpStatus.NOT_FOUND);
		}
		Technology tech =	technologyService.updatePartially(currentTechnology, id);
		return new ResponseEntity<Technology>(tech, HttpStatus.OK);
	}
}