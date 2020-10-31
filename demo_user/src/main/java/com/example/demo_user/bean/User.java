package com.example.demo_user.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;



@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;

	@Column(name="username")
	private String username;

	@Column(name="password")
	private String password;

	@NotEmpty(message = "First name is required")
	@Column(name="firstname")
	private String firstname;

	@NotNull(message="Last name cannot be null")
	@Column(name="lastname")
	private String lastname;

    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
            message="Mobile number is invalid")
	@Column(name="contactno")
	private String contactno;

    @Column(name="reg_datetime")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date reg_datetime;

    @Column(name="reg_code")
	private long reg_code;

	@Column(name="role")
	private String role;

	@Column(name="active")
	private boolean active;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;


	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}


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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public Date getReg_datetime() {
		return reg_datetime;
	}

	public void setReg_datetime(Date reg_datetime) {
		this.reg_datetime = reg_datetime;
	}

	public long getReg_code() {
		return reg_code;
	}

	public void setReg_code(long reg_code) {
		this.reg_code = reg_code;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
