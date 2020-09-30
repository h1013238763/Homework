package edu.miracosta.cs113.lab4;


public class DirectoryEntry {

 
	    private String name;
	    private String number;
	     
	    public DirectoryEntry(String name, String phone){
	        this.name = name;
	        this.number = phone;
	    }
	    
	    public String getNumber() {
	    	return number;
	    }
	    public void setNumber(String number) {
	    	this.number = number;
	    }

	    public String getName() {
	    	return name;
	    }
	    public void setName(String name) {
	    	this.name = name;
	    }
}
