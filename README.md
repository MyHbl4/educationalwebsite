# Educational website #

_Educational website - это справочный и образовательный сервис для специалистов определенной сферы деятельности для обмена опытом, в образовательных целях._

## Сервис обладает следующей фунциональностью ##

* Регистрация пользователей (администраторы и простые пользователи). 
* Редактирование профиля пользователя. 
* Справочник областей знаний по темам и разделам. 
* Просмотр списка доступных занятий, курсов. Поиск и фильтрация по характеристикам занятий, курсов.
* Возможность добавления / редактирования/ удаления занятий, курсов, элементов справочника.
* Возможность подписка на существующие занятия и курсы, возможность создавать свои занятия и курсы.
* Система вознаграждения для преподавателей (возможность назначать фиксированную либо не фиксированную цену).
* Простое расписание для участника проекта. 
* Возможность индивидуальных и групповых занятий.
* Возможность оставлять отзывы о занятиях, курсах.

## При разработке проекта использовалось ##
1. Java - 1.8
2. Spring Boot - 2.6.2
3. PostgreSQL - 42.3.1
4. Mapstruct - 1.4.2.Final
5. Javax - 8.0.1
6. Lombok - 1.18.22
7. Jsonwebtoken - 0.9.1
8. Liquibase - 4.5.0
9. Swagger2 - 2.9.2

## Проограмное обеспечение необходимое для запуска приложения ##
1. Project SDK - 1.8
2. PostgreSQL - 14
3. Apache-maven - 3.8.2

## Установка / Начало работы ##
### 1. Необходимо клонировать репозиторий 
```
  git@github.com:MyHbl4/educationalwebsite.git
```
### 2. Заходим в PostgreSQL и создаём базу данных
```
  drop database if exists educationalwebsite;
  create database educationalwebsite;
```
### 3. Компилируем приложение и создаём .jar
* Запускаем [build.bat](https://github.com/MyHbl4/educationalwebsite/blob/master/build.bat "Goto build.bat") 
```
  mvn clean install
```
### 3. Запускаем приложение
* Запускаем [run.bat](https://github.com/MyHbl4/educationalwebsite/blob/master/run.bat "Goto run.bat")
```
  java -jar target/educational_website-0.0.1-SNAPSHOT.jar
```
или
```
  mvn spring-boot:run
```
### 4. Тесты
```
  mvn test
```
____
<a href="http://localhost:9000/swagger-ui.html#/">Swagger-UI</a> позволяет проверить фунциональность сервиса.

![picture alt](https://sun9-87.userapi.com/impg/dZu8DuzfC_QG-cJGztGtKnt80WaV5nbjcF4EZA/J2sNFQLAYKs.jpg?size=1920x1080&quality=96&sign=d57ef818a07a08d534244d128a472a16&type=album "Swagger-ui")
