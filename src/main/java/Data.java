package ua.com.prologistic.model;


public class Data {
    private String name;
    private String surname;
    private String patronymic;
    private int age;
    private String sex;
    private String date;
    private String INN;
    private String index;
    private String country;
    private String region;
    private String city;
    private String street;
    private  int house;
    private int flat;



    public Data(String name, String surname, String patronymic, int age, String sex, String date, String INN, String index, String country, String region, String city, String street, int house, int flat) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age= age;
        this.sex = sex;
        this.date = date;
        this.INN = INN;
        this.index = index;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
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

    public int getAge() { return age;}

    public  void setAge (int age) {this.age = age;}

    public String getSex() { return sex;}

    public void setSex (String sex) {this.sex=sex;}

    public String getDate() { return date;}

    public void setDate (String date) {this.date=date;}

    public String getINN() { return INN;}

    public void setINN (String INN) {this.INN=INN;}

    public String getIndex() { return index;}

    public void setIndex (String index) {this.index=index;}

    public String getCountry() {
        return country;
    }

    public void setCountry(String city) {this.country = country; }

    public String getRegion() { return region; }

    public void setRegion(String region) { this.region = region; }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() { return street;  }

    public void setStreet(String street) {  this.street = street;  }

    public int getHouse() { return house; }

    public void setHouse(int house) { this.house = house;  }

    public int getFlat() {  return flat;  }

    public void setFlat(int flat) { this.flat = flat; }


}



