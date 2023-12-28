package visual.drawer;


import Model.Exercise_Type;
import Model.StudentLessons;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RectangleInsets;
import visual.mapper.ChartDataMapper;

import javax.swing.*;
import java.util.ArrayList;

public class PieChartDraiwer extends JFrame {
    public PieChartDraiwer(String title, ArrayList<StudentLessons> studentArrayList,Exercise_Type type){
        super(title);
        setContentPane(createStudentsByExercise(studentArrayList,type));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(600,300);
    }
    public static JPanel createStudentsByExercise(ArrayList<StudentLessons> studentLessons, Exercise_Type type){
        JFreeChart chart=createPieChart(ChartDataMapper.createPiedataset(studentLessons,type));
        chart.setPadding(new RectangleInsets(4,8,2,2));
        return new ChartPanel(chart);
    }
    public static JFreeChart createPieChart(PieDataset dataset){
        JFreeChart chart= ChartFactory.createPieChart(
                "how student completed tasks",
                dataset,
                false,true,false
        );
        return chart;
    }

}

