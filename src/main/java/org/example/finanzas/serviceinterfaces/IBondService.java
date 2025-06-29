package org.example.finanzas.serviceinterfaces;

import org.example.finanzas.entities.Bond;

import java.util.List;

public interface IBondService {
    public void insert(Bond device);
    public List<Bond> list();
    public void delete(int id);
    public Bond listId(int id);
    public void update(Bond device);
}
