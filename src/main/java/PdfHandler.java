import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PdfHandler extends FileHandler {
    private final static String EXTENSION_ = ".pdf";
    private final static String FONT_PATH = "font\\TimesTenRoman.ttf";
    private Document document_;
    private PdfPTable table_;
    private Font tableFont_;

    PdfHandler(String fileName) throws IOException, DocumentException {
        this.fileName = FOLDER_NAME + File.separator + fileName + EXTENSION_;

        document_ = new Document(PageSize.A3.rotate());
        PdfWriter.getInstance(document_, new FileOutputStream(this.fileName));
        document_.open();

        table_ = new PdfPTable(1);
        tableFont_ = new Font(BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, true));
        tableFont_.setSize(12);
    }

    @Override
    public void setHeaders(String[] headers) {
        table_.resetColumnCount(headers.length);
        table_.setTotalWidth(PageSize.A3.rotate().getWidth());
        table_.setLockedWidth(true);
        for (String header : headers) {
            table_.addCell(new Phrase(header, tableFont_));
        }
    }

    @Override
    public void addPerson(ArrayList<String> personData) {
        for (String data : personData) {
            table_.addCell(new PdfPCell(new Phrase(data, tableFont_)));
        }
    }

    @Override
    public void close() throws DocumentException {
        document_.add(table_);
        document_.close();
        System.out.println("PDF file '" + fileName + "'is generated.");
    }
}
