import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class databaseConnectionDevices {
    private String url = "jdbc:postgresql://10.101.0.244:5432/km1";
    private String user = "postgres";
    private String pass = "victor";
    private String tableName = "\"Devices\"";
    private Connection connection;
    private Statement statement;

    databaseConnectionDevices(){

    }

    public Device[] getDeviceData(){
        Device[] devices = new Device[0];
        try{
            connection = DriverManager.getConnection(url,user,pass);
            String selectQuery = "select * from " + tableName;
            statement = connection.createStatement();
            statement.execute(selectQuery);

            ResultSet resultSet = statement.getResultSet();

            while(resultSet.next()){
                String name = resultSet.getString("name");
                String status = resultSet.getString("status");
                int month = resultSet.getInt("month");
                int day = resultSet.getInt("day");
                int hour = resultSet.getInt("hour");
                int minute = resultSet.getInt("minute");

                LocalDateTime dateTime = LocalDateTime.of(LocalDate.now().getYear(),
                        month,day,hour,minute);

                Device newDevice = new Device();
                newDevice.setDateTime(dateTime);
                newDevice.setName(name);
                newDevice.setStatus(status);

                System.out.println(newDevice);

                devices = AddDevice(newDevice,devices);

            }

        }
        catch(SQLException e){
            System.out.println(e);
        }
        return devices;
    }
    private Device[] AddDevice(Device device,Device[] devices){
        Device[] newDevice = new Device[devices.length+1];
        for(int i = 0; i < devices.length; i++) {
            newDevice[i] = devices[i];
        }
        newDevice[devices.length]=device;
        return newDevice;
    }

    public void setDeviceData(Device[] devices){

        try{
            connection = DriverManager.getConnection(url,user,pass);
            String deleteQuery = "delete from " + tableName;
            statement = connection.createStatement();
            statement.execute(deleteQuery);
            for(Device aux : devices){
                String insertQuery = "insert into " + tableName +
                        " values('" + aux.getName() + "','" + aux.getStatus() +
                        "','" + aux.getDateTime().getMonthValue() +
                        "','" + aux.getDateTime().getDayOfMonth() +
                        "','" + aux.getDateTime().getHour() +
                        "','" + aux.getDateTime().getMinute() +"')";
                statement.execute(insertQuery);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

    }

}
