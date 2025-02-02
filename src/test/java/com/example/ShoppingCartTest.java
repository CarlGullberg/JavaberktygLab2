package com.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    private ShoppingCart cart;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
    }

    @Test
    void addingItemIncreasesItemCount() {
        cart.addItem("apple", 2.0, 1);
        assertEquals(1, cart.getItemCount());
    }

    @Test
    void removingItemDecreasesItemCount() {
        cart.addItem("apple", 2.0, 1);
        cart.removeItem("apple");
        assertEquals(0, cart.getItemCount());
    }
    @Test
    void totalPriceCalculatesCorrectly() {
        cart.addItem("apple", 2.0, 2); // 2 apples at 2.0 each = 4.0
        cart.addItem("banana", 1.5, 3); // 3 bananas at 1.5 each = 4.5
        assertEquals(8.5, cart.calculateTotalPrice());
    }
    @Test
    void applyDiscountCorrectlyReducesPrice() {
        cart.addItem("apple", 2.0, 2); // 4.0
        cart.applyDiscount(0.1); // 10% discount
        assertEquals(3.6, cart.calculateTotalPrice());
    }



}

