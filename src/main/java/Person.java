import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Person {
    private PersonalData personalData_;
    private Location location_;

    Person(PersonalData personalData, Location location) {
        this.personalData_ = personalData;
        this.location_ = location;
    }

    public PersonalData getPersonalData() {
        return this.personalData_;
    }

    public Location getLocation() {
        return this.location_;
    }

    public ArrayList<String> toArrayListOfString() {
        ArrayList<String> personAsAL = new ArrayList<>();

        personAsAL.add(personalData_.firstName);
        personAsAL.add(personalData_.lastName);
        personAsAL.add(personalData_.patronymic);
        personAsAL.add(String.valueOf(personalData_.age));

        if (personalData_.sex == Sex.Female)
            personAsAL.add("Ж");
        else
            personAsAL.add("М");

        personAsAL.add(personalData_.dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        personAsAL.add(personalData_.placeOfBirth);
        personAsAL.add(location_.zipCode);
        personAsAL.add(location_.country);
        personAsAL.add(location_.region);
        personAsAL.add(location_.city);
        personAsAL.add(location_.street);
        personAsAL.add(String.valueOf(location_.houseNo));
        personAsAL.add(String.valueOf(location_.apartmentNo));

        return personAsAL;
    }
}
