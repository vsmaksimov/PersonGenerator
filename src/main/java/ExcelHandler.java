import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelHandler extends FileHandler {
    private final static String EXTENSION_ = ".xls";
    private HSSFWorkbook workbook_;
    private Sheet sheet_;
    private int rowNumber_;

    ExcelHandler(String fileName) throws IOException {
        this.fileName = FOLDER_NAME + File.separator + fileName + EXTENSION_;

        workbook_ = new HSSFWorkbook();
        sheet_ = workbook_.createSheet();
        rowNumber_ = 0;
    }

    @Override
    public void setHeaders(String[] headers) {
        Row currentRow = sheet_.createRow(rowNumber_);
        rowNumber_++;

        for (int i = 0; i < headers.length; i++) {
            Cell currentCell = currentRow.createCell(i);
            currentCell.setCellValue(headers[i]);
        }
    }

    @Override
    public void addPerson(ArrayList<String> personData) throws IOException {
        Row currentRow = sheet_.createRow(rowNumber_);
        rowNumber_++;

        int personDataSize = personData.size();
        for (int i = 0; i < personDataSize; i++) {
            Cell currentCell = currentRow.createCell(i);
            currentCell.setCellValue(personData.get(i));
            sheet_.autoSizeColumn(i);
        }

        workbook_.write(new FileOutputStream(fileName));
    }

    @Override
    public void close() throws IOException {
        workbook_.close();
        System.out.println("Excel file '" + fileName + "' is generated.");
    }
}
