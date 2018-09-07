package model;

public class Lease {
private int lease_no;
private String receive;
private String amount;
private String money;
private String payment_date;
private String insurance;
private String paid;
private int apartment_no;
private String duration_from;
private String duration_until;
private int lodger_phone;
private String lodger_sign;
private String renter_sign;
private String address;
private String office_name;
private int office_tel;
//private int office_phone;

public Lease(){}
public Lease(int lease_no){
	this.lease_no =lease_no;
}
public Lease( int lease_no, String receive,int lodger_phone, String amount, String money, String payment_date, String paid,
	int apartment_no, String duration_from, String duration_until, String lodger_sign, String renter_sign,
		 String address, String office_name, int office_tel){
			this.lease_no =lease_no;
			this.receive = receive;
			this.amount = amount;
			this.money =money;
			this.payment_date =payment_date;
			this.paid = paid;
			this.apartment_no =apartment_no;
			this. lodger_phone =lodger_phone;
			this.duration_from = duration_from;
			this.duration_until = duration_until;
			this.lodger_sign = lodger_sign;
			this.renter_sign = renter_sign;
			this.address= address;
			this.office_name = office_name;
			this.office_tel = office_tel;

		}
public Lease( int lease_no, String receive,String amount, String money, String payment_date, String paid,
		String duration_from, String duration_until, String lodger_sign, String renter_sign,
 String address, String office_name, int office_tel){
	this.lease_no =lease_no;
	this.receive = receive;
	this.amount = amount;
	this.money =money;
	this.payment_date =payment_date;
	this.paid = paid;
	this.duration_from = duration_from;
	this.duration_until = duration_until;
	this.lodger_sign = lodger_sign;
	this.renter_sign = renter_sign;
	this.address= address;
	this.office_name = office_name;
	this.office_tel = office_tel;

}

public Lease( int lease_no, String receive,String amount, String money, String paid,
		String duration_from, String duration_until, String lodger_sign, String renter_sign,
	 String address, String office_name, int office_tel){
			this.lease_no =lease_no;
			this.receive = receive;
			this.amount = amount;
			this.money =money;
			this.paid = paid;
			this.duration_from = duration_from;
			this.duration_until = duration_until;
			this.lodger_sign = lodger_sign;
			this.renter_sign = renter_sign;
			this.address= address;
			this.office_name = office_name;
			this.office_tel = office_tel;
		
		}

public int getLease_no() {
	return lease_no;
}
public void setLease_no(int lease_no) {
	this.lease_no = lease_no;
}
public String getReceive() {
	return receive;
}
public void setReceive(String receive) {
	this.receive = receive;
}
public String getAmount() {
	return amount;
}
public void setAmount(String amount) {
	this.amount = amount;
}
public String getMoney() {
	return money;
}
public void setMoney(String money) {
	this.money = money;
}
public String getPayment_date() {
	return payment_date;
}
public void setPayment_date(String payment_date) {
	this.payment_date = payment_date;
}
public String getInsurance() {
	return insurance;
}
public void setInsurance(String insurance) {
	this.insurance = insurance;
}
public String getPaid() {
	return paid;
}
public void setPaid(String paid) {
	this.paid = paid;
}
public int getApartment_no() {
	return apartment_no;
}
public void setApartment_no(int apartment_no) {
	this.apartment_no = apartment_no;
}
public String getDuration_from() {
	return duration_from;
}
public void setDuration_from(String duration_from) {
	this.duration_from = duration_from;
}
public String getDuration_until() {
	return duration_until;
}
public void setDuration_until(String duration_until) {
	this.duration_until = duration_until;
}
public int getLodger_phone() {
	return lodger_phone;
}
public void setLodger_phone(int lodger_phone) {
	this.lodger_phone = lodger_phone;
}
public String getLodger_sign() {
	return lodger_sign;
}
public void setLodger_sign(String lodger_sign) {
	this.lodger_sign = lodger_sign;
}
public String getRenter_sign() {
	return renter_sign;
}
public void setRenter_sign(String renter_sign) {
	this.renter_sign = renter_sign;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getOffice_name() {
	return office_name;
}
public void setOffice_name(String office_name) {
	this.office_name = office_name;
}
public int getOffice_tel() {
	return office_tel;
}
public void setOffice_tel(int office_tel) {
	this.office_tel = office_tel;
}
/*public int getOffice_phone() {
	return office_phone;
}
public void setOffice_phone(int office_phone) {
	this.office_phone = office_phone;
}*/
}
