package musicplayer.model;

/**
 * Created by Federica on 26/04/2017.
 */
public class Address {

    private String firstName;
    private String lastName;
    private String streetNameAndNumber;
    private String city;
    private String postalCode;
    private Country country;

    public Address(String firstName, String lastName, String streetNameAndNumber, String city, Country country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetNameAndNumber = streetNameAndNumber;
        this.city = city;
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetNameAndNumber() {
        return streetNameAndNumber;
    }

    public void setStreetNameAndNumber(String streetNameAndNumber) {
        this.streetNameAndNumber = streetNameAndNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
