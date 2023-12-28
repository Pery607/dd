package visual.drawer;

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

public class BarCharDrawerForStudentsAct extends JFrame{
    public BarCharDrawerForStudentsAct(String title, ArrayList<Studentinfo> students,int min){
        super(title);
        setContentPane(creataStudentsPointForActPanel(students,min));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(680,300);
    }
    public static JPanel creataStudentsPointForActPanel(ArrayList<Studentinfo> students, int min){
        JFreeChart chart=createBarChart(ChartDataMapper.creadetStudentsActPoints(students,min));
        chart.setPadding(new RectangleInsets(4,8,2,2));
        return new ChartPanel(chart);
    }
    public  static JFreeChart createBarChart(CategoryDataset dataset){
        JFreeChart chart= ChartFactory.createBarChart(
                "Point Students for act",
                "Student Name",
                "Act Point",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );
        chart.setBackgroundPaint(Color.white);
        return chart;
    }
}
