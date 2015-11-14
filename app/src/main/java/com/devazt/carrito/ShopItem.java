package com.devazt.carrito;

/**
 * Created by Nekszer on 14/11/2015.
 */
public class ShopItem {

    private String code_bar;
    private String price;

    public ShopItem(String code_bar, String price) {
        this.code_bar = code_bar;
        this.price = price;
    }

    public String getCode_bar() {
        return code_bar;
    }

    public String getPrice() {
        return price;
    }
}
