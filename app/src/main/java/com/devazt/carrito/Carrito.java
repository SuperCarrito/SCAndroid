package com.devazt.carrito;

import java.util.ArrayList;

/**
 * Created by Nekszer on 14/11/2015.
 */
public class Carrito {

    private String _id;
    private String cartId;
    private String __v;
    private String lastUsed;
    private boolean hasPaid;
    private double totalPrice;
    ArrayList<Products> products;
}

class Products
{
    private String barCode;
    private String _id;
    private double price;
    private String numberBought;

    public String getBarCode() {
        return barCode;
    }

    public double getPrice() {
        return price;
    }
}
