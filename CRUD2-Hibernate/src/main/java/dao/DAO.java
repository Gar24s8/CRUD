package dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    List<T> getAll();

    Optional<T> getById(int id);

    boolean insert(T t);

    boolean update(T t);

    boolean delete(int id);
}
