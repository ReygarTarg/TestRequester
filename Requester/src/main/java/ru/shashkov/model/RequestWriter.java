package ru.shashkov.model;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class RequestWriter {
    private String request;
    private String path;
    private final HashMap<Integer, ArrayList<String>> result = new HashMap<>();

    public RequestWriter(String request, String path) {
        this.path = path;
        this.request = request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRequest() {
        return request;
    }

    public String getPath() {
        return path;
    }

    public HashMap<Integer, ArrayList<String>> prepareRequests() {
        XSSFWorkbook workbook = null;
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(path);
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert workbook != null;

        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();
        int i = 0;

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cells = row.iterator();
            ArrayList<String> text = new ArrayList<>();

            while (cells.hasNext()) {
                Cell cell = cells.next();
                CellType cellType = cell.getCellType();

                switch (cellType) {
                    case STRING -> {
                        text.add("\"" + cell.getStringCellValue() + "\"");
                        result.put(i, text);
                    }
                    case NUMERIC -> {
                        text.add(String.valueOf(cell.getNumericCellValue()));
                        result.put(i, text);
                    }
                    default -> {
                        text.add("?");
                        result.put(i, text);
                    }
                }
            }
            i++;
        }
        return result;
    }


    public void writeRequests() {
        try (FileWriter writer = new FileWriter("result.sql", false)) {
            String request = getRequest();
            prepareRequests();
            for (int i = 0; i < result.size(); i++) {
                String data = request;
                for (int j = 0; j < result.get(i).size(); j++) {
                    data = data.replaceFirst("\\{some}", result.get(i).get(j));
                }
                writer.write(data);
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}
