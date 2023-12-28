package Db.Mapper;

import Db.Model.StudentEntity;
import Model.Studentinfo;

public class StudentFromDBMapper {
    public static Studentinfo map(StudentEntity student){
        return  new Studentinfo(student.getName(),student.getGroup(),student.getAllActScores(),student.getAllHwScores(),student.getAllSemScores(),student.getAllPractScores(),student.getFriendCount());
    }
}
