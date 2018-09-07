package model;

public class Office {
private String office_name;
private int office_num;
private int office_tel;
private int office_phone;
private String office_address;
private String office_city;
private String office_owner;

public Office(){}

public Office( int office_num,String office_name, int office_phone,int office_tel,
		String office_address,String office_city, String office_owner){
	this.office_num = office_num;
	this.office_name = office_name;
	this.office_tel = office_tel;
	this.office_phone = office_phone;
	this.office_address = office_address;
	this.office_city = office_city;
	this.office_owner = office_owner;
	
}
public Office( String office_name, int office_phone, String office_address, String office_owner){
	
	this.office_name = office_name;
	this.office_phone = office_phone;
	this.office_address = office_address;
	this.office_owner = office_owner;
}
public String getOffice_name() {
	return office_name;
}
public void setOffice_name(String office_name) {
	this.office_name = office_name;
}
public int getOffice_num() {
	return office_num;
}
public void setOffice_num(int office_num) {
	this.office_num = office_num;
}
public int getOffice_tel() {
	return office_tel;
}

public void setOffice_tel(int office_tel) {
	this.office_tel = office_tel;
}

public int getOffice_phone() {
	return office_phone;
}
public void setOffice_phone(int office_phone) {
	this.office_phone = office_phone;
}
public String getOffice_address() {
	return office_address;
}
public void setOffice_address(String office_address) {
	this.office_address = office_address;
}
public String getOffice_city() {
	return office_city;
}

public void setOffice_city(String office_city) {
	this.office_city = office_city;
}

public String getOffice_owner() {
	return office_owner;
}
public void setOffice_owner(String office_owner) {
	this.office_owner = office_owner;
}
}
