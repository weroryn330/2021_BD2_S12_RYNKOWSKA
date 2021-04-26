package polsl.tab.skiresort.model.employee;

import polsl.tab.skiresort.model.customer.Customer;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

public class PostEmployeeDto {

    private Integer idEmployee;
    private String firstName;
    private String lastName;
    private String title;
    private Date birthDate;
    private Date hireDate;
    private String address;
    private String city;
    private String voivodeship;
    private String country;
    private String postalCode;
    private String phone;
    private String email;
    private String password;
    private Employee reportsTo;
    private Collection<Employee> children;
    private List<Customer> customerList;

    public PostEmployeeDto() {}

    public PostEmployeeDto(Employee employee) {
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

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(final Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(final String voivodeship) {
        this.voivodeship = voivodeship;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Employee getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(final Employee reportsTo) {
        this.reportsTo = reportsTo;
    }

    public Collection<Employee> getChildren() {
        return children;
    }

    public void setChildren(final Collection<Employee> children) {
        this.children = children;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(final List<Customer> customerList) {
        this.customerList = customerList;
    }

    public Employee toEntity() {
        return Employee
                .builder()
                .setAddress(this.address)
                .setCity(this.city)
                .setCountry(this.country)
                .setEmail(this.email)
                .setBirthDate(this.birthDate)
                .setChildren(this.children)
                .setFirstName(this.firstName)
                .setPassword(this.password)
                .setPhone(this.phone)
                .setPostalCode(this.postalCode)
                .setLastName(this.lastName)
                .setCustomerList(this.customerList)
                .setVoivodeship(this.voivodeship)
                .setHireDate(this.hireDate)
                .setReportsTo(this.reportsTo)
                .setTitle(this.title)
                .build();
    }
}
