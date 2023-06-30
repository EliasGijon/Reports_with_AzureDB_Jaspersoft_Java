package reports;

public class Employee
{
    private String id; //integer 
    private String producto;
    private String fecha;    
    private String cantidad;
    
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
	public void setID(String id) {
		this.id = id;
	}
        public void setProducto(String producto) {
		this.producto = producto;
	}
        public void setFecha(String fecha) {
		this.fecha = fecha;
	}
        public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
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
        
        
        public String getId() {
		return id;
	}
        public String getProducto() {
		return producto;
	}
        public String getFecha() {
		return fecha;
	}
        public String getCantidad() {
		return cantidad;
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
