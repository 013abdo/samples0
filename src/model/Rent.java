package model;

public class Rent {
	private int rent_id;
	private int floor_no;
	private int property_num;
	private int lease_no;
	private String rent_state;
	private int apartment_no;

	public Rent(int rent_id, int property_num,int floor_no,int apartment_no ,int lease_no, String rent_state){
		this.rent_id =rent_id;
		this.floor_no = floor_no;
		this.property_num =property_num;
		this.lease_no =lease_no;
		this.rent_state =rent_state;
		this.apartment_no=apartment_no;
	}


	public int getRent_id() {
		return rent_id;
	}

	public void setRent_id(int rent_id) {
		this.rent_id = rent_id;
	}

	public int getFloor_no() {
		return floor_no;
	}

	public void setFloor_no(int floor_no) {
		this.floor_no = floor_no;
	}

	public int getLease_no() {
		return lease_no;
	}
	public void setLease_no(int lease_no) {
		this.lease_no = lease_no;
	}
	public String getRent_state() {
		return rent_state;
	}



	public void setRent_state(String rent_state) {
		this.rent_state = rent_state;
	}



	public int getApartment_no() {
		return apartment_no;
	}
	public void setApartment_no(int apartment_no) {
		this.apartment_no = apartment_no;
	}



	public int getProperty_num() {
		return property_num;
	}



	public void setProperty_num(int property_num) {
		this.property_num = property_num;
	}
}
