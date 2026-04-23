package api.tests;

import api.model.user.Address;
import api.model.user.Geolocation;
import api.model.user.Name;
import api.model.user.User;
import api.steps.UserSteps;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Feature("Api Test User")
public class UserTest {

    @Test
    @DisplayName("Проверка получения списка всех пользователей")
    public void testGetAllUsers() {
        // Запрос списка пользователей
        List<User> users = UserSteps.getAllUsers();

        // Проверка непустоты списка
        assertFalse(users.isEmpty(), "Список пуст");

        // Проверка количества пользователей
        assertEquals(10, users.size(), "Количество товаров не соответствует ожидаемому");

        // Проверка уникальности id
        List<Integer> ids = users.stream().map(User::getId).toList();
        long uniqueIdsCount = ids.stream().distinct().count();
        assertEquals(users.size(), uniqueIdsCount, "Не все id в списке являются уникальными");

        // Проверка заполненности полей
        for (User user : users) {
            assertNotNull(user.getAddress().getGeolocation().getLat(), "Поле address.geo.lat пустое");
            assertNotNull(user.getAddress().getGeolocation().getMyLong(), "Поле address.geo.long пустое");
            assertNotNull(user.getAddress().getCity(), "Поле address.city пустое");
            assertNotNull(user.getAddress().getStreet(), "Поле address.street пустое");
            assertNotNull(user.getAddress().getNumber(), "Поле address.number пустое");
            assertNotNull(user.getAddress().getZipcode(), "Поле address.zipcode пустое");
            assertNotNull(user.getId(), "Поле id пустое");
            assertNotNull(user.getEmail(), "Поле email пустое");
            assertNotNull(user.getUsername(), "Поле username пустое");
            assertNotNull(user.getPassword(), "Поле password пустое");
            assertNotNull(user.getName().getFirstname(), "Поле name.firstname пустое");
            assertNotNull(user.getName().getLastname(), "Поле name.lastname пустое");
            assertNotNull(user.getPhone(), "Поле phone пустое");
            assertNotNull(user.get__v(), "Поле __v пустое");
        }
    }

    @Test
    @DisplayName("Проверка добавления нового пользователя")
    public void testPostNewUser() {
        // Создание тестового пользователя
        User user = User.builder()
                .email("john1@gmail.com")
                .username("johnd1")
                .password("m381rmF$")
                .build();

        // Запрос на создание нового пользователя
        User newUser = UserSteps.createUser(user);

        // Проверка id нового пользователя
        assertNotNull(newUser.getId(), "Новый товар должен иметь id после создания.");
    }

    @Test
    @DisplayName("Проверка получения пользователя по id")
    public void testGetUserWithId() {
        // Создание ожидаемого пользователя
        User expectedUser = User.builder()
                .address(Address.builder()
                        .geolocation(Geolocation.builder().lat("-37.3159").myLong("81.1496").build())
                        .city("kilcoole")
                        .street("new road")
                        .number(7682)
                        .zipcode("12926-3874")
                        .build())
                .id(1)
                .email("john@gmail.com")
                .username("johnd")
                .password("m38rmF$")
                .name(Name.builder().firstname("john").lastname("doe").build())
                .phone("1-570-236-7033")
                .__v(0)
                .build();

        // Запрос пользователя по id
        User user = UserSteps.getUserById(1);

        // Проверка полей нового пользователя с исходным
        assertEquals(expectedUser.getAddress().getGeolocation().getLat(), user.getAddress().getGeolocation().getLat(),
                "Поле address.geo.lat не совпадает");
        assertEquals(expectedUser.getAddress().getGeolocation().getMyLong(), user.getAddress().getGeolocation().getMyLong(),
                "Поле address.geo.long не совпадает");
        assertEquals(expectedUser.getAddress().getCity(), user.getAddress().getCity(),
                "Поле address.city не совпадает");
        assertEquals(expectedUser.getAddress().getStreet(), user.getAddress().getStreet(),
                "Поле address.street не совпадает");
        assertEquals(expectedUser.getAddress().getNumber(), user.getAddress().getNumber(),
                "Поле address.number не совпадает");
        assertEquals(expectedUser.getAddress().getZipcode(), user.getAddress().getZipcode(),
                "Поле address.zipcode не совпадает");
        assertEquals(expectedUser.getId(), user.getId(), "Поле id не совпадает");
        assertEquals(expectedUser.getEmail(), user.getEmail(), "Поле email не совпадает");
        assertEquals(expectedUser.getUsername(), user.getUsername(), "Поле username не совпадает");
        assertEquals(expectedUser.getPassword(), user.getPassword(), "Поле password не совпадает");
        assertEquals(expectedUser.getName().getFirstname(), user.getName().getFirstname(),
                "Поле name.firstname не совпадает");
        assertEquals(expectedUser.getName().getLastname(), user.getName().getLastname(),
                "Поле name.lastname не совпадает");
        assertEquals(expectedUser.getPhone(), user.getPhone(), "Поле phone не совпадает");
        assertEquals(expectedUser.get__v(), user.get__v(), "Поле __v не совпадает");
    }

    @Test
    @DisplayName("Проверка обновления пользователя по id")
    public void testPutUserWithId() {
        // Создание ожидаемого пользователя
        User expectedUser = User.builder()
                .address(Address.builder()
                        .geolocation(Geolocation.builder().lat("-37.3158").myLong("81.1596").build())
                        .city("kilcoole1")
                        .street("new road1")
                        .number(7681)
                        .zipcode("12926-3876")
                        .build())
                .id(1)
                .email("john1@gmail.com")
                .username("johnd1")
                .password("m381rmF$")
                .name(Name.builder().firstname("john1").lastname("doe1").build())
                .phone("1-570-236-7034")
                .__v(1)
                .build();

        // Запрос пользователя по id
        User user = UserSteps.putUserById(1, expectedUser);

        // Проверка полей нового пользователя с исходным
        assertEquals(expectedUser.getAddress().getGeolocation().getLat(), user.getAddress().getGeolocation().getLat(),
                "Поле address.geo.lat не совпадает");
        assertEquals(expectedUser.getAddress().getGeolocation().getMyLong(), user.getAddress().getGeolocation().getMyLong(),
                "Поле address.geo.long не совпадает");
        assertEquals(expectedUser.getAddress().getCity(), user.getAddress().getCity(),
                "Поле address.city не совпадает");
        assertEquals(expectedUser.getAddress().getStreet(), user.getAddress().getStreet(),
                "Поле address.street не совпадает");
        assertEquals(expectedUser.getAddress().getNumber(), user.getAddress().getNumber(),
                "Поле address.number не совпадает");
        assertEquals(expectedUser.getAddress().getZipcode(), user.getAddress().getZipcode(),
                "Поле address.zipcode не совпадает");
        assertEquals(expectedUser.getId(), user.getId(), "Поле id не совпадает");
        assertEquals(expectedUser.getEmail(), user.getEmail(), "Поле email не совпадает");
        assertEquals(expectedUser.getUsername(), user.getUsername(), "Поле username не совпадает");
        assertEquals(expectedUser.getPassword(), user.getPassword(), "Поле password не совпадает");
        assertEquals(expectedUser.getName().getFirstname(), user.getName().getFirstname(),
                "Поле name.firstname не совпадает");
        assertEquals(expectedUser.getName().getLastname(), user.getName().getLastname(),
                "Поле name.lastname не совпадает");
        assertEquals(expectedUser.getPhone(), user.getPhone(), "Поле phone не совпадает");
        assertEquals(expectedUser.get__v(), user.get__v(), "Поле __v не совпадает");
    }

    @Test
    @DisplayName("Проверка удаления пользователя по id")
    public void testDeleteUserWithId() {
        // Запрос на удаление пользователя
        User user = UserSteps.deleteUserById(1);

        // Проверка, что ответ с удаленным пользователем содержит корректный id
        assertEquals(1, user.getId());
    }
}