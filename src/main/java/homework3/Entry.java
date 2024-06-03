package homework3;

public class Entry implements Comparable<Entry> {
    private final String surname;
    private final String name;
    private final String street_address;
    private final String city;
    private final String postcode;
    private final String phone_number;
    private final String country;

    public Entry(String name, String surname, String street_address, String city, String postcode, String phone_number, String country) {
        this.surname = surname;
        this.name = name;
        this.street_address = street_address;
        this.city = city;
        this.postcode = postcode;
        this.phone_number = phone_number;
        this.country = country;
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