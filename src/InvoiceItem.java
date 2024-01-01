public class InvoiceItem {


    private Product product;
    private int units;
    private double unitPrice;
    private double discount;
    private double totalPrice;

    public InvoiceItem(Product product, int units, double unitPrice, double discount) {
        this.product = product;
        this.units = units;
        this.unitPrice = unitPrice;
        this.discount = discount;
        calculateTotalPrice();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    private void calculateTotalPrice() {
        // Implement logic to calculate the total price for this item after applying discount
         totalPrice = (unitPrice * units) - discount;
    }

}
