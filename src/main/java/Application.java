import com.itextpdf.text.DocumentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    public static void main(String[] args) {
        String[] headers = {
                "Имя", "Фамилия", "Отчество", "Возраст", "Пол", "Дата рождения", "Место рождения",
                "Индекс", "Страна", "Область", "Город", "Улица", "Дом", "Квартира"
        };
        String fileName = "persons";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = getInt(reader);

        try {
            PersonGenerator pg = new PersonGenerator();
            ExcelHandler excelHandler = new ExcelHandler(fileName);
            excelHandler.setHeaders(headers);
            PdfHandler pdfHandler = new PdfHandler(fileName);
            pdfHandler.setHeaders(headers);

            for (int i = 0; i < n; i++) {
                Person person = pg.generatePerson();
                excelHandler.addPerson(person.toArrayListOfString());
                pdfHandler.addPerson(person.toArrayListOfString());
            }

            excelHandler.close();
            pdfHandler.close();
        } catch (IOException | DocumentException ex) {
            System.out.println("Something goes wrong:");
            ex.printStackTrace();
        }
    }

    private static int getInt(BufferedReader reader) throws NumberFormatException {
        int n;
        while (true) {
            System.out.print("Enter integer number greater than 1 and less than 30: ");
            try {
                n = Integer.parseInt(reader.readLine());
                if (1 <= n && n <= 30)
                    break;
            } catch (IOException | NumberFormatException ex) {
                System.out.println("Something goes wrong: " + ex.getMessage());
                System.out.println("Try again.");
            }
        }
        return n;
    }
}
