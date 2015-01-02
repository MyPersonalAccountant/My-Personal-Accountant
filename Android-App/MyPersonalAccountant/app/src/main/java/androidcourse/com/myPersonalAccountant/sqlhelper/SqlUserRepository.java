package androidcourse.com.myPersonalAccountant.sqlhelper;

import androidcourse.com.myPersonalAccountant.entity.User;

/**
 * Created by Emrah.
 */
public interface SqlUserRepository<E extends User> extends SqlRepository<User> {
    void getFamilyRelationships(E item);

    void getIncomes(E item);

    void setFamilyRelationships(E item);

    void setIncomes(E item);
}
