package ua.com.prologistic.model;

public class Data {
    private String name;
    private String surname;
    private String patronymic;
    private String city;


    public Data() {
    }

    public Data(String name, String surname, String patronymic, String city) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public  String getPatronymic() { return patronymic; }

    public void setPatronymic(String patronymic){ this.patronymic = patronymic;}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}

