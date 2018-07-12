package hello;

import hello.model.Bill;
import hello.model.Position;
import hello.model.SummerizedPosition;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Controller
public class ExcelConverter {

    @GetMapping("/convert")
    public void transformExcel(Model model) throws IOException, InvalidFormatException {

        String path = "/Users/maex/Documents/Firma/Projekte/Phoenix Contact/2018/";
        Workbook wb = new XSSFWorkbook(new File(path + "Leistungsnachweis_mbaumann_2018_06_v8.3.xlsm"));
        DataFormatter formatter = new DataFormatter();
        PrintStream out = new PrintStream(new FileOutputStream(path + "Leistungsnachweis_mbaumann_2018_06_v8.3.csv"),
                true, "UTF-8");
        Bill bill = new Bill();
        for (Sheet sheet : wb) {
            if (sheet.getSheetName().equals("TimesheetTasks")) {
                for (Row row : sheet) {
                    boolean firstCell = true;
                    for (Cell cell : row) {
                        if (!firstCell) out.print(',');
                        String text = formatter.formatCellValue(cell);
                        out.print(text);
                        firstCell = false;

                        if (text.equals("Bestellnummer:")) {
                            bill.setOrderNumber(formatter.formatCellValue(row.getCell(2)));

                        } else if (text.equals("Projektbezeichnung:")) {
                            bill.setProjectDescription(formatter.formatCellValue(row.getCell(2)));

                        } else if (text.contains("Gesamtergebnis")) {
                            bill.setTimeSummary(formatter.formatCellValue(row.getCell(2)));

                        } else if (isValidDatum(text)) {
                            Position singlePosition = new Position();
                            bill.getSinglePositions().add(singlePosition);
                            String cellValue = formatter.formatCellValue(row.getCell(2));
                            if (cellValue.contains(" - ")) {
                                cellValue = cellValue.substring(0, cellValue.indexOf(" - "));
                            }

                            singlePosition.setDescription(cellValue);
                            singlePosition.setTimeStr(formatter.formatCellValue(row.getCell(3)));
                        }


                    }
                    out.println();

                }
                System.out.println(bill);
                for (Position position : bill.getSinglePositions()) {
                    String timeStr = position.getTimeStr();
                    BigDecimal timeSpend = new BigDecimal(timeStr);
                    if (bill.getSummerizedPositions().containsKey(position.getDescription())) {
                        SummerizedPosition sp = bill.getSummerizedPositions().get(position.getDescription());
                        sp.setTimeSpend(sp.getTimeSpend().add(timeSpend));
                    } else {
                        SummerizedPosition sp = new SummerizedPosition();
                        sp.setDescription(position.getDescription());
                        sp.setTimeSpend(timeSpend);
                        bill.getSummerizedPositions().put(position.getDescription(), sp);
                    }
                }
                for (SummerizedPosition sp : bill.getSummerizedPositions().values()) {
                    System.out.println(sp.getDescription() + " : " + sp.getTimeSpend().toPlainString());
                }
                System.out.println("Gesamtsumme:"+bill.getTimeSummary());
            }

        }

    }

    private boolean isValidDatum(String text) {

        SimpleDateFormat sdf = new SimpleDateFormat("mm/DD/YY");
        try {
            if (!text.isEmpty()) {
                Date date = sdf.parse(text);
                if (date.before(Date.from(Instant.now()))) {
                    return true;
                }
            }
        } catch (ParseException e) {
            // e.printStackTrace();

        }
        return false;
    }
}
