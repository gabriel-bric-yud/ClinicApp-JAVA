public class Employee extends HospitalStaffMember{
	
	private double salary;
	private int totalHours = 0;

	public Employee(String fName, String lName, String hospital, double eSalary, int numHours) {
		super(fName, lName, hospital);
		salary = eSalary;
		totalHours = numHours;
	}
	
	
	public String getSpecialty() {
		return "This employee has no medical speialty.";
	}
	
	public boolean isDoctor() {
		return false;
	}
	
	public void addVisit() {
		System.out.println("Visits are not possible with this staff member type.");
	}
	
	private double overtimeCalculation() {
		double overtimeHours = totalHours - 165;
		double overtimeMultiplier = (salary / 165) * 1.5;
		double overtimePay = overtimeHours * overtimeMultiplier;
		return overtimePay;
	}
	
	public double pay() {
		double overtime = 0;
		if (totalHours > 165) {
			overtime = overtimeCalculation();
		}
		return salary + overtime;
	}

	
	public String toString() {
		String result = "Employee's information: \n";
		result += super.toString();
		result += "\nSalary Rate: " + cfmt.format(salary);
		result += "\nHours: " + totalHours;
		result += "\nPaid: " + cfmt.format(pay());
		return result;
	}

}