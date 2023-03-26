import java.time.LocalDateTime;

public class Device {
    private String name;
    private String status;
    private LocalDateTime dateTime;

    public Device() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return this.name + "    " + this.status + "    " + this.dateTime.getMonth().toString() + "/" + this.dateTime.getDayOfMonth() + "-" + this.dateTime.getHour() + ":" + this.dateTime.getMinute() + "\n";
    }
}

