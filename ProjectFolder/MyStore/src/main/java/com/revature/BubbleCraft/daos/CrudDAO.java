package com.revature.BubbleCraft.daos;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface CrudDAO<T> {
    void save(T obj) throws IOException;
    void update(T obj);
    void delete(UUID id);
    T getById(UUID id);
    List<T> getAll();
}
