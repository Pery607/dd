package visual.drawer;

import Model.Exercise_Type;
import Model.StudentLessons;
import Model.Studentinfo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RectangleInsets;
import visual.mapper.ChartDataMapper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BarCharDrawForStudentsPoints extends JFrame {
    public BarCharDrawForStudentsPoints(String title, ArrayList<StudentLessons> students, String name, Exercise_Type type) {
        super(title);
        setContentPane(creataStudentsPointPanel(students,name,type));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(680, 300);
    }

    public static JPanel creataStudentsPointPanel(ArrayList<StudentLessons> students,String name,Exercise_Type type) {
        JFreeChart chart = createBarChart(ChartDataMapper.creadetStudentsPoints(students,name,type));
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        return new ChartPanel(chart);
    }

    public static JFreeChart createBarChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Student Points",
                "Exercise Name",
                "Execrcise Point",
                dataset,
                PlotOrientation.HORIZONTAL,
                false,
                false,
                false
        );
        chart.setBackgroundPaint(Color.white);
        return chart;

    }
}
