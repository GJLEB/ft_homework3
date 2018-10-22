package ua.com.prologistic.excel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONObject;
import org.json.JSONString;
import ua.com.prologistic.model.Data;
import ua.com.prologistic.model.Pls;
import ua.com.prologistic.model.Country;


import java.io.*;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.Date;

public class FileCreation {
    public static void main(String[] args) throws ParseException, IOException, UnirestException, InterruptedException, SQLException {


        // создание самого excel файла
        HSSFWorkbook workbook = new HSSFWorkbook();
        // создание листа с названием "Просто лист"
        HSSFSheet sheet = workbook.createSheet("Просто лист");

        // заполняем список какими-то данными


        // счетчик для строк
        int rowNum = 0;

        // создаем подписи к столбцам (это будет первая строчка в листе Excel файла)
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("Имя");
        row.createCell(1).setCellValue("Фамилия");
        row.createCell(2).setCellValue("Отчество");
        row.createCell(3).setCellValue("Возраст");
        row.createCell(4).setCellValue("Пол");
        row.createCell(5).setCellValue("Дата рождения");
        row.createCell(6).setCellValue("Инн");
        row.createCell(7).setCellValue("Почтовый индекс");
        row.createCell(8).setCellValue("Страна");
        row.createCell(9).setCellValue("Область");
        row.createCell(10).setCellValue("Город");
        row.createCell(11).setCellValue("Улица");
        row.createCell(12).setCellValue("Дом");
        row.createCell(13).setCellValue("Квартира");

        Process check = java.lang.Runtime.getRuntime().exec("ping www.randus.org");
        if (check.waitFor() == 0) {
            List<Data> dataList = (List<Data>) fillData();
            // заполняем лист данными
            for (Data dataModel : dataList) {
                createSheetHeader(sheet, ++rowNum, dataModel);
            }


            // записываем созданный в памяти Excel документ в файл

            try (FileOutputStream out = new FileOutputStream(new File("Apache POI Excel File.xls"))) {
                workbook.write(out);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Excel файл успешно создан с помощью API по пути *Директория программы*\\Apache POI Excel File.xls!");
        } else{
            List<Data> dataList = (List<Data>) fillDatafromDB();
            if (!dataList.isEmpty()) {
                for (Data dataModel : dataList) {
                    createSheetHeader(sheet, ++rowNum, dataModel);
                }


                // записываем созданный в памяти Excel документ в файл

                try (FileOutputStream out = new FileOutputStream(new File("Apache POI Excel File.xls"))) {
                    workbook.write(out);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Excel файл успешно создан из базы данных по пути *Директория программы*\\Apache POI Excel File.xls!");
            }
            else { System.out.println("Фаил не заплонен! Отсутсвует подключение к интернету и данные в базе данных.");}
            }

        }

    // заполнение строки (rowNum) определенного листа (sheet)
    // данными  из dataModel созданного в памяти Excel файла
    private static void createSheetHeader(HSSFSheet sheet, int rowNum, Data dataModel) {
        Row row = sheet.createRow(rowNum);

        row.createCell(0).setCellValue(dataModel.getName());
        row.createCell(1).setCellValue(dataModel.getSurname());
        row.createCell(2).setCellValue(dataModel.getPatronymic());
        row.createCell(3).setCellValue(dataModel.getAge());
        row.createCell(4).setCellValue(dataModel.getSex());
        row.createCell(5).setCellValue(dataModel.getDate());
        row.createCell(6).setCellValue(dataModel.getINN());
        row.createCell(7).setCellValue(dataModel.getIndex());
        row.createCell(8).setCellValue(dataModel.getCountry());
        row.createCell(9).setCellValue(dataModel.getRegion());
        row.createCell(10).setCellValue(dataModel.getCity());
        row.createCell(11).setCellValue(dataModel.getStreet());
        row.createCell(12).setCellValue(dataModel.getHouse());
        row.createCell(13).setCellValue(dataModel.getFlat());

    }

    // заполняем список рандомными данными
    // в реальных приложениях данные будут из БД или интернета
    private static List<String> getData(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "Cp1251"));
        String line;
        List<String> List = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            List.add(line);
        }
        return List;

    }

    private static Date RandomData() {
        Random rnd;
        Date dt;
        long ms;
// Get a new random instance, seeded from the clock
        rnd = new Random();
// Get an Epoch value roughly between 1940 and 2010
// -946771200000L = January 1, 1940
// Add up to 70 years to it (using modulus on the next long)
        ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
        dt = new Date(ms);
        return (dt);
    }

    private static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    private static String putData(Date a) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(a);

        int Day = calendar.get(Calendar.DAY_OF_MONTH);
        int Month = calendar.get(Calendar.MONTH) + 1;
        int Year = calendar.get(Calendar.YEAR);

        return (String.format("%s %s %s", Integer.toString(Day), Integer.toString(Month), Integer.toString(Year)));

    }

    private static int calculateAge1(Date a) {

        LocalDate b = LocalDate.ofInstant(a.toInstant(), ZoneId.systemDefault());
        LocalDate c = LocalDate.now();

        return (calculateAge(b, c));

    }

    private static String createINN() {
        String first = "77";
        String[] second = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "33", "34", "35", "36", "43", "45", "46", "47", "48", "49", "50", "51"};
        int rnd1 = new Random().nextInt(second.length);
        StringBuilder third = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int rnd2 = new Random().nextInt(10);
            third.append(rnd2);
        }
        String prep = first + second[rnd1] + third.toString();
        int elevennum = (Character.getNumericValue(prep.charAt(0)) * 7 + Character.getNumericValue(prep.charAt(1)) * 2 + Character.getNumericValue(prep.charAt(2)) * 4 + Character.getNumericValue(prep.charAt(3)) * 10 + Character.getNumericValue(prep.charAt(4)) * 3 + Character.getNumericValue(prep.charAt(5)) * 5 + Character.getNumericValue(prep.charAt(6)) * 9 + Character.getNumericValue(prep.charAt(7)) * 4 + Character.getNumericValue(prep.charAt(8)) * 6 + Character.getNumericValue(prep.charAt(9)) * 8) % 11;
        if (elevennum == 10) {
            elevennum = 0;
        }
        int twelve = (Character.getNumericValue(prep.charAt(0)) * 3 + Character.getNumericValue(prep.charAt(1)) * 7 + Character.getNumericValue(prep.charAt(2)) * 2 + Character.getNumericValue(prep.charAt(3)) * 4 + Character.getNumericValue(prep.charAt(4)) * 10 + Character.getNumericValue(prep.charAt(5)) * 3 + Character.getNumericValue(prep.charAt(6)) * 5 + Character.getNumericValue(prep.charAt(7)) * 9 + Character.getNumericValue(prep.charAt(8)) * 4 + Character.getNumericValue(prep.charAt(9)) * 6 + elevennum * 8) % 11;
        if (twelve == 10) {
            twelve = 0;
        }

        return (prep + elevennum + twelve);

    }

    private static String createIndex() {
        StringBuilder index = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int rnd = new Random().nextInt(10);
            index.append(rnd);
        }
        return (index.toString());
    }


    private static Pls parser() throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.get("https://randus.org/api.php").asJson();
        JsonNode body = jsonResponse.getBody();
        int status = jsonResponse.getStatus();


        Gson g = new Gson();
        Pls value = g.fromJson(body.toString(), Pls.class);

        return (value);
    }


    private static List<Data> fillData() throws IOException, UnirestException, SQLException {


        List<String> MNames = getData("src\\main\\resources\\MNames.txt");
        List<String> FNames = getData("src\\main\\resources\\FNames.txt");
        List<String> MSurnames = getData("src\\main\\resources\\MSurnames.txt");
        List<String> MPatronymic = getData("src\\main\\resources\\MPatronymic.txt");
        List<String> Countries = getData("src\\main\\resources\\Countries.txt");
        List<String> Regions = getData("src\\main\\resources\\Regions.txt");
        List<String> Cities = getData("src\\main\\resources\\Cities.txt");
        List<String> Streets = getData("src\\main\\resources\\Streets.txt");


        Random rnd = new Random(System.currentTimeMillis());
        int amount = 1 + rnd.nextInt(30);

        List<Data> dataModels = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            Date a = RandomData();
            Pls person = parser();
            var contr=rnd.nextInt(30);
            var reg=rnd.nextInt(30);
            var house=1 + rnd.nextInt(300);
            var flat=1 + rnd.nextInt(2000);
            if (person.gender.equals("w")) {

                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/home?autoReconnect=true&useSSL=false", "root", "1234");
                     Statement statement = connection.createStatement()) {
                    statement.executeUpdate("insert into persons (sname,secondname,patronymic,Age,Sex,BirtDay,INN,Ind,Country,region,city,street,house,flat) values ('" + person.fname + "', '" + person.lname + "',  '" + person.patronymic + "', '" + calculateAge1(a) + "',  'Ж',  '" + putData(a) + "', '" + createINN() + "',  '" + createIndex() + "',  '" + Countries.get(contr) + "',  '" + Regions.get(reg) + "',  '" + person.city + "',  '" + person.street + "',  '" + house + "', '" + flat+"')");

                    dataModels.add(new Data(person.fname, person.lname, person.patronymic, calculateAge1(a), "Ж", putData(a), createINN(), createIndex(), Countries.get(contr), Regions.get(reg), person.city, person.street, house, flat));
                }
            }
            else
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/home?autoReconnect=true&useSSL=false", "root", "1234");
                     Statement statement = connection.createStatement()) {

                    statement.executeUpdate("insert into persons (sname,secondname,patronymic,Age,Sex,BirtDay,INN,Ind,Country,region,city,street,house,flat) values ('" + person.fname + "', '" + person.lname + "',  '" + person.patronymic + "', '" + calculateAge1(a) + "',  'М',  '" + putData(a) + "', '" + createINN() + "',  '" + createIndex() + "',  '" + Countries.get(contr) + "',  '" + Regions.get(reg) + "',  '" + person.city + "',  '" + person.street + "',  '" + house + "', '" + flat+"')");
                    dataModels.add(new Data(person.fname, person.lname, person.patronymic, calculateAge1(a), "М", putData(a), createINN(), createIndex(), Countries.get(contr), Regions.get(reg), person.city, person.street, house, flat));

                }

            }

        return dataModels;

    }

    private static List<Data> fillDatafromDB() throws SQLException {
        Random rnd = new Random(System.currentTimeMillis());
        int amount = 1 + rnd.nextInt(30);

        List<Data> dataModels1 = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/home?autoReconnect=true&useSSL=false", "root", "1234");
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from persons limit "+amount);
            while (resultSet.next()){
                dataModels1.add(new Data(resultSet.getString("sname"),resultSet.getString("secondname"), resultSet.getString("patronymic"), resultSet.getInt("Age"), resultSet.getString("Sex"), resultSet.getString("BirtDay"), resultSet.getString("INN"), resultSet.getString("Ind"), resultSet.getString("Country"), resultSet.getString("region"),resultSet.getString("city"), resultSet.getString("street"), resultSet.getInt("house"), resultSet.getInt("flat")));
            }
        }
        return (dataModels1);
    }
}
