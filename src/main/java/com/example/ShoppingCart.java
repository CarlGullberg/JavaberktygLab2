package com.example;



import java.util.HashMap;
import java.util.Map;

class ShoppingCart {

    private Map<String, Item> items;
    private double discount;

    public ShoppingCart() {
        this.items = new HashMap<>();
        this.discount = 0.0;
    }

    public void addItem(String name, double price, int quantity) {
        items.put(name, new Item(name, price, quantity));
    }

    public void removeItem(String name) {
        items.remove(name);
    }

    public void updateItemQuantity(String name, int newQuantity) {
        if (items.containsKey(name)) {
            Item item = items.get(name);
            item.setQuantity(newQuantity);
        }
    }

    public int getItemCount() {
        return items.size();
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Item item : items.values()) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        return totalPrice * (1 - discount);
    }

    public void applyDiscount(double discountPercentage) {
        this.discount = discountPercentage;
    }

    // Inner class representing an item in the cart
    private static class Item {
        private String name;
        private double price;
        private int quantity;

        public Item(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}


