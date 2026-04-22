package api.steps;

import api.model.Product;
import api.specification.ApiSpecification;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductSteps {
    @Step("Получение списка всех продуктов")
    public static List<Product> getProducts() {
        // Установка спецификации
        ApiSpecification.installSpecification(ApiSpecification.requestSpec(ApiSpecification.BASE_URL),
                ApiSpecification.responseSpec(200));

        return given()
                .when()
                .get()
                .then()
                .log().all()
                .extract().as(new TypeRef<>() {
                });

    }

    @Step("Создание нового продукта: {product.title}")
    public static Product createProduct(Product product) {
        // Установка спецификации
        ApiSpecification.installSpecification(ApiSpecification.requestSpec(ApiSpecification.BASE_URL),
                ApiSpecification.responseSpec(201));

        return given()
                .body(product)
                .when()
                .post()
                .then()
                .log().all()
                .extract().as(Product.class);
    }

    @Step("Получение продукта по ID: {id}")
    public static Product getProductById(int id) {
        // Установка спецификации
        ApiSpecification.installSpecification(ApiSpecification.requestSpec(ApiSpecification.BASE_URL),
                ApiSpecification.responseSpec(200));

        return given()
                .when()
                .get("/" + id)
                .then()
                .log().all()
                .extract().as(Product.class);
    }

    @Step("Удаление продукта по ID: {id}")
    public static Product deleteProductById(int id) {
        // Установка спецификации
        ApiSpecification.installSpecification(ApiSpecification.requestSpec(ApiSpecification.BASE_URL),
                ApiSpecification.responseSpec(200));

        // Запрос удаления товара по id
        return given()
                .when()
                .delete("/" + id)
                .then()
                .log().all()
                .extract().as(Product.class);
    }
}