public class Doctor extends HospitalStaffMember{
	
	private String specialty;
	private double visitFee;
	private int totalVisits = 0;
	
	public Doctor(String fName, String lName, String hospital, String dSpecialty, double fee) {
		super(fName, lName, hospital);
		specialty = dSpecialty;
		visitFee = fee;
	}
	
	public int getTotalVisits() {
		return totalVisits;
	}
	
	public String getSpecialty() {
		return specialty;
	}
	
	public boolean isDoctor() {
		return true;
	}
	
	public void addVisit() {
		totalVisits++;
	}
	
	public double pay() {
		return totalVisits * visitFee;
	}
	
	
	
	public String toString() {
		String result = "Doctor's information: \n";
		result += super.toString();
		result += "\nSpecialty: " + specialty;
		result += "\nOffice visit fee: " + cfmt.format(visitFee);
		result += "\nNumber of visits: " + totalVisits;
		result += "\nPaid: " + cfmt.format(pay());
		return result;
	}

}
