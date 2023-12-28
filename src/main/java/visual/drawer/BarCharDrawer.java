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

import static visual.mapper.ChartDataMapper.creadefriendlyStudents;

public class BarCharDrawer extends JFrame {
    public BarCharDrawer(String title, ArrayList<Studentinfo> students,int mincount){
        super(title);
        setContentPane(creataStudentsFriendsPanel(students,mincount));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(680,300);
    }
    public static JPanel creataStudentsFriendsPanel(ArrayList<Studentinfo> students,int mincount){
        JFreeChart chart=createBarChart(ChartDataMapper.creadefriendlyStudents(students,mincount));
        chart.setPadding(new RectangleInsets(4,8,2,2));
        return new ChartPanel(chart);
    }
    public  static JFreeChart createBarChart(CategoryDataset dataset){
        JFreeChart chart= ChartFactory.createBarChart(
                "CountStudentsFriend",
                "Student Name",
                "Friends Count",
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
