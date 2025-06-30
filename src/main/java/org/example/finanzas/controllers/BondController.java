package org.example.finanzas.controllers;

import org.example.finanzas.dtos.FinalCostsResultDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.example.finanzas.dtos.BondDTO;
import org.example.finanzas.entities.Bond;
import org.example.finanzas.serviceinterfaces.IBondService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bonds")
@PreAuthorize("hasAuthority('USER')")
public class BondController {

    @Autowired
    private IBondService bS;

    @PutMapping("/calculate-initial/{id}")
    public ResponseEntity<BondDTO> calcularValoresIniciales(@PathVariable("id") Integer id) {
        Bond updatedBond = bS.calculateInitialValues(id);
        ModelMapper m = new ModelMapper();
        BondDTO dto = m.map(updatedBond, BondDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/calculate-final-costs/{id}")
    public ResponseEntity<FinalCostsResultDTO> calcularCostosFinales(@PathVariable("id") Integer id) {
        // El servicio ya devuelve el DTO que necesitamos. ¡No se necesita ModelMapper!
        FinalCostsResultDTO result = bS.calculateFinalCosts(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public void registrar(@RequestBody BondDTO dto) {
        ModelMapper m = new ModelMapper();
        Bond b = m.map(dto, Bond.class);
        bS.insert(b);
    }

    @PutMapping
    public void modificar(@RequestBody BondDTO dto) {
        ModelMapper m = new ModelMapper();
        Bond b = m.map(dto, Bond.class);
        bS.update(b);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        bS.delete(id);
    }

    @GetMapping("/{id}")
    public BondDTO listarId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        BondDTO dto = m.map(bS.listId(id), BondDTO.class);
        return dto;
    }

    @GetMapping
    public List<BondDTO> listar() {
        return bS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, BondDTO.class);
        }).collect(Collectors.toList());
    }
}