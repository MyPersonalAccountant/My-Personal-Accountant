package androidcourse.com.myPersonalAccountant.entity;

import java.util.Date;
import java.util.List;

import androidcourse.com.myPersonalAccountant.sqlhelper.Entity;

/**
 * Created by Emrah on 30.12.2014 г..
 */
public class User implements Entity {
    private Integer id;
    private String name;
    private int age;
    private String password;
    private String email;
    private Date birthDate;
    private String country;
    private String address;
    private String phone;
    private Boolean isDeleted;
    private List<FamilyRelationship> family;
    private List<Income> incomes;

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    public List<FamilyRelationship> getFamily() {
        return family;
    }

    public void setFamily(List<FamilyRelationship> family) {
        this.family = family;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
