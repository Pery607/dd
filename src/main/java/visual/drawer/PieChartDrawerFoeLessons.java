package visual.drawer;

import Model.Lessons;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RectangleInsets;
import visual.mapper.ChartDataMapper;


import javax.swing.*;
import java.util.ArrayList;

public class PieChartDrawerFoeLessons extends JFrame{
    public PieChartDrawerFoeLessons(String title, ArrayList<Lessons> lessons){
        super(title);
        setContentPane(createLessonsByMaxpoint(lessons));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(600,300);
    }
    public static JPanel createLessonsByMaxpoint(ArrayList<Lessons> lessons){
        JFreeChart chart=createPieChartLessons(ChartDataMapper. creadeMustBeneficialLesson(lessons));
        chart.setPadding(new RectangleInsets(4,8,2,2));
        return new ChartPanel(chart);
    }
    public static JFreeChart createPieChartLessons(PieDataset dataset){
        JFreeChart chart= ChartFactory.createPieChart(
                "the number of points in the lessons",
                dataset,
                false,true,false
        );
        return chart;
    }
}
