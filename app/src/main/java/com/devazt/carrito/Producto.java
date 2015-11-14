package com.devazt.carrito;

/**
 * Created by Nekszer on 14/11/2015.
 */
public class Producto {

    private String _id;
    private String name;
    private String barCode;
    private double price;
    private int discount;
    private int __v;
    private int stock;

    public String getName() {
        return name;
    }

    public String getBarCode() {
        return barCode;
    }

    public String get_id() {
        return _id;
    }

    public int getStock() {
        return stock;
    }

    public int getDiscount() {
        return discount;
    }

    public double getPrice() {
        return price;
    }

    public int get__v() {
        return __v;
    }
}

