package Db;


import Db.Model.LessonEntity;
import Db.Model.StudentEntity;
import Db.Model.StudentLessonEntity;
import Model.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

public class ORM {
    private ConnectionSource connectionSource = null;
    private final String URL = "jdbc:sqlite:C:\\Users\\User-004\\Downloads\\untitled1\\db\\bas.db";
    private Dao<StudentEntity,String> studentDao=null;
    private Dao<LessonEntity,String> lessonDao=null;
    private Dao<StudentLessonEntity,String> studentLessonsDao=null;

    public void connect() throws SQLException {
        connectionSource=new JdbcConnectionSource(URL);
        studentDao=DaoManager.createDao(connectionSource,StudentEntity.class);
        lessonDao = DaoManager.createDao(connectionSource, LessonEntity.class);
        studentLessonsDao=DaoManager.createDao(connectionSource, StudentLessonEntity.class);
    }
    public void createTable() throws SQLException {
        TableUtils.createTable(connectionSource,StudentEntity.class);
        TableUtils.createTable(connectionSource, LessonEntity.class);
        TableUtils.createTable(connectionSource,StudentLessonEntity.class);

    }

    public void saveStudents(List<StudentAllInfo> students) throws SQLException {
        for(var student:students){
            studentDao.create(new StudentEntity(student.getName(),student.getGroup(),student.getAllActScores(),student.getAllHwScores(),student.getAllSemScores(),student.getAllPractScores(),student.getAllPoint(),student.getFriendCount()));
        }
    }
    public List<StudentEntity> getStudents() throws SQLException {
        return studentDao.queryForAll();
    }
    public List<StudentEntity> getStudentsByName(String name) throws SQLException {
        return studentDao.queryBuilder()
                .where()
                .eq(StudentEntity.NAME_COLUMN,name)
                .query();
    }
    public void saveLessons(List<StudentAllInfo> students) throws SQLException {
        var student=students.get(0);
        for(var lesson: student.getLessons()){
            for (var exercise:lesson.getExercises()){
                lessonDao.create(new LessonEntity(lesson.getName(),exercise.getName(),exercise.getType(),exercise.getMaxScore()));
            }
        }
    }
    public List<LessonEntity> getLessons() throws SQLException {
        return lessonDao.queryForAll();
    }
    public List<LessonEntity> getLessonssByName(String lessonName) throws SQLException {
        return lessonDao.queryBuilder()
                .where()
                .eq(StudentEntity.NAME_COLUMN,lessonName)
                .query();
    }
    public void saveStudentLesson(List<StudentAllInfo> students) throws SQLException {
        for(var student:students) {
            for (var lesson : student.getLessons()) {
                for (var exercise : lesson.getExercises()) {
                    studentLessonsDao.create(new StudentLessonEntity(student.getName(),lesson.getName(), exercise.getName(), exercise.getType(), exercise.getPointsScored()));
                }
            }
        }
    }
    public List<StudentLessonEntity> getStudentLessons() throws SQLException {
        return studentLessonsDao.queryForAll();
    }
    public List<StudentLessonEntity> getStudentLessonssByName(String studentName) throws SQLException {
        return studentLessonsDao.queryBuilder()
                .where()
                .eq(StudentEntity.NAME_COLUMN,studentName)
                .query();
    }
    public void close() throws Exception {
        connectionSource.close();
    }
    public boolean studentsIsNull(){
        return studentDao==null;
    }
    public boolean studentsLessonsIsNull(){
        return studentLessonsDao==null;
    }
    public boolean lessonsIsNull(){
        return lessonDao==null;
    }


}

