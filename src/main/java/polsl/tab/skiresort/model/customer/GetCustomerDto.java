package polsl.tab.skiresort.model.customer;

import polsl.tab.skiresort.model.employee.Employee;
import polsl.tab.skiresort.model.invoice.Invoice;

import java.util.List;

public class GetCustomerDto {

    private final Integer idCustomer;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String city;
    private final String voivodeship;
    private final String country;
    private final String postalCode;
    private final String phone;
    private final String email;
    private final String password;
    private final Employee supportEmployee;
    private final List<Invoice> invoiceList;

    public GetCustomerDto(Customer customerEntity) {
        this.firstName = customerEntity.getFirstName();
        this.lastName = customerEntity.getLastName();
        this.address = customerEntity.getAddress();
        this.city = customerEntity.getCity();
        this.voivodeship = customerEntity.getVoivodeship();
        this.country = customerEntity.getCountry();
        this.postalCode = customerEntity.getPostalCode();
        this.phone = customerEntity.getPhone();
        this.email = customerEntity.getEmail();
        this.idCustomer = customerEntity.getIdCustomer();
        this.password = customerEntity.getPassword();
        this.supportEmployee = customerEntity.getSupportEmployee();
        this.invoiceList = customerEntity.getInvoiceList();
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public  Employee getSupportEmployee() {
        return supportEmployee;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }
}
