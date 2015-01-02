package androidcourse.com.myPersonalAccountant.entity;

/**
 * Created by Emrah.
 */
public class FamilyRelationship {
    private String relationShip;
    private User user;

    public FamilyRelationship() {
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
