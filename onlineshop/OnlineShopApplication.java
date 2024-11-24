package de.telran.onlineshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineShopApplication {
//В проект работи в классе добавлен файл DB.png, в котором описана
// примерная структура БД online магазина.
//Для каждой таблицы создайте Entity класс для отражения ее в ORM.
//Создайте также интерфейс репозитория для работы с каждой таблицей.
//Переработайте наши Service классы для работы с БД через интерфейсы репозиториев,
// а также заполните первоначальными значениями наши таблицы используя метод,
// помеченный аннотацией @PostConstruct.
//Переработайте метод GET контроллера для отображения всех строк из таблицы БД.
//1) Users - пользователи
//2) Products - товары
//*7) Favorites - избранные товары
//*8) Categories - категории товаров.

	public static void main(String[] args) {
		SpringApplication.run(OnlineShopApplication.class, args);
	}

}
