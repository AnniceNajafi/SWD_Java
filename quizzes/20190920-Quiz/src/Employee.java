public class Employee {
    private static int numberOfEmployees=0;
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;
    public Employee(String firstName, String lastName, String socialSecurityNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;

        numberOfEmployees++;
    }
    // return first name
    public String getFirstName() {
        return firstName;
    }

    // return last name
    public String getLastName() {
        return lastName;
    }

    // return social security number
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }
    public String toString(){
        return String.format("%s: %s %s%n%s: %s%n%s: %.2f%n%s: %.2f",numberOfEmployees,
                super.toString(), "Employee Name", getFirstName(), getLastName(),
                "social security number", getSocialSecurityNumber());
    }

}
