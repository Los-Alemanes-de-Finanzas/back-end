package org.example.finanzas.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "issuance_costs")
public class IssuanceCosts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "bond_id", nullable = false) // Requerido: Un costo debe pertenecer a un bono.
    private Bond bond;

    @Column(name = "prima", nullable = false) // Requerido: Se necesita para el cálculo.
    private Float premium;

    @Column(name = "structuring", nullable = false) // Requerido: Se necesita para el cálculo.
    private Float structuringCost;

    @Column(name = "who_pays_structuring", nullable = false, length = 200) // Requerido: Regla de negocio.
    private String whoPaysStructuring;

    @Column(name = "placement", nullable = false) // Requerido: Se necesita para el cálculo.
    private Float placementCost;

    @Column(name = "who_pays_placement", nullable = false, length = 200) // Requerido: Regla de negocio.
    private String whoPaysPlacement;

    @Column(name = "floatation", nullable = false) // Requerido: Se necesita para el cálculo.
    private Float flotationCost;

    @Column(name = "who_pays_floatation", nullable = false, length = 200) // Requerido: Regla de negocio.
    private String whoPaysFloatation;

    @Column(name = "cavali", nullable = false) // Requerido: Se necesita para el cálculo.
    private Float cavaliFee;

    @Column(name = "who_pays_cavali", nullable = false, length = 200) // Requerido: Regla de negocio.
    private String whoPaysCavali;

    // Opcional: Este campo es un resultado. Se calcula y se guarda DESPUÉS de la creación.
    @Column(name = "initial_cost_issuer")
    private Float initialCostIssuer;

    // Opcional: Este campo es un resultado. Se calcula y se guarda DESPUÉS de la creación.
    @Column(name = "initial_cost_bonista")
    private Float initialCostBondholder;

    public IssuanceCosts() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bond getBond() {
        return bond;
    }

    public void setBond(Bond bond) {
        this.bond = bond;
    }

    public Float getPremium() {
        return premium;
    }

    public void setPremium(Float premium) {
        this.premium = premium;
    }

    public Float getStructuringCost() {
        return structuringCost;
    }

    public void setStructuringCost(Float structuringCost) {
        this.structuringCost = structuringCost;
    }

    public String getWhoPaysStructuring() {
        return whoPaysStructuring;
    }

    public void setWhoPaysStructuring(String whoPaysStructuring) {
        this.whoPaysStructuring = whoPaysStructuring;
    }

    public Float getPlacementCost() {
        return placementCost;
    }

    public void setPlacementCost(Float placementCost) {
        this.placementCost = placementCost;
    }

    public String getWhoPaysPlacement() {
        return whoPaysPlacement;
    }

    public void setWhoPaysPlacement(String whoPaysPlacement) {
        this.whoPaysPlacement = whoPaysPlacement;
    }

    public Float getFlotationCost() {
        return flotationCost;
    }

    public void setFlotationCost(Float flotationCost) {
        this.flotationCost = flotationCost;
    }

    public String getWhoPaysFloatation() {
        return whoPaysFloatation;
    }

    public void setWhoPaysFloatation(String whoPaysFloatation) {
        this.whoPaysFloatation = whoPaysFloatation;
    }

    public Float getCavaliFee() {
        return cavaliFee;
    }

    public void setCavaliFee(Float cavaliFee) {
        this.cavaliFee = cavaliFee;
    }

    public String getWhoPaysCavali() {
        return whoPaysCavali;
    }

    public void setWhoPaysCavali(String whoPaysCavali) {
        this.whoPaysCavali = whoPaysCavali;
    }

    public Float getInitialCostIssuer() {
        return initialCostIssuer;
    }

    public void setInitialCostIssuer(Float initialCostIssuer) {
        this.initialCostIssuer = initialCostIssuer;
    }

    public Float getInitialCostBondholder() {
        return initialCostBondholder;
    }

    public void setInitialCostBondholder(Float initialCostBondholder) {
        this.initialCostBondholder = initialCostBondholder;
    }
}