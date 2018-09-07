package model;

public class Realty {
private int property_num;
private String property_name;
private String property_type;
private String property_address;
private String property_city;
private String property_zip;
private int floor_no;
private int apartment_no;
private int room_no;
private int kitchen_no;
private int toilet_no;
private int hall_no;
private int office_num;

public Realty(){}
public Realty(String property_name){
	this.property_name = property_name;
}
//proprty data
public Realty( int property_num, String property_name, String property_type , String property_address){
	this.property_num = property_num;
	this.property_name = property_name;
	this.property_type = property_type;
	this.property_address =property_address;
}
//property address
public Realty( String property_city, String property_zip){
	this.property_city = property_city;
	this.property_zip = property_zip;
}
//address
public Realty( int property_num, String property_name, String property_type , String property_address,
		String property_city, String property_zip){
	this.property_num = property_num;
	this.property_name = property_name;
	this.property_type = property_type;
	this.property_address =property_address;
	this.property_city = property_city;
	this.property_zip = property_zip;
}
//property size
public Realty( int floor_no, int apartment_no, int room_no, int kitchen_no, int toilet_no, int hall_no){
	this.floor_no = floor_no;
	this.apartment_no = apartment_no;
	this.room_no = room_no;
	this.kitchen_no = kitchen_no;
	this.toilet_no = toilet_no;
	this.hall_no = hall_no;
}
public Realty( int property_num, String property_name, String property_type , String property_address,
		int floor_no, int apartment_no, int room_no, int kitchen_no, int toilet_no, int hall_no){
	this.property_num = property_num;
	this.property_name = property_name;
	this.property_type = property_type;
	this.property_address =property_address;
	this.floor_no = floor_no;
	this.apartment_no = apartment_no;
	this.room_no = room_no;
	this.kitchen_no = kitchen_no;
	this.toilet_no = toilet_no;
	this.hall_no = hall_no;
}

//whole data
public Realty( int property_num, String property_name,String property_type, String property_address,
		String property_city, String property_zip,
		int floor_no, int apartment_no, int room_no, int kitchen_no, int toilet_no, int hall_no,int office_num){
	this.property_num = property_num;
	this.property_name = property_name;
	this.property_type = property_type;
	this.property_address =property_address;
	this.property_city = property_city;
	this.property_zip = property_zip;
	this.floor_no = floor_no;
	this.apartment_no = apartment_no;
	this.room_no = room_no;
	this.kitchen_no = kitchen_no;
	this.toilet_no = toilet_no;
	this.hall_no = hall_no;
	this.office_num = office_num;
}

public int getProperty_num() {
	return property_num;
}
public void setProperty_num(int property_num) {
	this.property_num = property_num;
}
public String getProperty_name() {
	return property_name;
}
public void setProperty_name(String property_name) {
	this.property_name = property_name;
}
public String getProperty_type() {
	return property_type;
}
public void setProperty_type(String property_type) {
	this.property_type = property_type;
}
public String getProperty_address() {
	return property_address;
}
public void setProperty_address(String property_address) {
	this.property_address = property_address;
}
public String getProperty_city() {
	return property_city;
}
public void setProperty_city(String property_city) {
	this.property_city = property_city;
}

public String getProperty_zip() {
	return property_zip;
}
public void setProperty_zip(String property_zip) {
	this.property_zip = property_zip;
}
public int getFloor_no() {
	return floor_no;
}
public void setFloor_no(int floor_no) {
	this.floor_no = floor_no;
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
public int getOffice_num() {
	return office_num;
}
public void setOffice_num(int office_num) {
	this.office_num = office_num;
}
}
