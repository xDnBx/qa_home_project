package api.tests;

import api.model.cart.Cart;
import api.model.cart.ProductShort;
import api.steps.CartSteps;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Feature("Api Test Cart")
public class CartTest {

    @Test
    @DisplayName("Проверка получения списка всех корзин товаров")
    public void testGetAllCarts() {
        // Запрос списка корзин товаров
        List<Cart> carts = CartSteps.getAllCarts();

        // Проверка непустоты списка
        assertFalse(carts.isEmpty(), "Список пуст");

        // Проверка количества корзин товаров
        assertEquals(7, carts.size(), "Количество корзин товаров не соответствует ожидаемому");

        // Проверка уникальности id
        List<Integer> ids = carts.stream().map(Cart::getId).toList();
        long uniqueIdsCount = ids.stream().distinct().count();
        assertEquals(carts.size(), uniqueIdsCount, "Не все id в списке являются уникальными");

        // Проверка заполненности полей
        for (Cart cart : carts) {
            assertNotNull(cart.getId(), "Поле id пустое");
            assertNotNull(cart.getUserId(), "Поле userId пустое");
            assertNotNull(cart.getDate(), "Поле date пустое");
            assertNotNull(cart.getProducts().getFirst().getProductId(), "Поле products.productId пустое");
            assertNotNull(cart.getProducts().getFirst().getQuantity(), "Поле products.quantity пустое");
            assertNotNull(cart.get__v(), "Поле __v пустое");
        }
    }

    @Test
    @DisplayName("Проверка добавления новой корзины товаров")
    public void testPostNewCart() {
        // Создание тестовой корзины товаров
        Cart cart = Cart.builder()
                .date(LocalDateTime.of(2020, 4, 3, 0, 4))
                .products(List.of(ProductShort.builder().productId(3).quantity(9).build(),
                        ProductShort.builder().productId(4).quantity(2).build()))
                .build();

        // Запрос на создание новой корзины товаров
        Cart newCart = CartSteps.createCart(cart);

        // Проверка id новой корзины
        assertNotNull(newCart.getId(), "Новая корзина товаров должна иметь id после создания.");

        // Проверка полей нового товара с исходным
        assertEquals(cart.getDate(), newCart.getDate(), "Поле date не совпадает");
        assertEquals(cart.getProducts().getFirst().getProductId(), newCart.getProducts().getFirst().getProductId(),
                "Поле products.productId не совпадает");
        assertEquals(cart.getProducts().getFirst().getQuantity(), newCart.getProducts().getFirst().getQuantity(),
                "Поле products.quantity не совпадает");
    }

    @Test
    @DisplayName("Проверка получения корзины товаров по id")
    public void testGetCartWithId() {
        // Создание ожидаемой корзины товаров
        Cart expectedCart = Cart.builder()
                .id(1)
                .userId(1)
                .date(LocalDateTime.of(2020, 3, 2, 0, 0))
                .products(List.of(ProductShort.builder().productId(1).quantity(4).build(),
                        ProductShort.builder().productId(2).quantity(1).build(),
                        ProductShort.builder().productId(3).quantity(6).build()))
                .__v(0)
                .build();

        // Запрос корзины товаров по id
        Cart cart = CartSteps.getCartById(1);

        // Проверка полей новой корзины товаров с исходной
        assertEquals(expectedCart.getId(), cart.getId(), "Поле id не совпадает");
        assertEquals(expectedCart.getUserId(), cart.getUserId(), "Поле userId не совпадает");
        assertEquals(expectedCart.getDate(), cart.getDate(), "Поле date не совпадает");
        assertEquals(expectedCart.getProducts().getFirst().getProductId(), cart.getProducts().getFirst().getProductId(),
                "Поле products.productId не совпадает");
        assertEquals(expectedCart.getProducts().getFirst().getQuantity(), cart.getProducts().getFirst().getQuantity(),
                "Поле products.quantity не совпадает");
    }

    @Test
    @DisplayName("Проверка обновления корзины товаров по id")
    public void testPutCartWithId() {
        // Создание ожидаемой корзины товаров
        Cart expectedCart = Cart.builder()
                .id(1)
                .userId(2)
                .date(LocalDateTime.of(2021, 4, 8, 1, 9, 1))
                .products(List.of(ProductShort.builder().productId(1).quantity(4).build(),
                        ProductShort.builder().productId(2).quantity(1).build(),
                        ProductShort.builder().productId(3).quantity(6).build()))
                .__v(0)
                .build();

        // Запрос корзины товаров по id
        Cart cart = CartSteps.putCartById(1, expectedCart);

        // Проверка полей новой корзины товаров с исходной
        assertEquals(expectedCart.getId(), cart.getId(), "Поле id не совпадает");
        assertEquals(expectedCart.getUserId(), cart.getUserId(), "Поле userId не совпадает");
        assertEquals(expectedCart.getDate(), cart.getDate(), "Поле date не совпадает");
        assertEquals(expectedCart.getProducts().getFirst().getProductId(), cart.getProducts().getFirst().getProductId(),
                "Поле products.productId не совпадает");
        assertEquals(expectedCart.getProducts().getFirst().getQuantity(), cart.getProducts().getFirst().getQuantity(),
                "Поле products.quantity не совпадает");
    }

    @Test
    @DisplayName("Проверка удаления корзины товаров по id")
    public void testDeleteCartWithId() {
        // Создание ожидаемого удаления id
        int expectedId = 1;

        // Запрос на удаление корзины товаров
        Cart cart = CartSteps.deleteCartById(expectedId);

        // Проверка, что ответ с удаленным товаром содержит корректный id
        assertEquals(expectedId, cart.getId());
    }
}