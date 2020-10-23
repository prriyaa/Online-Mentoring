package com.example.demo_search.bean;

import javax.persistence.*;


@Entity
@Table(name="Mentor")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Mentor {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;

	@Column(name="username")
	private String username;

	@Column(name="linkedin_url")
	private String linkedin_url;

	@Column(name="reg_datetime")
	private String reg_datetime;

    @Column(name="reg_code")
    private String reg_code;

    @Column(name="experience")
    private String experience;

    @Column(name="active")
    private String active;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLinkedin_url() {
        return linkedin_url;
    }

    public void setLinkedin_url(String linkedin_url) {
        this.linkedin_url = linkedin_url;
    }

    public String getReg_datetime() {
        return reg_datetime;
    }

    public void setReg_datetime(String reg_datetime) {
        this.reg_datetime = reg_datetime;
    }

    public String getReg_code() {
        return reg_code;
    }

    public void setReg_code(String reg_code) {
        this.reg_code = reg_code;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
