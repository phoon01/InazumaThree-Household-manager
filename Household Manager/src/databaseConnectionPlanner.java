import java.sql.*;

public class databaseConnectionPlanner {
    private String url = "jdbc:postgresql://10.101.0.244:5432/km1";
    private String user = "postgres";
    private String pass = "victor";
    private String tableName = "\"table_name\"";
    private Connection connection;
    private Statement statement;

    databaseConnectionPlanner() {

    }

    public Day[] getPlannerData() {
        Day[] schedule = new Day[7];
        for (int i = 0; i < 7; i++) {
            schedule[i] = new Day();
        }
        try {

            connection = DriverManager.getConnection(url, user, pass);
            String getDataQuery = "select * from " + tableName;
            statement = connection.createStatement();
            statement.execute(getDataQuery);

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {

                int day = resultSet.getInt("day");
                int hour = resultSet.getInt("startHour");
                String activity = resultSet.getString("activityName");
                schedule[day].text[hour] = activity;

            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return schedule;
    }

    public void setPlannerData(Day[] schedule) {
        try {
            connection = DriverManager.getConnection(url, user, pass);
            String deleteQuery = "delete from " + tableName;
            statement = connection.createStatement();
            statement.execute(deleteQuery);

            for (int i = 0; i < 7; i++) {
                for (int j = 8; j < 22; j++) {
                    String putData = "insert into " + tableName +
                            " values('" + i + "','" + (j - 8) +
                            "','" + schedule[i].text[j - 8] + "')";
                    statement.execute(putData);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
