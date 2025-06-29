package org.example.finanzas.serviceimplements;

import org.example.finanzas.entities.Bond;
import org.example.finanzas.repositories.IBondRepository;
import org.example.finanzas.serviceinterfaces.IBondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BondServiceImplements implements IBondService {
    @Autowired
    private IBondRepository bR;
    @Override
    public void insert(Bond device) {
       bR.save(device);
    }

    @Override
    public List<Bond> list() {
        return bR.findAll();
    }

    @Override
    public void delete(int id) {
        bR.deleteById(id);
    }

    @Override
    public Bond listId(int id) {
        return bR.findById(id).orElse(new Bond());
    }

    @Override
    public void update(Bond device) {
        bR.save(device);
    }
}
