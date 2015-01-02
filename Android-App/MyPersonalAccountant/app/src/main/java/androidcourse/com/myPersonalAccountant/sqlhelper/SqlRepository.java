package androidcourse.com.myPersonalAccountant.sqlhelper;

import java.util.List;

/**
 * Created by Emrah on 30.12.2014 г..
 */
public interface SqlRepository<E> {
    List<E> getAll();

    E getByID(int id);

    Long insert(E item);

    Integer update(E item);

    int delete(int id);

    int delete(E item);
}
