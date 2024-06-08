import java.text.NumberFormat;

abstract public class HospitalStaffMember extends Person {
	protected NumberFormat cfmt = NumberFormat.getCurrencyInstance();
	
	public HospitalStaffMember(String fName, String lName, String pAddress) {
		super(fName, lName, pAddress);
	}
	
	public abstract void addVisit();
	
	public abstract String getSpecialty();
	
	public abstract boolean isDoctor();
	
	public abstract double pay();
	
}
