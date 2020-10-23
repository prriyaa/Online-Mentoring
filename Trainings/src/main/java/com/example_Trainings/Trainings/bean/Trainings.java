package com.example_Trainings.Trainings.bean;

//import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


@Entity
@Table(name="Trainings")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Trainings {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;

	@Column(name="user_id")
	private long user_id;


	@Column(name="mentor_id")
	private long mentorId;

	@Column(name="skill_id")
	private long skill_id;

	@Column(name = "status")
	private String status;

	@Column(name = "progress")
	private String progress;

	@Column(name = "rating")
	private double rating;

	@Column(name = "start_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date start_date;

	@Column(name = "end_date")
    @JsonFormat(pattern="yyyy-MM-dd")
	private Date end_date;

	@Column(name = "start_time")
    @JsonFormat(pattern="HH-mm-ss")
	private Time start_time;

	@Column(name = "end_time")
    @JsonFormat(pattern="HH-mm-ss")
	private Time end_time;

	@Column(name = "amount_recieved")
	private double amount_recieved;

	/*public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getMentorId() {
        return mentorId;
    }

    public void setMentorId(long mentorId) {
        this.mentorId = mentorId;
    }

    public long getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(long skill_id) {
        this.skill_id = skill_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }

    public double getAmount_recieved() {
        return amount_recieved;
    }

    public void setAmount_recieved(double amount_recieved) {
        this.amount_recieved = amount_recieved;
    }
}
