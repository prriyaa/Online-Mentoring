package com.example.demo_search.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


@Entity
@Table(name="MentorCalender")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MentorCalender {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;

	@Column(name="mid")
	private long mid;

	@Column(name="start_date")
    @JsonFormat(pattern="yyyy-MM-dd")
	private Date start_date;

	@Column(name="end_date")
    @JsonFormat(pattern="yyyy-MM-dd")
	private Date end_date;

    @Column(name="start_time")
    @JsonFormat(pattern="HH-mm-ss")
    private Time start_time;

    @Column(name="end_time")
    @JsonFormat(pattern="HH-mm-ss")
    private Time end_time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
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
}
