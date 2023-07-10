package createReports;

public class Employee
{
    private String column1; //Jasper parameter field name 
    private String column2;
    private String column3;    
    private String column4;
    
    private int year_end;    
    private int year_start;
    private String month_start;
    private String month_end;
    
    //private double salary;// floating point value
    
    /*public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	*/
    
    //Dinamic DB Information
	public void setColumn1(String id) {
		column1 = id;
	}
        public void setColumn2(String producto) {
        column2 = producto;
	}
        public void setColumn3(String fecha) {
    	column3 = fecha;
	}
        public void setColumn4(String cantidad) {
        column4 = cantidad;
	}
          
        public void setMonthstart(String monthstart) {
		month_start = monthstart;
	}
        public void setYearstart(int yearstart) {
		year_start = yearstart;
	}
        public void setMonthend(String monthend) {
		month_end = monthend;
	}
        public void setYearend(int yearend) {
		year_end = yearend;
	}
        
    //Find information jasper to compile need get+MAYUS+yourfieldname
        public String getColumn1() {
		return column1;
	}
        public String getColumn2() {
		return column2;
	}
        public String getColumn3() {
		return column3;
	}
        public String getColumn4() {
		return column4;
	}
        
        
        public String getMonthstart() {
            return month_start;
	}
        public int getYearstart() {
            return year_start;
	}
        
        public String getMonthend() {
            return month_end;
	}
        public int getYearend() {
            return year_end;
	}
        
	/*
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	 */
	
}
