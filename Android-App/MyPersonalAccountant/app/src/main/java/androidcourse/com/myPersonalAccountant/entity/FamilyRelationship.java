package androidcourse.com.myPersonalAccountant.entity;

import androidcourse.com.myPersonalAccountant.sqlhelper.Entity;

/**
 * Created by Emrah.
 */
public class FamilyRelationship implements Entity {
    private Integer id;
    private String relationShip;
    private User user;

    public FamilyRelationship() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
