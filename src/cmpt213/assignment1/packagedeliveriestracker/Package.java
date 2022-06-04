package cmpt213.assignment1.packagedeliveriestracker;

import java.time.LocalDateTime;

/**
 * This class holds information for a package
 * @author Nhat Huy Phan (James)
 */
public class Package implements Comparable<Package> {

    private String name;
    private String notes;
    private double priceInDollar;
    private double weightInKg;
    private boolean delivered;
    private LocalDateTime expectedDeliveryDate;

    public Package(String name, String notes, double priceInDollar, double weightInKg, LocalDateTime expectedDeliveryDate) {
        this.name = name;
        this.notes = notes;
        this.priceInDollar = priceInDollar;
        this.weightInKg = weightInKg;
        this.delivered = false;
        this.expectedDeliveryDate = expectedDeliveryDate;
    }


    /**
     * Set name of the package
     * @param name Name of the package
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set notes of the package
     * @param notes Notes for the package
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Set price of the package
     * @param priceInDollar price of the package
     */
    public void setPriceInDollar(double priceInDollar) {
        this.priceInDollar = priceInDollar;
    }

    /**
     * Set weight of the package
     * @param weightInKg Weight of the package
     */
    public void setWeightInKg(double weightInKg) {
        this.weightInKg = weightInKg;
    }

    /**
     * Mark as delivered or undelivered of the package
     * @param delivered Delivery status of the package
     */
    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    /**
     * Set expected delivery of the package
     * @param expectedDeliveryDate Expected delivery date
     */
    public void setExpectedDeliveryDate(LocalDateTime expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }


    /**
     * Get name of the package
     * @return Name of the package
     */
    public String getName() {
        return name;
    }

    /**
     * Get notes of the package
     * @return Notes of the package
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Get weight of the package
     * @return Weight of the package
     */
    public double getWeightInKg() {
        return weightInKg;
    }

    /**
     * Get price of the package
     * @return Price of the package
     */
    public double getPriceInDollar() {
        return priceInDollar;
    }

    /**
     * Get delivery status of the package
     * @return Delivery status of the package
     */
    public boolean isDelivered() {
        return delivered;
    }

    /**
     * Get expected delivery date of the package
     * @return Expected delivery date of the package
     */
    public LocalDateTime getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    /**
     * Print the information of the package
     * @return Information of the package
     */
    @Override
    public String toString() {
        String deliveryStatus = "No";
        if (this.delivered){
            deliveryStatus = "Yes";
        }
        return "Package: " + this.name + " \n" +
                "Notes: " + this.notes + "\n" +
                "Price: $" + this.priceInDollar + "\n" +
                "Weight: " + this.weightInKg + "kg \n" +
                "Expected Delivery Date: " + this.expectedDeliveryDate + "\n" +
                "Delivered? " + deliveryStatus + "\n";
    }

    /**
     * Print the information of the package
     */
    public void printPackage(){
        System.out.println("Package: " + this.name);
        System.out.println("Notes: " + this.notes);
        System.out.println("Price: $" + this.priceInDollar);
        System.out.println("Weight: " + this.weightInKg + "kg");
        System.out.println("Expected Delivery Date: " +this.expectedDeliveryDate );
        String deliveryStatus = "No";
        if (this.delivered){
            deliveryStatus = "Yes";
        }
        System.out.println("Delivered? " + deliveryStatus);
    }

    @Override
    public int compareTo(Package o) {
        return this.expectedDeliveryDate.compareTo(o.expectedDeliveryDate);
    }
}
