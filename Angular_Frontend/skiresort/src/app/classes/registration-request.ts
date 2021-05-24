export class RegistrationRequest {
  firstName: string;
  lastName: string;
  address: string;
  city: string;
  voivodeship: string;
  country: string;
  email: string;
  phone: string;
  postalCode: string;
  password: string;


  constructor(firstName: string, lastName: string, country: string, email: string, phone: string,
              voivodeship: string, postalCode: string, city: string, address: string, password: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.country = country;
    this.email = email;
    this.phone = phone;
    this.voivodeship = voivodeship;
    this.postalCode = postalCode;
    this.city = city;
    this.address = address;
    this.password = password;
  }

}
