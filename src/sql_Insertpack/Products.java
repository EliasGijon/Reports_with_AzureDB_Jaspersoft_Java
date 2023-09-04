package sql_Insertpack;

public class Products {
	private String productId;
    private String name;
    private float price;
    private int existence;
    private String stationeryId;
    
    // Getter method for productId
    public String getProductId() {
        return productId;
    }

    // Setter method for productId
    public void setProductId(String productId) {
        this.productId = productId;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Setter method for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for price
    public float getPrice() {
        return price;
    }

    // Setter method for price
    public void setPrice(float price) {
        this.price = price;
    }

    // Getter method for existence
    public int getExistence() {
        return existence;
    }

    // Setter method for existence
    public void setExistence(int existence) {
        this.existence = existence;
    }

    // Getter method for stationeryId
    public String getStationeryId() {
        return stationeryId;
    }

    // Setter method for stationeryId
    public void setStationeryId(String stationeryId) {
        this.stationeryId = stationeryId;
    }
}
