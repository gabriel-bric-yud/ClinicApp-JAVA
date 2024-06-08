public class Patient extends Person {
	String PCP = "";
	String lastVisitedDoctor = "";
	
	
	public Patient(String fName, String lName, String address) {
		super(fName, lName, address);
	}
	
	
	public String getPCP() {
		return PCP;
	}
	
	public String getLastDoctor() {
		return lastVisitedDoctor;
	}
	
	public void setPCP(String primary) {
		PCP = primary;
	}
	
	public void setLastDoctor(String lastDoc) {
		lastVisitedDoctor = lastDoc;
	}

	public void visit(String doc) {
		lastVisitedDoctor = doc;
	}
	
	public String toString() {
		String result = super.toString();
		result += "\nAddress: " + address;
		result += "\nPrimary Care Physician: " + PCP;
		result += "\nLast Visited Doctor: " + lastVisitedDoctor;
		return result;
	}

}
