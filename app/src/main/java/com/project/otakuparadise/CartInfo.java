package com.project.otakuparadise;

public class CartInfo {
    private static int image;
    private static String name, price, size, quantity;

    public CartInfo() {
    }

    public static int getImage() {
        return image;
    }

    public static void setImage(int image) {
        CartInfo.image = image;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        CartInfo.name = name;
    }

    public static String getPrice() {
        return price;
    }

    public static void setPrice(String price) {
        CartInfo.price = price;
    }

    public static String getSize() {
        return size;
    }

    public static void setSize(String size) {
        CartInfo.size = size;
    }

    public static String getQuantity() {
        return quantity;
    }

    public static void setQuantity(String quantity) {
        CartInfo.quantity = quantity;
    }
}
