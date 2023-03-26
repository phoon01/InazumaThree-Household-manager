import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class databaseConnectionProducts {
    private String url = "jdbc:postgresql://10.101.0.244:5432/km1";
    private String user = "postgres";
    private String pass = "victor";
    private String tableName = "\"Cookings\"";
    private Connection connection;
    private Statement statement;

    databaseConnectionProducts() {

    }

    public Cooking[] getCookingData() {
        Cooking[] cookings = new Cooking[0];

        try {
            connection = DriverManager.getConnection(url, user, pass);
            String selectQuery = "select * from " + tableName;
            statement = connection.createStatement();
            statement.execute(selectQuery);

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {

                String name = resultSet.getString("name");
                int expireYear = resultSet.getInt("expireYear");
                int expireMonth = resultSet.getInt("expireMonth");
                int expireDay = resultSet.getInt("expireDay");

                Cooking newCooking = new Cooking(name, expireDay, expireYear, expireMonth);

                //System.out.printf(newCooking.toString());

                cookings = addCooking(newCooking, cookings);

            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return cookings;
    }

    private Cooking[] addCooking(Cooking add, Cooking[] cookings) {
        Cooking[] newCooking = new Cooking[cookings.length + 1];
        int k = 0;
        for (Cooking aux : cookings) {
            newCooking[k] = aux;
            k++;
        }
        newCooking[cookings.length] = add;
        return newCooking;
    }

    public void setCookingData(Cooking[] cookings) {
        try {
            connection = DriverManager.getConnection(url, user, pass);
            String deleteQuery = "delete from " + tableName;
            statement = connection.createStatement();
            statement.execute(deleteQuery);
            for (Cooking aux : cookings) {
                //System.out.println(aux.toString());
                //System.out.println("da");
                String insertQuery = "insert into " + tableName +
                        " values('" + aux.getName() +
                        "','" + aux.getExpireYear() +
                        "','" + aux.getExpireMonth() +
                        "','" + aux.getExpireDay() + "')";
                statement.execute(insertQuery);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
