package api.steps;

import api.model.user.User;
import api.specification.ApiSpecification;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserSteps {
    public static final String BASE_URL_USER ="https://fakestoreapi.com/users";

    @Step("Получение списка всех пользователей")
    public static List<User> getAllUsers() {
        // Установка спецификации
        ApiSpecification.installSpecification(ApiSpecification.requestSpec(BASE_URL_USER),
                ApiSpecification.responseSpec(200));

        return given()
                .when()
                .get()
                .then()
                .log().all()
                .extract().as(new TypeRef<>() {
                });
    }

    @Step("Создание нового пользователя с id: {user.id}")
    public static User createUser(User user) {
        // Установка спецификации
        ApiSpecification.installSpecification(ApiSpecification.requestSpec(BASE_URL_USER),
                ApiSpecification.responseSpec(201));

        return given()
                .body(user)
                .when()
                .post()
                .then()
                .log().all()
                .extract().as(User.class);
    }

    @Step("Получение пользователя по id: {id}")
    public static User getUserById(int id) {
        // Установка спецификации
        ApiSpecification.installSpecification(ApiSpecification.requestSpec(BASE_URL_USER),
                ApiSpecification.responseSpec(200));

        return given()
                .when()
                .get("/" + id)
                .then()
                .log().all()
                .extract().as(User.class);
    }

    @Step("Обновление пользователя по id: {id}")
    public static User putUserById(int id, User user) {
        // Установка спецификации
        ApiSpecification.installSpecification(ApiSpecification.requestSpec(BASE_URL_USER),
                ApiSpecification.responseSpec(200));

        return given()
                .body(user)
                .when()
                .put("/" + id)
                .then()
                .log().all()
                .extract().as(User.class);
    }

    @Step("Удаление пользователя по id: {id}")
    public static User deleteUserById(int id) {
        // Установка спецификации
        ApiSpecification.installSpecification(ApiSpecification.requestSpec(BASE_URL_USER),
                ApiSpecification.responseSpec(200));

        return given()
                .when()
                .delete("/" + id)
                .then()
                .log().all()
                .extract().as(User.class);
    }
}