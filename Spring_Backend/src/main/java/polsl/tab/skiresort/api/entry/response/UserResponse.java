package polsl.tab.skiresort.api.entry.response;

import polsl.tab.skiresort.model.Role;
import polsl.tab.skiresort.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserResponse {
    private final Integer id;

    private final String firstName;

    private final String lastName;

    private final String address;

    private final String city;

    private final String voivodeship;

    private final String country;

    private final String postalCode;

    private final String phone;

    private final String email;

    private final List<String> roleList;

    private final String token;

    public UserResponse(User user) {
        this.id = user.getIdUser();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.city = user.getCity();
        this.voivodeship = user.getVoivodeship();
        this.country = user.getCountry();
        this.postalCode = user.getPostalCode();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.roleList = user.getRoleList().stream().map(Role::getRoleName).collect(Collectors.toList());
        this.token = "Log in to acquire token!";
    }

    public UserResponse(User user, String token) {
        this.id = user.getIdUser();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.city = user.getCity();
        this.voivodeship = user.getVoivodeship();
        this.country = user.getCountry();
        this.postalCode = user.getPostalCode();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.roleList = user.getRoleList().stream().map(Role::getRoleName).collect(Collectors.toList());
        this.token = token;
    }

    public Integer getId() {
        return id;
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

    public List<String> getRoleList() {
        return roleList;
    }

    public String getToken() {
        return token;
    }
}
