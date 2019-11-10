import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class PersonGenerator {
    private static final String FOLDER_NAME = "src\\main\\resources";
    private static final String FEMALE_FIRST_NAMES = FOLDER_NAME + File.separator + "female_firstname.txt";
    private static final String MALE_FIRST_NAMES = FOLDER_NAME + File.separator + "male_firstname.txt";
    private static final String FEMALE_LAST_NAMES = FOLDER_NAME + File.separator + "female_lastname.txt";
    private static final String MALE_LAST_NAMES = FOLDER_NAME + File.separator + "male_lastname.txt";
    private static final String FEMALE_PATRONYMICS = FOLDER_NAME + File.separator + "female_patronymic.txt";
    private static final String MALE_PATRONYMICS = FOLDER_NAME + File.separator + "male_patronymic.txt";
    private static final String COUNTRIES = FOLDER_NAME + File.separator + "countries.txt";
    private static final String REGIONS = FOLDER_NAME + File.separator + "regions.txt";
    private static final String CITIES = FOLDER_NAME + File.separator + "cities.txt";
    private static final String STREETS = FOLDER_NAME + File.separator + "streets.txt";

    private static final long JANUARY_01_1940 = -946771200000L;

    private ArrayList<String> femaleFirstNames_;
    private ArrayList<String> femaleLastNames_;
    private ArrayList<String> femalePatronymics_;
    private ArrayList<String> maleFirstNames_;
    private ArrayList<String> maleLastNames_;
    private ArrayList<String> malePatronymics_;
    private ArrayList<String> countries_;
    private ArrayList<String> regions_;
    private ArrayList<String> cities_;
    private ArrayList<String> streets_;

    private Random random_;

    PersonGenerator() throws IOException {
        random_ = new Random(System.currentTimeMillis());

        femaleFirstNames_ = readDataFromFile(FEMALE_FIRST_NAMES);
        maleFirstNames_ = readDataFromFile(MALE_FIRST_NAMES);
        femaleLastNames_ = readDataFromFile(FEMALE_LAST_NAMES);
        maleLastNames_ = readDataFromFile(MALE_LAST_NAMES);
        femalePatronymics_ = readDataFromFile(FEMALE_PATRONYMICS);
        malePatronymics_ = readDataFromFile(MALE_PATRONYMICS);
        countries_ = readDataFromFile(COUNTRIES);
        regions_ = readDataFromFile(REGIONS);
        cities_ = readDataFromFile(CITIES);
        streets_ = readDataFromFile(STREETS);
    }

    private ArrayList<String> readDataFromFile(String fileName) throws IOException {
        ArrayList<String> data = new ArrayList<>();
        Files.lines(Paths.get(fileName), StandardCharsets.UTF_8).forEach(data::add);
        return data;
    }

    private PersonalData generatePersonalData() {
        PersonalData personalData = new PersonalData();

        int sex = random_.nextInt(2);
        if (sex == 0) {
            personalData.sex = Sex.Female;
            generateName(personalData, femaleFirstNames_, femaleLastNames_, femalePatronymics_);
        } else {
            personalData.sex = Sex.Male;
            generateName(personalData, maleFirstNames_, maleLastNames_, malePatronymics_);
        }

        personalData.dateOfBirth = generateDateOfBirth();
        personalData.age = Period.between(personalData.dateOfBirth, LocalDate.now()).getYears();
        personalData.placeOfBirth = cities_.get(random_.nextInt(cities_.size()));

        return personalData;
    }

    private void generateName(PersonalData personalData, ArrayList<String> firstNames,
                              ArrayList<String> lastNames, ArrayList<String> patronymics) {
        personalData.firstName = firstNames.get(random_.nextInt(firstNames.size()));
        personalData.lastName = lastNames.get(random_.nextInt(lastNames.size()));
        personalData.patronymic = patronymics.get(random_.nextInt(patronymics.size()));
    }

    private LocalDate generateDateOfBirth() {
        long ms = JANUARY_01_1940 + Math.abs(random_.nextLong()) % (60L * 365 * 24 * 60 * 60 * 1000);
        Date dateOfBirth = new Date(ms);
        return dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private Location generateLocation() {
        Location location = new Location();

        location.zipCode = String.valueOf(100000 + random_.nextInt(999999 - 100000 + 1));
        location.country = countries_.get(random_.nextInt(countries_.size()));
        location.region = regions_.get(random_.nextInt(regions_.size()));
        location.city = cities_.get(random_.nextInt(cities_.size()));
        location.street = streets_.get(random_.nextInt(streets_.size()));
        location.houseNo = random_.nextInt(100) + 1;
        location.apartmentNo = random_.nextInt(100) + 1;

        return location;
    }

    public Person generatePerson() {
        return new Person(generatePersonalData(), generateLocation());
    }
}
