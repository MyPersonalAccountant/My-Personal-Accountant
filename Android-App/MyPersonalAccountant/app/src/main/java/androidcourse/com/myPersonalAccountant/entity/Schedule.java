package androidcourse.com.myPersonalAccountant.entity;

import java.util.Date;

import androidcourse.com.myPersonalAccountant.sqlhelper.Entity;

/**
 * Created by Emrah.
 */
public class Schedule implements Entity {
    private Integer id;
    private String name;
    private Date firstPaymentDate;
    private Boolean isRepeatable;
    private int notificationBeforePaymentInMinutes;
    private Date firstDate;
    private Date endDate;
    private UserOrder userOrder;

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
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

    public Date getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public void setFirstPaymentDate(Date firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
    }

    public Boolean getIsRepeatable() {
        return isRepeatable;
    }

    public void setIsRepeatable(Boolean isRepeatable) {
        this.isRepeatable = isRepeatable;
    }

    public int getNotificationBeforePaymentInMinutes() {
        return notificationBeforePaymentInMinutes;
    }

    public void setNotificationBeforePaymentInMinutes(int notificationBeforePaymentInMinutes) {
        this.notificationBeforePaymentInMinutes = notificationBeforePaymentInMinutes;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
