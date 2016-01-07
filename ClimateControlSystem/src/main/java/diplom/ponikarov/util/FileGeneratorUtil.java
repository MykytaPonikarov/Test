package diplom.ponikarov.util;

import diplom.ponikarov.entity.ClimateData;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component("fileGenerator")
public class FileGeneratorUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileGeneratorUtil.class);

    @Value("${generateFileStorageLocation}")
    private String dir;
    @Value("${dateFormatForFile}")
    private String dateFormat;

    public void generateFile(List<ClimateData> content) {
        LOGGER.debug("Generate file...");
        XWPFDocument document = new XWPFDocument();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        try {
            File dirFile = new File(dir);
            if (!dirFile.isDirectory()) {
                dirFile.mkdirs();
            }
            String pathname = dirFile.getAbsolutePath() + File.separator + simpleDateFormat.format(new Date()) + ".docx";
            LOGGER.debug("File path name ---> {}", pathname);
            File outputFile = new File(pathname);
            outputFile.createNewFile();

            FileOutputStream out = new FileOutputStream(outputFile);
            XWPFTable table = document.createTable();

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
            LOGGER.warn("Exception when generate file with climate data. Exception: {}", e);
        }
    }
}
