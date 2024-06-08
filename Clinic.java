import java.util.Scanner;

public class Clinic {
	
	//Static scanner object to use in clinic methods
	static final Scanner SCAN = new Scanner(System.in);
	
	
	//Method to create mixed staff array with type of parent class HospitalStaffMember to support Polymorphism.
	public static HospitalStaffMember[] createStaff() {
		Doctor doctor1 = new Doctor("Alfred", "Bester", "SF Hospital", "General Practitioner", 200.0);
		Doctor doctor2 = new Doctor("Isaac", "Asimov", "SF Hospital", "Neurologist", 400.0);
		Doctor doctor3 = new Doctor("Mary", "Shelley", "SF Hospital", "Surgeon", 600.0);
	
		Employee employee1 = new Employee("William", "Gibson", "SF Hospital", 1800.0, 150);
		Employee employee2 = new Employee("Ursula", "LeGuin", "SF Hospital", 3000.0, 170);
		
		HospitalStaffMember[] newStaff = {doctor1, doctor2, doctor3, employee1, employee2};
		return newStaff;
	}
	
	
	//Method uses loop to prompt the user and validates the length before continuing.
	public static String validateMinimumLength(int min, String prompt) {
		System.out.println(prompt);
		String userInput = SCAN.nextLine();
		while (userInput.length() < min) {
			System.out.println("Invalid! Response too short.");
			System.out.println(prompt);
			userInput = SCAN.nextLine();
		}
		return userInput;
	}
	
	
	//Boolean method uses loop to validate and enforce yes/ no responses before continuing.
	public static boolean validateYesNo() {
		String userInput = SCAN.nextLine();
		while (!userInput.toLowerCase().equals("y") && !userInput.toLowerCase().equals("yes") && 
				!userInput.toLowerCase().equals("n") && !userInput.toLowerCase().equals("no")) {
			userInput = "";
			System.out.println("Invalid Response! (Enter YES / NO):");
			userInput = SCAN.nextLine();
		}
		
		if (userInput.toLowerCase().equals("y") || userInput.toLowerCase().equals("yes")) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	//Boolean method to search array and check whether staff member with name is also doctor.
	public static boolean validateIsDoctor(String name, HospitalStaffMember[] staffList) {
		for (HospitalStaffMember member : staffList) {
			if (member.getFullName().equals(name)) {
				if (!member.isDoctor()) {
					System.out.println("Invalid! Not a doctor.");
				}
				return member.isDoctor();
			}
		}
		
		return false;
	}
	
	
	//Method uses loop to check ANY array with Person parent and enforce valid data entry before continuing. Supports polymorphism.
	public static String findValidPersonName(Person[] array, String prompt) {
		String fullname = "";
		Boolean exists = false;
		
		while (exists != true) {
			//get user input of proper length.
			String currentPerson = validateMinimumLength(2, prompt);
			//check array.
			for (Person member : array) {
				if (member != null) {
					if (member.lastName.toLowerCase().equals(currentPerson.toLowerCase())) {
						fullname = member.getFullName();
						exists = true;
					}
				}
			}
			if (exists == false) {
				System.out.println("\nName not found. Try again!");	
			}
		}
		return fullname;
	}
	
	
	//Method to search array for name and update doctor data.
	public static void updateDoctorVisit(String name, HospitalStaffMember[] staffList ) {
		for (HospitalStaffMember member : staffList) {
			if (member.getFullName().equals(name)) {
				member.addVisit();
			}
		}
	}

	
	//Method to create a new patient and enforcing all proper validations.
	public static Patient createNewPatient(HospitalStaffMember[] staffList) {
		//set patient data with  user inputs that have validated length.
		String firstName = validateMinimumLength(2, "Patient's first name: ");
		String lastName = validateMinimumLength(2, "Patient's last name: ");
		String address = validateMinimumLength(5, "Patient's address: ");
		//instantiate patient object with basic info.
		Patient currentPatient = new Patient(firstName, lastName, address);
		
		//prevent patient from choosing employee who isn't a certified doctor.
		while (!validateIsDoctor(currentPatient.getPCP(), staffList)) {
			currentPatient.setPCP(findValidPersonName(staffList, "Primary Care Doctor's last name: " ));
		}
		//Update doctor visit data for payment.
		updateDoctorVisit(currentPatient.getPCP(), staffList);
		
		//prevent patient from choosing employee who isn't a certified doctor.
		while (!validateIsDoctor(currentPatient.getLastDoctor(), staffList)) {
			currentPatient.setLastDoctor(findValidPersonName(staffList, "Visitng Doctor's last name: "));
		}
		
		//check that visiting doctor is not the PCP.
		if (!currentPatient.getLastDoctor().equals(currentPatient.getPCP())) {
			updateDoctorVisit(currentPatient.getLastDoctor(), staffList);
		}
		
		return currentPatient;
	}
	
	
	//Method to handle a returning patient visit with all validation checks on inputs.
	public static void returningPatientVisit(HospitalStaffMember[] staffList, Patient[] patientList) {
		//get patient name that is validated length and exists in database.
		String fullname = findValidPersonName(patientList, "Patient's last name: ");
		Patient currentPatient;
		
		//search patient database.
		for (Patient member : patientList) {
			if (member != null) {
				System.out.println(fullname);
				if (member.getFullName().toLowerCase().equals(fullname.toLowerCase())) {
					//Find patient
					currentPatient = member;
					currentPatient.setLastDoctor("");
					while (!validateIsDoctor(currentPatient.getLastDoctor(), staffList)) {
						currentPatient.setLastDoctor(findValidPersonName(staffList, "Visiting Doctor's last name: "));
					}
					updateDoctorVisit(currentPatient.getPCP(), staffList);
					if (!currentPatient.getLastDoctor().equals(currentPatient.getPCP())) {
						updateDoctorVisit(currentPatient.getLastDoctor(), staffList);
					};
				}
			}
		}
	}
	
	
	//Method to handle ANY array with parent PERSON and displaying its toString data. Supports Polymorphism.
	public static void displayData(Person[] array) {
		for (Person member : array) {
			if (member != null) {
				System.out.println("======================================================");
				System.out.println(member.toString());
			}
		}
	}
	
	
	//Method to run clinic program. 
	public static void main(String[] args) {
		
		//prevent empty user inputs.
		SCAN.useDelimiter("\\R");
		
		//create clinic staff.
		HospitalStaffMember[] staff = createStaff();
		
		//create variables and array for patient data.
		int databaseLimit = 10;
		int totalPatients = 0;
		Patient[] patientDatabase = new Patient[databaseLimit];
		
		
		System.out.println("Welcome to the Science Fiction Clinic!");
	
		
		Boolean runProgram = true;
		while (runProgram) {
			
			System.out.println("\nPress ENTER to see the doctors on duty today:");
			SCAN.nextLine();
			System.out.println("======================================================");
			for (HospitalStaffMember member : staff) {
				if (member.isDoctor()) {
					System.out.println("Doctor's information:");
					System.out.println("Name: " + member.getFullName());
					System.out.println("Specialty: " + member.getSpecialty());
					System.out.println("======================================================");
				}
			}
			System.out.println("\nPress ENTER to continue");
			SCAN.nextLine();
			
			boolean userResponse;
			//Check if patient database is empty.
			if (totalPatients > 0) {
				//Check if patient database is too full, then double its size.
				if (totalPatients >= databaseLimit - 1) {
					databaseLimit *= 2;
					Patient[] patientDatabaseClone = patientDatabase.clone();
					patientDatabase = new Patient[databaseLimit];
					//Add original values to a bigger patient database array
					for (int i = 0; i < patientDatabaseClone.length; i++) {
						patientDatabase[i] = patientDatabaseClone[i];
					}
				}
				System.out.println("Is this a new patient? (YES / NO): ");
				userResponse = validateYesNo();
			}
			
			else {
				userResponse = true;		
			}
			
			//Start handling patient visits.
			if (userResponse) { //New Patient.
				patientDatabase[totalPatients] = createNewPatient(staff);	
				totalPatients++;
			}
			else { //Returning Patient.
				returningPatientVisit(staff, patientDatabase);
			}
			
			//Check if user wants to continue loop.
			System.out.println("\nMore patients? (YES / NO): ");
			userResponse = validateYesNo();
			
			//Display all final values for staff and patient data.
			if (!userResponse) {
				System.out.println("\nClinic database updated! **Authorized view only** ");
				System.out.println("\n****************************************************** \n");
				System.out.println("\tStaff Data:");
				displayData(staff);
				System.out.println("\n****************************************************** \n");
				System.out.println("\tPatient Data:");
				displayData(patientDatabase);
				runProgram = false;
			}
		}
		
		System.out.println("\n\nMain program terminated. Goodbye!");
	}

}
