package ng.Pharmacy.dtos.requests;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddDrugRequests {

    private String name;
    private String brand;
    private int price;
    private LocalDate expiryDate;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.expiryDate = LocalDate.parse(expiryDate, formatter);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
