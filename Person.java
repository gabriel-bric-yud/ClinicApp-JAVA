abstract public class Person {
	protected String firstName;
	protected String lastName;
	protected String address;
	
	public Person(String fName, String lName, String pAddress) {
		firstName = fName;
		lastName = lName;
		address = pAddress;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	
	public String getAddress(String pAddress) {
		return address;
	}
	
	public void setFirstName(String name) {
		firstName = name;
	}
	
	public void setLastName(String name) {
		lastName = name;
	}
	
	public void setAddress(String pAddress) {
		address = pAddress;
	}
	
	
	public String toString() {
		return "Name: " + getFullName();
	}


}
