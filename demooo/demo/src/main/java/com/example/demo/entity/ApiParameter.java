package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "script_parameters")
public class ApiParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nasdaq_traded")
    private String nasdaqTraded;

    @Column(name = "market_category")
    private String marketCategory;

    @Column(name = "round_lot_size")
    private String roundLotSize;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNasdaqTraded() {
        return nasdaqTraded;
    }

    public void setNasdaqTraded(String nasdaqTraded) {
        this.nasdaqTraded = nasdaqTraded;
    }

    public String getMarketCategory() {
        return marketCategory;
    }

    public void setMarketCategory(String marketCategory) {
        this.marketCategory = marketCategory;
    }

    public String getRoundLotSize() {
        return roundLotSize;
    }

    public void setRoundLotSize(String roundLotSize) {
        this.roundLotSize = roundLotSize;
    }
}
