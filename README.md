# First Lab

Лабораторна   є системою управління інформацією про книги та персонажів у них. Ця система дозволяє зберігати дані про різні книги, включаючи їх назву, авторів, дату виходу, кількість сторінок та коротку анотацію. Крім того, вона також включає інформацію про персонажів, які з'являються в цих книгах, включаючи їх імена (або псевдоніми), перелік книг, в яких вони згадуються, і рівень участі (головний, другорядний, епізодичний персонаж).

Лабораторна 1.
Functions: 131( 64(untrivial))

Fields:154( кожен клас, який діє як контролер JavaFX, має велику кількість полів). 

Всього класів: 29.

Classes: (Author(6 functions(all trival),3 fields),Book(16 functions(all trival),6 fields),MovieBasedSeries(3 functions(trival),1 field),UnofficialSeries(1 field,3 functions),Character(11 functions(all trival),4 fields), AuthorDAO (5 functions,4 fields),BookDAO(7 functions , 4 fields),CharacterDAO(7 functions ,4 fields), SeriesDAO(6 functions, 4 fields),DataModel(6 functions(all trival), 4 fields), Connect(2 functions , 2 fields), ще 9 класів,що є підкласами класу Application в JavaFX, вони використовується для створення графічного користувацького інтерфейсу),10 класів -  є контролерами JavaFX, який управляє взаємодією з віджетами та подіями (events) для вікон(53 functions(25 тривіальних)).

Кожен з класів CanonicalSeries, MovieBasedSeries та UnofficialSeries реалізує інтерфейс Series.

Кожен з класів BookDAO,AuthorDAO,CharacterDAO,SeriesDAO реалізує інтерфейс DatabaseDAO.

# Technologies Used
JavaFX,
Java,
CSS (for styling),
MySQL (for database)
# Some screenshots
![image](https://github.com/Nastya231213/FirstLab/assets/122891769/9087841a-b4d4-4ca2-a10e-a13ee5a022f7)
![image](https://github.com/Nastya231213/FirstLab/assets/122891769/74cfe0c3-3a1f-4a39-87b3-4144c8bc702d)
![image](https://github.com/Nastya231213/FirstLab/assets/122891769/01e8c9e0-3c90-4033-943c-daa923e1e1f9)
![image](https://github.com/Nastya231213/FirstLab/assets/122891769/82393770-9d9c-47f9-a4c8-70385d6fc41d)
![image](https://github.com/Nastya231213/FirstLab/assets/122891769/3a5f3329-bc1a-47a5-8ca8-32abd0bfbd23)
![image](https://github.com/Nastya231213/FirstLab/assets/122891769/e316c8c2-7d0c-496d-a01c-496842c6ac6c)
![image](https://github.com/Nastya231213/FirstLab/assets/122891769/1d7ef1d0-41df-4368-81f7-5bb27efa284f)


# Features
Додавання книг до бібліотеки.
Пошук книг за назвою, автором,ID. Пошук авторів за псевдонімом, повним ім'ям,ID.
Редагування/видалення та додавання книг, серій книг, авторів,  додавання персонажів до деякої кількості книг, додавання книг до серії. 
