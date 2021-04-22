package polsl.tab.skiresort.model.customer;

import polsl.tab.skiresort.model.employee.Employee;
import polsl.tab.skiresort.model.invoice.Invoice;

import java.util.List;

public class PostCustomerDto {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String voivodeship;
    private String country;
    private String postalCode;
    private String phone;
    private String email;
    private String password;
    private Employee supportEmployee;
    private List<Invoice> invoiceList;

    public PostCustomerDto() {}

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public void setVoivodeship(final String voivodeship) {
        this.voivodeship = voivodeship;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setSupportEmployee(final Employee supportEmployee) {
        this.supportEmployee = supportEmployee;
    }

    public void setInvoiceList(final List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public Customer toEntity() {
        return Customer
                .builder()
                .setAddress(this.address)
                .setCity(this.city)
                .setCountry(this.country)
                .setFirstName(this.firstName)
                .setEmail(this.email)
                .setInvoiceList(this.invoiceList)
                .setLastName(this.lastName)
                .setPassword(this.password)
                .setPhone(this.phone)
                .setPostalCode(this.postalCode)
                .setSupportEmployee(this.supportEmployee)
                .setVoivodeship(this.voivodeship)
                .build();
    }
}
