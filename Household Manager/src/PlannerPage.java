import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class PlannerPage extends JFrame {
    private JPanel[] days;
    private JPanel top;
    private JPanel page;
    private JPanel content;
    private JButton addButton;
    private JButton removeButton;
    private JLabel label;
    private Day[] emptySchedule = new Day[7];
    private databaseConnectionPlanner plannerData = new databaseConnectionPlanner();
    PlannerPage(Day[] schedule){
        createPlanner(this,schedule);
    }
    PlannerPage(){
        for(int i=0;i<7;i++){
            emptySchedule[i] = new Day();
        }
        createPlanner(this,emptySchedule);
    }
    private void createPlanner(JFrame planner, Day[] schedule){

        days = new JPanel[7];

        initializeDays(schedule);
        top = new JPanel(new BorderLayout());

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        top.setPreferredSize(new Dimension(screen.width,screen.height/10));

        content = new JPanel(new GridLayout(1,7));
        page = new JPanel(new BorderLayout());

        for(int i=0;i<7;i++){
            content.add(days[i]);
        }

        initializeTop();

        page.add(top, BorderLayout.NORTH);
        page.add(content, BorderLayout.CENTER);

        planner.add(page);
        planner.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        planner.setExtendedState(JFrame.MAXIMIZED_BOTH);
        planner.setLocationRelativeTo(null);
        planner.setVisible(true);
        planner.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                plannerData.setPlannerData(schedule);
                new StartUpPage();
            }
        });
    }
    private void initializeTop(){

        LocalDate monday = LocalDate.now();
        while(monday.getDayOfWeek() != DayOfWeek.MONDAY){
            monday = monday.minusDays(1);
        }

        LocalDate sunday = LocalDate.now();
        while(sunday.getDayOfWeek() != DayOfWeek.SUNDAY){
            sunday = sunday.plusDays(1);
        }

        String weekStart = monday.getDayOfMonth() + "." + monday.getMonthValue();
        String weekEnd = sunday.getDayOfMonth() + "." + sunday.getMonthValue();

        label = new JLabel(weekStart + " - " + weekEnd, SwingConstants.CENTER);

        label.setFont(new Font(label.getFont().getName(), Font.PLAIN,30));
        top.add(label, BorderLayout.CENTER);

    }
    private void initializeDays(Day[] schedule){
        for(int i=0;i<7;i++){
            days[i] = new JPanel(new GridLayout(15,1));
            JLabel dayName = new JLabel("", SwingConstants.CENTER);
            dayName.setFont(new Font(dayName.getFont().getName(), Font.PLAIN, 20));
            Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
            dayName.setBorder(border);
            switch (i) {
                case 0 -> dayName.setText("Monday");
                case 1 -> dayName.setText("Tuesday");
                case 2 -> dayName.setText("Wednesday");
                case 3 -> dayName.setText("Thursday");
                case 4 -> dayName.setText("Friday");
                case 5 -> dayName.setText("Saturday");
                case 6 -> dayName.setText("Sunday");
            }
            days[i].add(dayName);
            for(int j=8;j<22;j++) {
                JPanel hoursPanel = new JPanel(new BorderLayout());
                JLabel hours = new JLabel(j + "-" + (j + 1) + ":");
                hoursPanel = new JPanel(new BorderLayout());
                JLabel text = new JLabel(schedule[i].text[j - 8]);

                int finalI = i;
                int finalJ = j - 8;
                hoursPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        addOrRemove(finalI, finalJ, schedule);
                    }
                });

                if (!text.getText().equals("")) {
                    hours.setOpaque(true);
                    hours.setBackground(Color.GRAY);
                    hours.setForeground(Color.WHITE);

                    text.setOpaque(true);
                    text.setBackground(Color.GRAY);
                    text.setForeground(Color.WHITE);
                }
                hoursPanel.setBorder(border);
                hoursPanel.add(hours, BorderLayout.WEST);
                hoursPanel.add(text);
                days[i].add(hoursPanel);
            }
        }
    }
    private void addOrRemove(int day, int hour, Day[] schedule){
        String[] options = {"Add activity","Remove activity"};
        int result = JOptionPane.showOptionDialog(new JFrame(),
                "Please select an action!",
                "Action",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                null);

        if(result==JOptionPane.YES_OPTION){
            String message = "";
            if(!schedule[day].text[hour].equals("")){
                message = "Are you sure you want to change the existing activity?\nIf yes, please write new activity.";
            }
            else{
                message = "Please input new activity.";

            }
            String changeAct = JOptionPane.showInputDialog(new JFrame(), message);
            if(changeAct!=null) {
                schedule[day].text[hour] = changeAct;
            }
        }
        else if(result==JOptionPane.NO_OPTION){
            schedule[day].text[hour]="";
        }
        new PlannerPage(schedule);
        this.dispose();
    }

}
