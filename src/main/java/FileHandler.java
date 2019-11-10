import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public abstract class FileHandler {
    protected final static String FOLDER_NAME = "generated";
    protected String fileName;

    FileHandler() throws IOException {
        if (!Files.exists(Paths.get(FOLDER_NAME)))
            Files.createDirectory(Paths.get(FOLDER_NAME));
    }

    public abstract void setHeaders(String[] headers) throws IOException;

    public abstract void addPerson(ArrayList<String> personData) throws IOException;

    public abstract void close() throws IOException, DocumentException;
}
