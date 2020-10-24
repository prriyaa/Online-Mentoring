package com.example_Trainings.Trainings.controller;



import com.example_Trainings.Trainings.Model.Payment;
import com.example_Trainings.Trainings.bean.Trainings;
import com.example_Trainings.Trainings.service.TrainingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value={"/trainings"})
public class TrainingsController {
	@Autowired
	TrainingsService trainingsService;

	/*@Value("${pqr.xyz}")
	String str1;

	@Value("${abcd.efgh}")
	String str;*/


	/*@GetMapping
	public ResponseEntity<List<Trainings>> getAllTrainings(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy)
	{
		List<Trainings> list = trainingsService.getAllTrainings(pageNo, pageSize, sortBy);
		System.out.println("String is: "+str);
		System.out.println("Message from company: "+str1);

		return new ResponseEntity<List<Technology>>(list, new HttpHeaders(), HttpStatus.OK);
	}*/

	//get training details using trainig id
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Trainings> getTrainingsById(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Trainings trainings = trainingsService.findById(id);
        if (trainings == null) {
            return new ResponseEntity<Trainings>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Trainings>(trainings, HttpStatus.OK);
    }
    //get completed trainings
	@GetMapping(value="/getcompleted/{status}", headers="Accept=application/json")
	public List<Trainings> getByName(@PathVariable String status) {
		List<Trainings> lm=trainingsService.findByStatus(status);
		return lm;

	}
	@GetMapping(value="/getcompleted/{status}/{mid}", headers="Accept=application/json")
	public List<Trainings> getByNameAndId(@PathVariable String status,@PathVariable long mid) {
		List<Trainings> lm=trainingsService.findByStatusAndMentorId(status,mid);
		return lm;

	}
    
	 @PostMapping(value="/create",headers="Accept=application/json")
	 public ResponseEntity<Void> createTrainings(@RequestBody Trainings trainings, UriComponentsBuilder ucBuilder){
	     System.out.println("Creating Trainings "+trainings.getProgress());
	     trainingsService.createTrainings(trainings);
	     HttpHeaders headers = new HttpHeaders();
	     headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(trainings.getId()).toUri());
	     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }
	 //propose new training

	@PostMapping(value="/propose/{learner_id}/{mentor_id}",headers="Accept=application/json")
	public ResponseEntity<Void> proposeTrainings(@RequestBody Trainings trainings, UriComponentsBuilder ucBuilder,@PathVariable long learner_id,@PathVariable long mentor_id){
		System.out.println("Propose new Training by learner with id  "+trainings.getId());
		trainings.setMentorId(mentor_id);
		trainings.setUser_id(learner_id);
        trainingsService.createTrainings(trainings);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(trainings.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	 @GetMapping(value="/get", headers="Accept=application/json")
	 public List<Trainings> getAllTrainings() {
	  List<Trainings> tasks=trainingsService.getTrainings();
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
	public ResponseEntity<String> updateTrainings(@RequestBody Trainings currentTrainings)
	{
		System.out.println("sd");
	Trainings trainings = trainingsService.findById(currentTrainings.getId());
	if (trainings==null) {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	trainingsService.update(currentTrainings, currentTrainings.getId());
	return new ResponseEntity<String>(HttpStatus.OK);
	}
	//Approve training

    @PutMapping(value="/approve/{training_id}", headers="Accept=application/json")
    public ResponseEntity<String> approveTraining(@PathVariable long training_id)
    {
        //System.out.println("sd");
        Trainings trainings = trainingsService.findById(training_id);
        if (trainings==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        trainings.setStatus("Approved");
        trainingsService.update(trainings, trainings.getId());
        System.out.println("training approved of learner having id:"+trainings.getUser_id());
        return new ResponseEntity<String>(HttpStatus.OK);

    }

    //finalize training
    @PutMapping(value="/finalize/{training_id}", headers="Accept=application/json")
    public ResponseEntity<String> finalizeTraining(@PathVariable long training_id)
    {
        //System.out.println("sd");
        Trainings trainings = trainingsService.findById(training_id);
        if (trainings==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        trainings.setStatus("finalized");

		/*RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:9826/createpayment";
		URI uri = null;
		try {
			uri = new URI(baseUrl);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResponseEntity <Payment> result = restTemplate.postForEntity(uri, Payment.class);

		//ResponseEntity <List> result = restTemplate.getForEntity(uri, List.class);
		//List <Technology> tech1=result.getBody();
		Payment payment=result.getBody();

		//Verify request succeed
		System.out.println("Status code: " + result.getStatusCodeValue());
		//System.out.println("class type is :"+tech1.getClass().getName());
		System.out.println("result body class is " + result.getBody().getClass().getName());
		System.out.println("result: " + result.getBody());
*/

		//return payment;
        //trainings.setAmount_recieved(900.32);
        trainingsService.update(trainings, trainings.getId());
        System.out.println("training finalized of learner having id:"+trainings.getUser_id());
        return new ResponseEntity<String>(HttpStatus.OK);

    }
	
	@DeleteMapping(value="/{id}", headers ="Accept=application/json")
	public ResponseEntity<Trainings> deleteTrainings(@PathVariable("id") long id){
		Trainings trainings = trainingsService.findById(id);
		if (trainings == null) {
			return new ResponseEntity<Trainings>(HttpStatus.NOT_FOUND);
		}
		trainingsService.deleteTrainingsById(id);
		return new ResponseEntity<Trainings>(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping(value="/{id}", headers="Accept=application/json")
	public ResponseEntity<Trainings> updateTechnologyPartially(@PathVariable("id") long id, @RequestBody Trainings currentTrainings){
		Trainings trainings = trainingsService.findById(id);
		if(trainings ==null){
			return new ResponseEntity<Trainings>(HttpStatus.NOT_FOUND);
		}
		Trainings tech =	trainingsService.updatePartially(currentTrainings, id);
		return new ResponseEntity<Trainings>(tech, HttpStatus.OK);
	}
}
