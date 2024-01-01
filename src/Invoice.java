import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Invoice {
    private Date invoiceDate;
    private String customerName;
    private List<InvoiceItem> items = new ArrayList<>();
    private int unitPerProduct;

    private float unitPricePerProduct;

    private float totalPricePerProduct;

    private float discountPrice; //based on quantity

    public Invoice(String customerName) {
        this.invoiceDate = new Date();
        this.customerName = customerName;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }

    public int getUnitPerProduct() {
        return unitPerProduct;
    }

    public void setUnitPerProduct(int unitPerProduct) {
        this.unitPerProduct = unitPerProduct;
    }

    public float getUnitPricePerProduct() {
        return unitPricePerProduct;
    }

    public void setUnitPricePerProduct(float unitPricePerProduct) {
        this.unitPricePerProduct = unitPricePerProduct;
    }

    public float getTotalPricePerProduct() {
        return totalPricePerProduct;
    }

    public void setTotalPricePerProduct(float totalPricePerProduct) {
        this.totalPricePerProduct = totalPricePerProduct;
    }

    public float getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(float discountPrice) {
        this.discountPrice = discountPrice;
    }

    public void addItem(Product product, int units, double unitPrice, double discount) {
        InvoiceItem item = new InvoiceItem(product, units, unitPrice, discount);
        items.add(item);
        // Update product quantity based on the sold units
        product.updateQuantity(-units);
    }


    public void generateInvoice(){}

    public void addInvoiceToDatabase(){}


}
