package api.test;

import api.model.product.Product;
import api.model.product.Rating;
import api.steps.ProductSteps;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Feature("Api Test Product")
public class ProductTest {

    @Test
    @DisplayName("Проверка получения списка всех товаров")
    public void testGetAllProducts() {
        // Запрос списка товаров
        List<Product> products = ProductSteps.getAllProducts();

        // Проверка непустоты списка
        assertFalse(products.isEmpty(), "Список пуст");

        // Проверка количества товаров
        assertEquals(20, products.size(), "Количество товаров не соответствует ожидаемому");

        // Проверка уникальности id
        List<Integer> ids = products.stream().map(Product::getId).toList();
        long uniqueIdsCount = ids.stream().distinct().count();
        assertEquals(products.size(), uniqueIdsCount, "Не все id в списке являются уникальными");

        // Проверка заполненности полей
        for (Product product : products) {
            assertNotNull(product.getId(), "Поле id пустое");
            assertNotNull(product.getTitle(), "Поле title пустое");
            assertNotNull(product.getPrice(), "Поле price пустое");
            assertNotNull(product.getDescription(), "Поле description пустое");
            assertNotNull(product.getCategory(), "Поле category пустое");
            assertNotNull(product.getImage(), "Поле image пустое");
            assertNotNull(product.getRating().getRate(), "Поле rating.rate пустое");
            assertNotNull(product.getRating().getCount(), "Поле rating.count пустое");
        }
    }

    @Test
    @DisplayName("Проверка добавления нового товара")
    public void testPostNewProduct() {
        // Создание тестового товара
        Product product = Product.builder()
                .title("Osprey Farpoint 40 Travel Backpack, Carry-On Size")
                .price(159.99)
                .description("Lightweight and durable travel backpack with padded laptop sleeve, lockable zippers, and stowaway harness. Perfect for weekend trips and international travel. Fits most airline carry-on requirements.")
                .category("men's clothing")
                .image("https://fakestoreapi.com/img/osprey-farpoint-40.png")
                .rating(Rating.builder().rate(4.8).count(892).build())
                .build();

        // Запрос на создание нового товара
        Product newProduct = ProductSteps.createProduct(product);

        // Проверка id нового товара
        assertNotNull(newProduct.getId(), "Новый товар должен иметь id после создания.");

        // Проверка полей нового товара с исходным
        assertEquals(product.getTitle(), newProduct.getTitle(), "Поле title не совпадает");
        assertEquals(product.getPrice(), newProduct.getPrice(), "Поле price не совпадает");
        assertEquals(product.getDescription(), newProduct.getDescription(), "Поле description не совпадает");
        assertEquals(product.getCategory(), newProduct.getCategory(), "Поле category не совпадает");
        assertEquals(product.getImage(), newProduct.getImage(), "Поле image не совпадает");
    }

    @Test
    @DisplayName("Проверка получения товара по id")
    public void testGetProductWithId() {
        // Создание ожидаемого товара
        Product expectedProduct = Product.builder()
                .id(1)
                .title("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops")
                .price(109.95)
                .description("Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday")
                .category("men's clothing")
                .image("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png")
                .rating(Rating.builder().rate(3.9).count(120).build())
                .build();

        // Запрос товара по id
        Product product = ProductSteps.getProductById(1);

        // Проверка полей нового товара с исходным
        assertEquals(expectedProduct.getId(), product.getId(), "Поле id не совпадает");
        assertEquals(expectedProduct.getTitle(), product.getTitle(), "Поле title не совпадает");
        assertEquals(expectedProduct.getPrice(), product.getPrice(), "Поле price не совпадает");
        assertEquals(expectedProduct.getDescription(), product.getDescription(), "Поле description не совпадает");
        assertEquals(expectedProduct.getCategory(), product.getCategory(), "Поле category не совпадает");
        assertEquals(expectedProduct.getImage(), product.getImage(), "Поле image не совпадает");
        assertEquals(expectedProduct.getRating().getRate(), product.getRating().getRate(), "Поле rating.rate не совпадает");
        assertEquals(expectedProduct.getRating().getCount(), product.getRating().getCount(), "Поле rating.count не совпадает");
    }

    @Test
    @DisplayName("Проверка обновления товара по id")
    public void testPutProductWithId() {
        // Создание ожидаемого товара
        Product expectedProduct = Product.builder()
                .id(1)
                .title("12345")
                .price(109.94)
                .description("67890")
                .category("1men's clothing")
                .image("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t1.png")
                .rating(Rating.builder().rate(4.3).count(125).build())
                .build();

        // Запрос товара по id
        Product product = ProductSteps.putProductById(1, expectedProduct);

        // Проверка полей нового товара с исходным
        assertEquals(expectedProduct.getId(), product.getId(), "Поле id не совпадает");
        assertEquals(expectedProduct.getTitle(), product.getTitle(), "Поле title не совпадает");
        assertEquals(expectedProduct.getPrice(), product.getPrice(), "Поле price не совпадает");
        assertEquals(expectedProduct.getDescription(), product.getDescription(), "Поле description не совпадает");
        assertEquals(expectedProduct.getCategory(), product.getCategory(), "Поле category не совпадает");
        assertEquals(expectedProduct.getImage(), product.getImage(), "Поле image не совпадает");
    }

    @Test
    @DisplayName("Проверка удаления товара по id")
    public void testDeleteProductWithId() {
        // Запрос на удаление товара
        Product product = ProductSteps.deleteProductById(1);

        // Проверка, что ответ с удаленным товаром содержит корректный id
        assertEquals(1, product.getId());
    }
}