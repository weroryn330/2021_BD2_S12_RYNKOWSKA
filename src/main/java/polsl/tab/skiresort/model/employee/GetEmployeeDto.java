package polsl.tab.skiresort.model.employee;

import polsl.tab.skiresort.model.customer.Customer;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

public class GetEmployeeDto {

    private final Integer idEmployee;
    private final String firstName;
    private final String lastName;
    private final String title;
    private final Date birthDate;
    private final Date hireDate;
    private final String address;
    private final String city;
    private final String voivodeship;
    private final String country;
    private final String postalCode;
    private final String phone;
    private final String email;
    private final String password;
    private final Employee reportsTo;
    private final Collection<Employee> children;
    private final List<Customer> customerList;

    public GetEmployeeDto(Employee employee) {
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.title = employee.getTitle();
        this.birthDate = employee.getBirthDate();
        this.hireDate = employee.getHireDate();
        this.address = employee.getAddress();
        this.city = employee.getCity();
        this.idEmployee = employee.getIdEmployee();
        this.voivodeship = employee.getVoivodeship();
        this.country = employee.getCountry();
        this.postalCode = employee.getPostalCode();
        this.phone = employee.getPhone();
        this.email = employee.getEmail();
        this.password = employee.getPassword();
        this.reportsTo = employee.getReportsTo();
        this.children = employee.getChildren();
        this.customerList = employee.getCustomerList();
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getHireDate() {
        return hireDate;
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

    public  String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Employee getReportsTo() {
        return reportsTo;
    }

    public Collection<Employee> getChildren() {
        return children;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }
}
