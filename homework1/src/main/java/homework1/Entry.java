package homework1;

public class Entry implements Comparable<Entry> {
    private String surname;
    private String name;
    private String street_address;
    private String city;
    private String postcode;
    private String phone_number;
    private String country;

    public Entry(String name, String surname, String street_address, String city, String postcode, String phone_number, String country) {
        this.surname = surname;
        this.name = name;
        this.street_address = street_address;
        this.city = city;
        this.postcode = postcode;
        this.phone_number = phone_number;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getStreet_address() {
        return street_address;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getCountry() {
        return country;
    }

    public String getFullName() {
        return this.surname + ", " + this.name;
    }

    @Override
    public String toString() {
        return "Name: " + getFullName() + '\n' +
                "Street address: " + street_address + '\n' +
                "City: " + city + '\n' +
                "Postal code: " + postcode + '\n' +
                "country: " + country + '\n' +
                "Phone number: " + phone_number + '\n';
    }

    public String getLine() {
        return surname + ", " + name + ";" +
                street_address + ";" +
                city + ";" +
                postcode + ";" +
                country + ";" +
                phone_number;
    }

    @Override
    public int compareTo(Entry other) {
        String thisName = this.getFullName();
        String otherName = other.getFullName();

        return thisName.compareTo(otherName);
    }
}