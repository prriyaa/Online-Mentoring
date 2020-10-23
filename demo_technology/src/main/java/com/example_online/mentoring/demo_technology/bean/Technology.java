package com.example_online.mentoring.demo_technology.bean;

import javax.persistence.*;


@Entity
@Table(name="Technology")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Technology {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;

	@Column(name="name")
	
	private String name;

	@Column(name="toc")
	private String toc;

	@Column(name="duration")
	private String duration;

    @Column(name="prerequisites")
    private String prerequisites;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToc() {
		return toc;
	}

	public void setToc(String toc) {
		this.toc = toc;
	}

	public String getDuration(){return duration;}

	public void setDuration(String duration){this.duration=duration ;}

}
