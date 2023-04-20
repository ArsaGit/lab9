package com.example.lab9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab9Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab9Application.class, args);
	}

}
/*
Реализуйте при помощи Spring MVC приложение, позволяющее вести список покупок:

Просматривать список покупок
Добавлять элемент в список
Удалять элемент из списка
Отмечать элемент купленным
Приложение должно быть реализовано на основе Spring Boot, без xml-конфигурации Spring

Клиентская часть должна работать в браузере

Серверная часть приложения должна взаимодействовать с клиентской при помощи REST-интерфейса
 */