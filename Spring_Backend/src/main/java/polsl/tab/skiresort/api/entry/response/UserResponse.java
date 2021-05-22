package polsl.tab.skiresort.api.entry.response;

import polsl.tab.skiresort.model.User;

public class UserResponse {
    private final String firstName;

    private final String lastName;

    private final String address;

    private final String city;

    private final String voivodeship;

    private final String country;

    private final String postalCode;

    private final String phone;

    private final String email;

    private final String token;

    public UserResponse(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.city = user.getCity();
        this.voivodeship = user.getVoivodeship();
        this.country = user.getCountry();
        this.postalCode = user.getPostalCode();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.token = "Log in to acquire token!";
    }

    public UserResponse(User user, String token) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.city = user.getCity();
        this.voivodeship = user.getVoivodeship();
        this.country = user.getCountry();
        this.postalCode = user.getPostalCode();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.token = token;
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

    public String getToken() {
        return token;
    }
}
