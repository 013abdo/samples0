package model;

public class Apartment {
	private int apartment_no;//09
	private int room_no;//10
	private int kitchen_no;//11
	private int toilet_no;//12
	private int hall_no;//13
	private String insurance;//05
	private String amount;//01
	private int price;
	private String rent_state;//14
	private String property_name;

	public Apartment(){}
	
	public Apartment(int apartment_no){
		this.apartment_no = apartment_no;
	}
	
	public Apartment(int apartment_no, int room_no, int kitchen_no, int toilet_no,int hall_no,
			 String amount, int price,
	   String insurance, String rent_state,String property_name){
		 this.amount = amount;
		 this. price = price;
		 this.insurance = insurance;
		 this.apartment_no = apartment_no;
		 this.room_no = room_no;
		 this.kitchen_no = kitchen_no;
		 this.toilet_no = toilet_no;
		 this.hall_no = hall_no;
		 this.rent_state = rent_state; 
		 this.property_name = property_name;
	}
	
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public int getApartment_no() {
		return apartment_no;
	}
	public void setApartment_no(int apartment_no) {
		this.apartment_no = apartment_no;
	}
	public int getRoom_no() {
		return room_no;
	}
	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}
	public int getKitchen_no() {
		return kitchen_no;
	}
	public void setKitchen_no(int kitchen_no) {
		this.kitchen_no = kitchen_no;
	}
	public int getToilet_no() {
		return toilet_no;
	}
	public void setToilet_no(int toilet_no) {
		this.toilet_no = toilet_no;
	}
	public int getHall_no() {
		return hall_no;
	}
	public void setHall_no(int hall_no) {
		this.hall_no = hall_no;
	}
	public String getRent_state() {
		return rent_state;
	}
	public void setRent_state(String rent_state) {
		this.rent_state = rent_state;
	}

	public String getProperty_name() {
		return property_name;
	}

	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}
}
