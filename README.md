# ElectronicShop
Магазин электроники

Запуск приложения:
1. Клонируем проект
2. Назначаем JDK(используется 11, т.к. 8 не нашел)
3. Пишем команду чтоб подтянулись все зависимости
```bash
mvn clean install
```
4. в файле ```src/main/resources/application.properties``` настраиваем конфигурацию с БД
5. Запускаем программу ```src/main/java/com/example/demo/ElectronicsStoreApplication.java```

Для просмотра документации использовать SwaggerUI (http://localhost:8080/swagger-ui/index.html)