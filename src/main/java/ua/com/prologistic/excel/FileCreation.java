package ua.com.prologistic.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import ua.com.prologistic.model.Data;

import javax.naming.InsufficientResourcesException;
import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class FileCreation {
    public static void main(String[] args) throws ParseException, IOException {

        // создание самого excel файла
        HSSFWorkbook workbook = new HSSFWorkbook();
        // создание листа с названием "Просто лист"
        HSSFSheet sheet = workbook.createSheet("Просто лист");

        // заполняем список какими-то данными
        List <Data> dataList = (List<Data>) fillData();

        // счетчик для строк
        int rowNum = 0;

        // создаем подписи к столбцам (это будет первая строчка в листе Excel файла)
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("Имя");
        row.createCell(1).setCellValue("Фамилия");
        row.createCell(2).setCellValue("Отчество");
        row.createCell(3).setCellValue("Возраст");
        row.createCell(4).setCellValue("Пол");
        row.createCell(5).setCellValue("Инн");
        row.createCell(6).setCellValue("Почтовый индекс");
        row.createCell(7).setCellValue("Страна");
        row.createCell(8).setCellValue("Область");
        row.createCell(9).setCellValue("Город");
        row.createCell(10).setCellValue("Улица");
        row.createCell(11).setCellValue("Дом");
        row.createCell(12).setCellValue("Квартира");



        // заполняем лист данными
        for (Data dataModel : dataList) {
            createSheetHeader(sheet, ++rowNum, dataModel);
        }

        // записываем созданный в памяти Excel документ в файл
        try (FileOutputStream out = new FileOutputStream(new File("C:\\FT\\HW_3\\Apache POI Excel File.xls"))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Excel файл успешно создан по пути C:\\FT\\HW_3\\Apache POI Excel File.xls\\!");
    }

    // заполнение строки (rowNum) определенного листа (sheet)
    // данными  из dataModel созданного в памяти Excel файла
    private static void createSheetHeader(HSSFSheet sheet, int rowNum, Data dataModel) {
        Row row = sheet.createRow(rowNum);

        row.createCell(0).setCellValue(dataModel.getName());
        row.createCell(1).setCellValue(dataModel.getSurname());
        row.createCell(2).setCellValue(dataModel.getPatronymic());
        row.createCell(3).setCellValue(dataModel.getCity());
    }

    // заполняем список рандомными данными
    // в реальных приложениях данные будут из БД или интернета

    private static List<Data> fillData() throws IOException {
        BufferedReader reader= null;
        reader = new BufferedReader(new FileReader("C:\\FT\\HW_3\\src\\main\\resources\\MNames.txt"));
        String line;
        List<String> lines= new ArrayList<String>();
        while ((line= reader.readLine())!=null) {
            lines.add(line);
        }
        Random rnd = new Random(System.currentTimeMillis());
        int number = 1+rnd.nextInt(5);
        List<Data> dataModels = new ArrayList<>();
        dataModels.add(new Data(lines.get(number),"Howard", "Wolowitz", "Massachusetts"));


        return dataModels;
    }
}
