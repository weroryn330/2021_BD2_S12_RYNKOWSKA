export class UserResponse {
  firstName: string;
  lastName: string;
  address: string;
  city: string;
  voivodeship: string;
  country: string;
  email: string;
  phone: string;
  postalCode: string;


  constructor(user: any) {
    this.firstName = user.firstName;
    this.lastName = user.lastName;
    this.address = user.address;
    this.city = user.city;
    this.voivodeship = user.voivodeship;
    this.country = user.country;
    this.email = user.email;
    this.phone = user.phone;
    this.postalCode = user.postalCode;
  }
}
