package com.example.demo_search.Model;

import javax.persistence.*;



//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Technology {

	private long id;

	private String name;

	private String toc;

	private String duration;

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
