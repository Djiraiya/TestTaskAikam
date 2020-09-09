# TestTaskAikam

## Общее описание
Разработанное приложение предоставляет сервис работы с данными в БД. Данный сервис, на
основании входных параметров(аргументы командной строки), типа операции и входного файла – извлекает
необходимые данные из БД и формирует результат обработки в выходной файл.

## Запуск
Первым делом необходимо в классе **DBConnect.java** изменить данные для подключения к базе данных.

Файл с дампом базы данных: **TestTaskAikam/src/main/resources/AikamDBDump.sql**

Входные файлы: для операции **search - TestTaskAikam/src/main/resources/inputSearch.json**,
для операции **stat - TestTaskAikam/src/main/resources/inputStat.json**

Чтобы запустить приложение необходимо:
1. Создать базу данных и экспортировать данные из дампа. 
2. Собрать проект `mvn package`
3. Перейти в папку **target**
4. Выполнить запуск из командной строки

`java -jar TestTaskAikam-1.0-SNAPSHOT.jar stat inputStat.json statOutput.json`

stat или search - название возможной операции

inputStat.json - название входного файла (необходимо указывать полный путь до файла, пример: "C:\Users\Pr\Desktop\TestTaskAikam\src\main\resources\inputStat.json")

statOutput.json - название выходного файла (также необходимо указывать полный путь).
