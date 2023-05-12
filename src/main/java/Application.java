import java.sql.*;

public class Application {
    public static void main(String[] args) {

        //задаем параметры для подключения к базе данных
        final String user = "postgres";
        final String password = "1996Arslan";
        final String url = "jdbc:postgresql://localhost:5433/newbase";

        //создаем соединение с бд внутри try-with-resources
        try (final Connection connection =
                     DriverManager.getConnection(url, user, password);

             //создаем объект PreparedStatement с SQL запросом для выборки записей в таблице
             PreparedStatement statement =
                     connection.prepareStatement("SELECT *FROM books")){
            //выполняем sql запрос и получем результаты в виде ResultSet
            ResultSet resultSet = statement.executeQuery();

            // Обрабатываем результаты запроса, перебирая все записи в таблице
            while (resultSet.next()) {
                // получаем значение полей и выводим их на экран
                int idOfBook = resultSet.getInt("book_id");
                System.out.println("ID книги: " + idOfBook);

                String titleOfBook = resultSet.getString("title");
                int authorOfBook = resultSet.getInt("author_id");
                int amountOfBook = resultSet.getInt("amount");

                System.out.println("Название: " + titleOfBook);
                System.out.println("ID автора: " + authorOfBook);
                System.out.println("Количество: " + amountOfBook);
            }
        } catch (SQLException e) {
            // в случае возникновения ошибки при работе с бд,
            // выводит сообщение об ошибке и информацию об исключении
            System.out.println("Ошибка при подключени к базе данных!");
            e.printStackTrace();
        }
    }
}
