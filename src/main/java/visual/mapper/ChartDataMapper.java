package visual.mapper;

import Model.*;
import jdk.jfr.Category;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChartDataMapper {
    public static PieDataset createPiedataset(List<StudentLessons> studentslesson,Exercise_Type type) {
        var studentlesson = studentslesson.stream()
                .filter(x->x.getType()== type)
                .collect(
                        Collectors.groupingBy(
                                StudentLessons::getExerciseName,
                                HashMap::new,
                                Collectors.summingInt(StudentLessons::getPoint)
                        )
                );
        DefaultPieDataset dataset=new DefaultPieDataset();
        studentlesson.forEach(dataset::setValue);
        return dataset;
    }
    public static CategoryDataset creadefriendlyStudents(List<Studentinfo> students, int minCount){
        var dataset=new DefaultCategoryDataset();
        students.stream().filter(x->x.getFriendCount()>minCount).forEach(x->dataset.setValue(x.getFriendCount(),"friendsCount", x.getName()));
        return dataset;
    }
    public static PieDataset creadeMustBeneficialLesson(List<Lessons> lessons){
        var lesson = lessons.stream()
                .collect(
                        Collectors.groupingBy(
                                Lessons::getLessonName,
                                HashMap::new,
                                Collectors.summingInt(Lessons::getMaxPoint)
                        )
                );
        DefaultPieDataset dataset=new DefaultPieDataset();
        lesson.forEach(dataset::setValue);
        return dataset;
    }
    public static CategoryDataset creadetStudentsActPoints(List<Studentinfo> students,int min){
        var dataset=new DefaultCategoryDataset();
        students.stream().filter(x->x.getAllActScores()>min)
                .forEach(x->dataset.setValue(x.getAllActScores(),"pointForAct", x.getName()));
        return dataset;
    }
    public static CategoryDataset creadetStudentsSemPoints(List<Studentinfo> students,int min){
        var dataset=new DefaultCategoryDataset();
        students.stream().filter(x->x.getAllSemScores()>min)
                .forEach(x->dataset.setValue(x.getAllSemScores(),"pointForAct", x.getName()));
        return dataset;
    }
    public static CategoryDataset creadetStudentsPoints(List<StudentLessons> students,String name,Exercise_Type type){
        var dataset=new DefaultCategoryDataset();
        students.stream().filter(x-> Objects.equals(x.getStudentName(), name))
                .filter(y->y.getType()==type)
                .forEach(x->dataset.setValue(x.getPoint(),"pointForAct", x.getExerciseName()));
        return dataset;
    }
}
