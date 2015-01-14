package androidcourse.com.myPersonalAccountant.entity;

import java.io.Serializable;
import java.util.Date;

import androidcourse.com.myPersonalAccountant.sqlhelper.Entity;

/**
 * Created by Emrah.
 */
public class UserOrder implements Entity, Serializable {
    private Integer id;
    private String name;
    private String description;
    private Date createdDate;
    private Boolean onServer;
    private Boolean isPaid;
    private Double value;
    private Schedule schedule;
    private Expense expense;
    private Category category;
    private User user;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getOnServer() {
        return onServer;
    }

    public void setOnServer(Boolean onServer) {
        this.onServer = onServer;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
