package com.example.demo_search.bean;

import javax.persistence.*;


@Entity
@Table(name="MentorSkills")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MentorSkills {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;

	@Column(name="mid")
	private long mid;

	@Column(name="sid")
	private long sid;

	@Column(name="rating")
	private double rating;

    @Column(name="exp")
    private String exp;

    @Column(name="trainings_delivered")
    private int trainings_delivered;

    @Column(name="facilities_offered")
    private int facilities_offered;

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

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public int getTrainings_delivered() {
        return trainings_delivered;
    }

    public void setTrainings_delivered(int trainings_delivered) {
        this.trainings_delivered = trainings_delivered;
    }

    public int getFacilities_offered() {
        return facilities_offered;
    }

    public void setFacilities_offered(int facilities_offered) {
        this.facilities_offered = facilities_offered;
    }


}
