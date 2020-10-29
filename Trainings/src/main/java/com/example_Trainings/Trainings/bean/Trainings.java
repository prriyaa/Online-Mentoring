package com.example_Trainings.Trainings.bean;

//import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
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
	private int status;

	@Column(name = "progress")
	private String progress;

	@Column(name = "rating")
	private double rating;

	@Column(name = "start_date")
    //@JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate start_date;

	@Column(name = "end_date")
    //@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate end_date;

	@Column(name = "start_time")
    //@JsonFormat(pattern="HH-mm-ss")
	private LocalTime start_time;

	@Column(name = "end_time")
    //@JsonFormat(pattern="HH-mm-ss")
	private LocalTime end_time;

	@Column(name = "amount_recieved")
	private double amount_recieved;


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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date() {
        this.start_date = java.time.LocalDate.now();
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date() {
        this.end_date = start_date.plusDays(1);
    }

    public LocalTime getStart_time() {
        return start_time;
    }

    public void setStart_time() {
        this.start_time =java.time.LocalTime.now() ;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time() {
        this.end_time = start_time.plusHours(2);
    }

    public double getAmount_recieved() {
        return amount_recieved;
    }

    public void setAmount_recieved(double amount_recieved) {
        this.amount_recieved = amount_recieved;
    }
}
