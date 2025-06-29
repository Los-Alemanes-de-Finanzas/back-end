package org.example.finanzas.serviceimplements;

import org.example.finanzas.entities.IssuanceCosts;
import org.example.finanzas.repositories.IIssuanceCostsRepository;
import org.example.finanzas.serviceinterfaces.IIssuanceCostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssuanceCostsServiceImplements implements IIssuanceCostsService {
    @Autowired
    private IIssuanceCostsRepository iR;

    @Override
    public void insert(IssuanceCosts device) {
        iR.save(device);
    }

    @Override
    public List<IssuanceCosts> list() {
        return iR.findAll();
    }

    @Override
    public void delete(int id) {
        iR.deleteById(id);
    }

    @Override
    public IssuanceCosts listId(int id) {
        return iR.findById(id).orElse(new IssuanceCosts());
    }

    @Override
    public void update(IssuanceCosts device) {
        iR.save(device);
    }
}
