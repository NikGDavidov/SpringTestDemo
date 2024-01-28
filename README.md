    * Доделать сервис управления книгами:
    * 1.1 Реализовать контроллер по управлению книгами с ручками: GET /book/{id} - получить описание книги, DELETE /book/{id} - удалить книгу, POST /book - создать книгу
    * 1.2 Реализовать контроллер по управлению читателями (аналогично контроллеру с книгами из 1.1)
    *  1.3 В контроллере IssueController добавить ресурс GET /issue/{id} - получить описание факта выдачи
   
    *  2.1 В сервис IssueService добавить проверку, что у пользователя на руках нет книг. Если есть - не выдавать книгу (статус ответа - 409 Conflict)
    *  2.2 В сервис читателя добавить ручку GET /reader/{id}/issue - вернуть список всех выдачей для данного читателя
   
    *  Пункт 2.1 расширить параметром, сколько книг может быть на руках у пользователя,
       должно задаваться в конфигурации (параметр application.issue.max-allowed-books). Если параметр не задан - то использовать значение 1.

    * `Добавить следующие рерурсы,
    * которые возвращают готовые HTML-страницы (т.е. это просто Controller'ы):
    *  3.1 /ui/books - на странице должен отобразиться список всех доступных книг в системе
    *  3.2 /ui/reader - аналогично 
    *  3.3 /ui/issues - на странице отображается таблица, в которой есть столбцы (книга, читатель, когда взял, когда вернул (если не вернул - пустая ячейка)).

    
    * 4. Подключить базу данных к проекту "библиотека", который разрабатывали на прошлых уроках.
    * 4.1 Подключить spring-boot-starter-data-jpa (и необходимый драйвер) и указать параметры соединения в application.yml
    * 4.2 Для книги, читателя и факта выдачи описать JPA-сущности
    * 4.3 Заменить самописные репозитории на JPA-репозитории
    *
    * Замечание: базу данных можно использовать любую (h2, mysql, postgres).
      */