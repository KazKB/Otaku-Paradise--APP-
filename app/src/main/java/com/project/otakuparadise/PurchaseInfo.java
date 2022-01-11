package com.project.otakuparadise;

public class PurchaseInfo {
    private static int image;
    private static String name, price;

    public PurchaseInfo() {
    }

    public static int getImage() {
        return image;
    }

    public static void setImage(int image) {
        PurchaseInfo.image = image;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        PurchaseInfo.name = name;
    }

    public static String getPrice() {
        return price;
    }

    public static void setPrice(String price) {
        PurchaseInfo.price = price;
    }
}
