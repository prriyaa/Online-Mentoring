package com.example_Trainings.Trainings.controller;



import com.example_Trainings.Trainings.Model.MentorCalender;
import com.example_Trainings.Trainings.Model.Payment;
import com.example_Trainings.Trainings.bean.Trainings;
import com.example_Trainings.Trainings.service.TrainingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value={"/trainings"})
public class TrainingsController {
	@Autowired
	TrainingsService trainingsService;

    private final Logger Log = LoggerFactory.getLogger(getClass());

	@Value("${pqr.xyz}")
	String str1;

	@Value("${abcd.efgh}")
	String str;


	@GetMapping
	public ResponseEntity<List<Trainings>> getAllTrainings(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy)
	{
		List<Trainings> list = trainingsService.getAllTrainings(pageNo, pageSize, sortBy);
		System.out.println("String is: "+str);
		System.out.println("Message from company: "+str1);
		return new ResponseEntity<List<Trainings>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	//get training details using training id
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Trainings> getTrainingsById(@PathVariable("id") long id) {
	    Log.info("fetching training with id"+id);
        //System.out.println("Fetching User with id " + id);
        Trainings trainings = trainingsService.findById(id);
        if (trainings == null) {
            return new ResponseEntity<Trainings>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Trainings>(trainings, HttpStatus.OK);
    }
    //get completed trainings
	@GetMapping(value="/getcompleted/{status}", headers="Accept=application/json")
	public List<Trainings> getByName(@PathVariable int status) {
	    Log.info("return completed trainings");
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
	    Log.info("create trainings");
	     //System.out.println("Creating Trainings "+trainings.getProgress());
         trainings.setStart_date();
         trainings.setStart_time();
         trainings.setEnd_date();
         trainings.setEnd_time();
	     trainingsService.createTrainings(trainings);
	     HttpHeaders headers = new HttpHeaders();
	     headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(trainings.getId()).toUri());
	     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }


    @PostMapping(value="/createpayment",headers="Accept=application/json")
    public ResponseEntity<Void> createPayment(@RequestBody Payment payment, UriComponentsBuilder ucBuilder){
        Log.info("create payment");
        //System.out.println("Creating Trainings "+trainings.getProgress());
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:9826/createpayment";
        URI uri = null;
        try {
            uri = new URI(baseUrl);
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        restTemplate.postForEntity(uri,payment, Payment.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(payment.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	 //propose new training

	@PostMapping(value="/propose/{learner_id}/{mentor_id}",headers="Accept=application/json")
	public ResponseEntity<Void> proposeTrainings(@RequestBody Trainings trainings, UriComponentsBuilder ucBuilder,@PathVariable long learner_id,@PathVariable long mentor_id){
		Log.info("propose new training by user learner id: "+learner_id);
	     //System.out.println("Propose new Training by learner with id  "+trainings.getId());
		trainings.setMentorId(mentor_id);
		trainings.setUser_id(learner_id);
		trainings.setStatus(0);
        trainingsService.createTrainings(trainings);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(trainings.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	 @GetMapping(value="/get", headers="Accept=application/json")
	 public List<Trainings> getAllTrainings() {
	    Log.info("returning list of all ongoing trainings ");
	  List<Trainings> tasks=trainingsService.getTrainings();
	  return tasks;
	
	 }


	@PutMapping(value="/update", headers="Accept=application/json")
	public ResponseEntity<String> updateTrainings(@RequestBody Trainings currentTrainings)
	{
		//System.out.println("sd");
	Trainings trainings = trainingsService.findById(currentTrainings.getId());
	if (trainings==null) {
	    Log.error("you are trying to update an empty training");
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	trainingsService.update(currentTrainings, currentTrainings.getId());
	return new ResponseEntity<String>(HttpStatus.OK);
	}
	//Approve training


    @PutMapping(value="/approve/{training_id}", headers="Accept=application/json")
    public ResponseEntity<String> approveTraining(@PathVariable long training_id,@RequestHeader ("Authorization") String header)
    {
        //System.out.println("sd");

		//@Autowired
		RestTemplate restTemplate=new RestTemplate();

		final String baseUrl121 = "http://localhost:9829/users/authorizing";
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
        //System.out.println("role is"+role);

			Trainings trainings = trainingsService.findById(training_id);
			if (trainings == null) {
				Log.error("empty training cannot be approved");
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}
			String role1="mentor";

			if(role.equals(role1)) {
				System.out.println("just check");
				if (trainings.getStatus() == 0) {
					trainings.setStatus(1);
				}
				trainingsService.update(trainings, trainings.getId());

				System.out.println("training approved of learner having id:" + trainings.getUser_id());
			}
			else
			{
				Log.info("you can't approve training because your role is : " +role);
			}
        return new ResponseEntity<String>(HttpStatus.OK);

    }

    //finalize training
    @PutMapping(value="/finalize/{training_id}", headers="Accept=application/json")
    public ResponseEntity<String> finalizeTraining(@PathVariable long training_id,@RequestBody Payment payment)
    {
        //System.out.println("sd");
        Trainings trainings = trainingsService.findById(training_id);
        if (trainings==null) {
            Log.error("you are trying to finalize empty training");
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        if(trainings.getStatus()==1) {
            trainings.setStatus(2);
        }
        payment.setMentorId(trainings.getMentorId());
        payment.setDatetime();
        payment.setTrainingId(training_id);

		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:9826/createpayment";
		URI uri = null;
		try {
			uri = new URI(baseUrl);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 restTemplate.postForEntity(uri,payment, Payment.class);
        trainingsService.update(trainings, trainings.getId());
        System.out.println("training finalized of learner having id:"+trainings.getUser_id());
        return new ResponseEntity<String>(HttpStatus.OK);

    }
//add mentor calender
	@PutMapping(value="/updatecalender/{training_id}", headers="Accept=application/json")
	public ResponseEntity<String> finalizeTraining1(@PathVariable long training_id)
	{
		//System.out.println("sd");
		MentorCalender calender=new MentorCalender();
		Trainings trainings = trainingsService.findById(training_id);
		if (trainings==null) {
			Log.error("you are trying to finalize empty training");
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		trainings.setStatus(2);

		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:8986/search/addmentorcalender";
		URI uri = null;
		try {
			uri = new URI(baseUrl);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		calender.setStart_date(trainings.getStart_date());
		calender.setEnd_date(trainings.getEnd_date());
		calender.setStart_time(trainings.getStart_time());
		calender.setEnd_date(trainings.getEnd_date());
		calender.setMid(trainings.getMentorId());

		restTemplate.postForEntity(uri,calender, MentorCalender.class);

		trainingsService.update(trainings, trainings.getId());
		System.out.println("training finalized of learner having id:"+trainings.getUser_id());
		return new ResponseEntity<String>(HttpStatus.OK);

	}
	@DeleteMapping(value="/{id}", headers ="Accept=application/json")
	public ResponseEntity<Trainings> deleteTrainings(@PathVariable("id") long id){
		Trainings trainings = trainingsService.findById(id);
		if (trainings == null) {
		    Log.error("no such trainings with given ud exists");
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
    @PutMapping(value="/updateAmount/{id}")
    public ResponseEntity<String> updateAmount(@PathVariable("id") long id, @RequestParam double amount){
        Log.info("Fetching training with id " + id);
        Trainings training = trainingsService.findById(id);
        if (training == null) {
            Log.error("No training with id: " + id +" exists");
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        trainingsService.updateAmount(id,amount);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
