import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String address;
    private int contactNumber;
    private String dateOfBirth;
    private String gender;

    //connection Object
    Connection conn = DBConnect.connect();

    //constructor
    public Customer() {

    }

    public Customer(int id) {
        this.id = id;
    }

    public Customer(String name, String email, String address, int contactNumber, String dateOfBirth, String gender) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.contactNumber = contactNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    //ADD NEW CUSTOMER

    public boolean addNewCustomer() {

        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO customer (name, email, address, contact_no, dob, gender) VALUES (?, ?, ?, ?, ?, ?)");
            pst.setString(1,this.name);
            pst.setString(2,this.email);
            pst.setString(3,this.address);
            pst.setInt(4,this.contactNumber);
            pst.setString(5,this.dateOfBirth);
            pst.setString(6,this.gender);
            pst.executeUpdate();
            System.out.println("Customer saved to database");
            return  true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isCustomerSaved(int id) {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM customer WHERE id = ?");
            pst.setInt(1,id);
            ResultSet rst = pst.executeQuery();
            if(rst.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Customer getCustomerById(int customerId){
        String sql = "SELECT * FROM customer WHERE customer_id = ?";

        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM customer WHERE id = ?");
            ResultSet rst = pst.executeQuery();
            if(rst.next()) {
                int id = rst.getInt(1);
                String name = rst.getString(2);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try(PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1,customerId);

            try (ResultSet resultSet = statement.executeQuery()){

                if (resultSet.next()){
                    Customer customer = new Customer();
                    customer.id = resultSet.getInt("");
                    customer.setName(resultSet.getString("customer_name"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setAddress(resultSet.getString("address"));
                    customer.setContactNumber(resultSet.getInt("contact_no"));
                    customer.setGender(resultSet.getString("gender"));
                    return customer;
                }
            }

        }catch (SQLException e){

            System.out.println(e.getMessage());
        }


        return null;
    }

    //UPDATE CUSTOMER BY ID
    public boolean updateCustomerById() {

        String updateSQL = "UPDATE customer SET customer_name = ?, email = ?, address = ?, contact_no = ?, dob = ?, gender = ? WHERE customer_id = ?";

        try (PreparedStatement statement = conn.prepareStatement(updateSQL)) {
            statement.setString(1, this.name);
            statement.setString(2, this.email);
            statement.setString(3, this.address);
            statement.setInt(4, this.contactNumber);
            statement.setString(5, this.dateOfBirth);
            statement.setString(6, this.gender);
            statement.setInt(7, this.id);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //SEARCH AND VIEW CUSTOMER BY TAG METHOD
    public static List<Customer> searchAndShowCustomer(Connection conn, String searchTag) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String searchSql = "SELECT * FROM customer WHERE name = ? OR id = ?";

        try (PreparedStatement statement = conn.prepareStatement(searchSql)) {

            statement.setString(1, searchTag);
            statement.setString(2, searchTag);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.id = (resultSet.getInt("customer_id"));
                    customer.name = (resultSet.getString("customer_name"));
                    customer.email = (resultSet.getString("email"));
                    customer.address = (resultSet.getString("address"));
                    customer.contactNumber = (resultSet.getInt("contact_no"));
                    customer.setDateOfBirth(resultSet.getString("dob"));
                    customer.setGender(resultSet.getString("gender"));
                    customers.add(customer);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());


        }


        return customers;
    }




    //DELETE CUSTOMER

    public boolean deleteCustomer() {

        try{
            String deleteSql = "DELETE FROM customer WHERE customer_id = ?";
            PreparedStatement statement = conn.prepareStatement(deleteSql);
            statement.setInt(1,this.id);

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;


        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}