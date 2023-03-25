public class Main {
    public static void main(String[] args) {
        Day[] d = new Day[7];
        for(int i=0;i<7;i++){
            d[i]=new Day();
        }
        String t1 = "Clean";
        String t2 = "Cook";
        d[0].text[3]=t1;
        d[2].text[6]=t2;
        new PlannerPage(d);
    }
}