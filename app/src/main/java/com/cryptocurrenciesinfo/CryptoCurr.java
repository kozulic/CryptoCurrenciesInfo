package com.cryptocurrenciesinfo;

import java.io.Serializable;

public class CryptoCurr implements Serializable{

    private int _id;
    private String name;
    private String symbol;
    private double price;
    private long marketCap;
    private double avSupply;
    private double totSupply;
    private long volume;
    private double pc1;
    private double pc24;
    private double pcw;

    public CryptoCurr(int _id, String name, String symbol, double price, long marketCap,
                      double avSupply, double totSupply, long volume, double pc1,
                      double pc24, double pcw)
    {
        this._id = _id;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.marketCap = marketCap;
        this.avSupply = avSupply;
        this.totSupply = totSupply;
        this.volume = volume;
        this.pc1 = pc1;
        this.pc24 = pc24;
        this.pcw = pcw;
    }

    public int get_id() {
        return _id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public double getAvSupply() {
        return avSupply;
    }

    public double getTotSupply() {
        return totSupply;
    }

    public long getVolume() {
        return volume;
    }

    public double getPc1() {
        return pc1;
    }

    public double getPc24() {
        return pc24;
    }

    public double getPcw() {
        return pcw;
    }
}
