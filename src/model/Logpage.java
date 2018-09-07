package model;

public class Logpage {
private String username;
private int pass;
private String type;

public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public int getPass() {
	return pass;
}
public void setPass(int pass) {
	this.pass = pass;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
// default constructor
public Logpage(String username, int pass,String type){
	this.username = username;
	this.pass = pass;
	this.type =type;
}
}
