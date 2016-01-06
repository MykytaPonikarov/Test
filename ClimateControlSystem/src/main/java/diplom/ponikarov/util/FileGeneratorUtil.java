package diplom.ponikarov.util;

import diplom.ponikarov.entity.ClimateData;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FileGeneratorUtil {

    @Value("${generateFileStorageLocation}")
    private String dir;
    @Value("${dateFormatForFile}")
    private String dateFormat;

    public void generateFile(List<ClimateData> content) {
        //Blank Document
        XWPFDocument document = new XWPFDocument();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        try {
            //Write the Document in file system
            File dirFile = new File(dir);
            if (!dirFile.isDirectory()) {
                dirFile.mkdirs();
            }
            File outputFile = new File(dirFile.getAbsolutePath() + File.separator + simpleDateFormat.format(new Date()) + ".docx");
            outputFile.createNewFile();
            FileOutputStream out = new FileOutputStream(
                    outputFile);

            //create table
            XWPFTable table = document.createTable();

            //create first row
            XWPFTableRow tableRowOne = table.getRow(0);
            tableRowOne.getCell(0).setText("Controller number");
            tableRowOne.addNewTableCell().setText("Status");
            tableRowOne.addNewTableCell().setText("Temperature");
            tableRowOne.addNewTableCell().setText("Humidity");
            tableRowOne.addNewTableCell().setText("Date");

            for (ClimateData climateData : content) {
                XWPFTableRow row = table.createRow();
                row.getCell(0).setText(String.valueOf(climateData.getControllerNumber()));
                row.getCell(1).setText(climateData.getStatus());
                row.getCell(2).setText(String.valueOf(climateData.getTemperature()));
                row.getCell(3).setText(String.valueOf(climateData.getHumidity()));
                row.getCell(4).setText(String.valueOf(climateData.getDate()));
            }

            document.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
