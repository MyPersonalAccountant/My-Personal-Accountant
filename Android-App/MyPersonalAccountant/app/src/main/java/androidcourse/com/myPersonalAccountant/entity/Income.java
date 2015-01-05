package androidcourse.com.myPersonalAccountant.entity;

import androidcourse.com.myPersonalAccountant.sqlhelper.Entity;

/**
 * Created by Emrah.
 */
public class Income implements Entity {
    private Integer id;
    private String name;
    private Double value;
    private String description;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
