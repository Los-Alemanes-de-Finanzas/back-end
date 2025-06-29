package org.example.finanzas.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.example.finanzas.dtos.IssuanceCostsDTO;
import org.example.finanzas.entities.IssuanceCosts;
import org.example.finanzas.serviceinterfaces.IIssuanceCostsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/issuancecosts")
@PreAuthorize("hasAuthority('USER')")
public class IssuanceCostsController {

    @Autowired
    private IIssuanceCostsService iS;

    @PostMapping
    public void registrar(@RequestBody IssuanceCostsDTO dto) {
        ModelMapper m = new ModelMapper();
        IssuanceCosts ic = m.map(dto, IssuanceCosts.class);
        iS.insert(ic);
    }

    @PutMapping
    public void modificar(@RequestBody IssuanceCostsDTO dto) {
        ModelMapper m = new ModelMapper();
        IssuanceCosts ic = m.map(dto, IssuanceCosts.class);
        iS.update(ic);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        iS.delete(id);
    }

    @GetMapping("/{id}")
    public IssuanceCostsDTO listarId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        IssuanceCostsDTO dto = m.map(iS.listId(id), IssuanceCostsDTO.class);
        return dto;
    }

    @GetMapping
    public List<IssuanceCostsDTO> listar() {
        return iS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, IssuanceCostsDTO.class);
        }).collect(Collectors.toList());
    }
}