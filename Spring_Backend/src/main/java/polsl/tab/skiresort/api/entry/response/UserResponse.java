package polsl.tab.skiresort.api.entry.response;

import polsl.tab.skiresort.model.User;

public class UserResponse {
    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private String voivodeship;

    private String country;

    private String postalCode;

    private String phone;

    private String email;

    private String token;

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

}
