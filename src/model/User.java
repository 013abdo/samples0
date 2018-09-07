package model;



public class User {
	private int id;
	private String firstname;
	private String lastname;
	private int phone;
	private String email;
	private String job;
	private String gender;
	private String city;
	private String address;
	private String zip;
	private int na_id;
	

	// Default cons
	/*
	 * public User(){
	 * 
	 * }
	 */
	// data for Admin
	public User(int id, String firstname, String lastname, int phone, String email, String job, String gender,
			String city, String address, String zip, int na_id) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.job = job;
		this.gender = gender;
		this.city = city;
		this.address = address;
		this.zip = zip;
		this.na_id = na_id;
	}

	// data for he worker
	public User(int id, String firstname, String lastname, int phone, String gender, int na_id) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.gender = gender;
		this.na_id = na_id;
	}

	// data for the lodger
	public User(String firstname, String lastname, int phone) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public int getNa_id() {
		return na_id;
	}

	public void setNa_id(int na_id) {
		this.na_id = na_id;
	}
}
