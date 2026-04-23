package api.steps;

import api.model.cart.Cart;
import api.specification.ApiSpecification;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CartSteps {
    public static final String BASE_URL_CART = "https://fakestoreapi.com/carts";

    @Step("Получение списка всех корзин")
    public static List<Cart> getAllCarts() {
        // Установка спецификации
        ApiSpecification.installSpecification(ApiSpecification.requestSpec(BASE_URL_CART),
                ApiSpecification.responseSpec(200));

        return given()
                .when()
                .get()
                .then()
                .log().all()
                .extract().as(new TypeRef<>() {
                });
    }

    @Step("Создание новой корзины товаров с id: {cart.id}")
    public static Cart createCart(Cart cart) {
        // Установка спецификации
        ApiSpecification.installSpecification(ApiSpecification.requestSpec(BASE_URL_CART),
                ApiSpecification.responseSpec(201));

        return given()
                .body(cart)
                .when()
                .post()
                .then()
                .log().all()
                .extract().as(Cart.class);
    }

    @Step("Получение корзины товаров по id: {id}")
    public static Cart getCartById(int id) {
        // Установка спецификации
        ApiSpecification.installSpecification(ApiSpecification.requestSpec(BASE_URL_CART),
                ApiSpecification.responseSpec(200));

        return given()
                .when()
                .get("/" + id)
                .then()
                .log().all()
                .extract().as(Cart.class);
    }

    @Step("Обновление корзины товаров по id: {id}")
    public static Cart putCartById(int id, Cart cart) {
        // Установка спецификации
        ApiSpecification.installSpecification(ApiSpecification.requestSpec(BASE_URL_CART),
                ApiSpecification.responseSpec(200));

        return given()
                .body(cart)
                .when()
                .put("/" + id)
                .then()
                .log().all()
                .extract().as(Cart.class);
    }

    @Step("Удаление товара по id: {id}")
    public static Cart deleteCartById(int id) {
        // Установка спецификации
        ApiSpecification.installSpecification(ApiSpecification.requestSpec(BASE_URL_CART),
                ApiSpecification.responseSpec(200));

        return given()
                .when()
                .delete("/" + id)
                .then()
                .log().all()
                .extract().as(Cart.class);
    }
}