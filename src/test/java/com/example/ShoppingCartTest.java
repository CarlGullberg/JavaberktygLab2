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


}

