public class Cooking {
    private String name;
    private int expireMonth;
    private int expireDay;
    private int expireYear;

    public Cooking() {
        this.name = "";
        expireDay = expireMonth = expireYear = 0;
    }
    public Cooking(String name, int expireDay, int expireYear, int expireMonth){
        this.name = name;
        this.expireMonth = expireMonth;
        this.expireYear = expireYear;
        this.expireDay = expireDay;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpireMonth() {
        return expireMonth;
    }

    public void setExpireMonth(int expireMonth) {
        this.expireMonth = expireMonth;
    }

    public int getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(int expireDay) {
        this.expireDay = expireDay;
    }

    public int getExpireYear() {
        return expireYear;
    }

    public void setExpireYear(int expireYear) {
        this.expireYear = expireYear;
    }

    @Override
    public String toString() {
        return this.name + " " + this.expireDay +
                "/" + this.expireMonth + "/" + this.expireYear;
    }
}
