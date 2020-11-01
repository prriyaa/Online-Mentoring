package com.example_online.mentoring.demo_technology.controller;



import com.example_online.mentoring.demo_technology.bean.Technology;
import com.example_online.mentoring.demo_technology.service.TechnologyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping(value={"/technology"})
public class TechnologyController {
	@Autowired
	TechnologyService technologyService;

    private final Logger Log = LoggerFactory.getLogger(getClass());

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

		Log.info("fetching different pages of technology");
		return new ResponseEntity<List<Technology>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Technology> getSkill(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Technology technology = technologyService.findById(id);
        if (technology == null)
        {
            Log.error("trying to find technology with no id");
            return new ResponseEntity<Technology>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Technology>(technology, HttpStatus.OK);
    }
    
	 @PostMapping(value="/create",headers="Accept=application/json")
	 public ResponseEntity<Void> createTechnology(@RequestBody Technology technology,@RequestHeader ("Authorization") String header){
         RestTemplate restTemplate=new RestTemplate();

         final String baseUrl121 = "http://localhost:9879/api/test/admin";
         URI uri121 = null;
         try {
             uri121 = new URI(baseUrl121);
         } catch (URISyntaxException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         HttpHeaders headers = new HttpHeaders();
         headers.set("Authorization", header);
         HttpEntity entity = new HttpEntity(headers);
         ResponseEntity<String> res = restTemplate.exchange(
                 uri121, HttpMethod.GET, entity,
                 String.class);
         String role= res.getBody().toString();
         Log.info("Your role is : "+role);

         String role1="Admin ";

         HttpHeaders header1 = new HttpHeaders();
         if(role.equals(role1)) {
             Log.info("Creating Technology "+technology.getName());
             //System.out.println("Creating User "+technology.getName());
             technologyService.createTechnology(technology);
             return new ResponseEntity<Void>(header1, HttpStatus.CREATED);
         }
         else
         {
             Log.info("you can't get training details because your role is : " +role);
             return new ResponseEntity<Void>(header1, HttpStatus.NOT_FOUND);
         }

	 }


	 @GetMapping(value="/get", headers="Accept=application/json")
	 public List<Technology> getAllTechnology() {
	     Log.info("returning all technologies");
	  List<Technology> tasks=technologyService.getTechnology();
	  return tasks;
	
	 }

    @GetMapping(value="/getname/{name}", headers="Accept=application/json")
    public List<Technology> searchSkill(@PathVariable String name) {
		//String encodedString = Base64.getEncoder().encodeToString(name.getBytes());

        Log.info("fetching information of skill "+name);
		List<Technology> lm=technologyService.findByName(name);
        return lm;

    }


	@PutMapping(value="/update", headers="Accept=application/json")
	public ResponseEntity<String> updateTechnology(@RequestBody Technology currentTechnology)
	{
		//System.out.println("sd");
	Technology technology = technologyService.findById(currentTechnology.getId());
	if (technology==null) {
	    Log.warn("updating null technology!!!");
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	technologyService.update(currentTechnology, currentTechnology.getId());
	Log.info("updating technology with id"+currentTechnology.getId());
	return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}", headers ="Accept=application/json")
	public ResponseEntity<Technology> deleteTechnology(@PathVariable("id") long id){
		Technology technology = technologyService.findById(id);
		if (technology == null) {
		    Log.error("delete the data which is already deleted or not exist!!!");
			return new ResponseEntity<Technology>(HttpStatus.NOT_FOUND);
		}
		Log.info("Deleting technology with id"+id);
		technologyService.deleteTechnologyById(id);
		return new ResponseEntity<Technology>(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping(value="/{id}", headers="Accept=application/json")
	public ResponseEntity<Technology> updateTechnologyPartially(@PathVariable("id") long id, @RequestBody Technology currentTechnology){
		Technology technology = technologyService.findById(id);
		if(technology ==null){
		    Log.warn("updating null technology!!!!!");
			return new ResponseEntity<Technology>(HttpStatus.NOT_FOUND);
		}
		Technology tech =	technologyService.updatePartially(currentTechnology, id);
		return new ResponseEntity<Technology>(tech, HttpStatus.OK);
	}
}
