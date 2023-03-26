public class Day {
    public String[] text = new String[14];//on position i stores the activity for hour (i+8 -> i+8+1)

    Day() {
        for (int i = 0; i < 14; i++) {
            text[i] = "";
        }
    }

    Day(String[] text) {
        this.text = text;
    }
}
