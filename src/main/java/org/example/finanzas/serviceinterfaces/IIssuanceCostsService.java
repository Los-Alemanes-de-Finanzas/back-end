package org.example.finanzas.serviceinterfaces;

import org.example.finanzas.entities.IssuanceCosts;

import java.util.List;

public interface IIssuanceCostsService {
    public void insert(IssuanceCosts device);
    public List<IssuanceCosts> list();
    public void delete(int id);
    public IssuanceCosts listId(int id);
    public void update(IssuanceCosts device);
}
