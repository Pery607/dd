package Db;

import Model.StudentAllInfo;

import java.sql.*;
import java.util.HashMap;
import java.util.List;

public class DataBase {
    private static Connection conn = null;
    private static final String URL = "jdbc:sqlite:C:\\Users\\User-004\\Downloads\\untitled1\\db\\base.db";


    public static void connect() {
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connect creat");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void createTableStudent() {
        String sql = "CREATE TABLE IF NOT EXISTS students (\n"
                + "id integer PRIMARY KEY,\n"
                + "fullname text NOT NULL,\n"
                + "allPoints integer NOT NULL,\n"
                + "friendsCount real\n"
                + ")\n";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement statement = conn.createStatement()) {
            statement.execute(sql);
            System.out.println("Table creat");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createTableStudentLesson() {
        String sql = "CREATE TABLE IF NOT EXISTS studentsLessons (\n"
                + "id integer PRIMARY KEY,\n"
                + "fullname text NOT NULL,\n"
                + "lessonName text NOT NULL,\n"
                + "execiseName text NOT NULL,\n"
                + "execiseType text NOT NULL,\n"
                + "points integer NOT NULL\n"
                + ")\n";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement statement = conn.createStatement()) {
            statement.execute(sql);
            System.out.println("Table creat");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createTableLesson() {
        String sql = "CREATE TABLE IF NOT EXISTS lessons (\n"
                + "id integer PRIMARY KEY,\n"
                + "lessonName text NOT NULL,\n"
                + "exerciseName text NOT NULL,\n"
                + "exerciseType text NOT NULL,\n"
                + "maxPoints integer NOT NULL\n"
                + ")\n";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement statement = conn.createStatement()) {
            statement.execute(sql);
            System.out.println("Table creat");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveStudentsLesson(List<StudentAllInfo> students) {
        String sql = "INSERT INTO studentsLessons (fullname,lessonName,execiseName,execiseType,points) VALUES(?,?,?,?,?)";
        for (var student : students) {
            for (var lesson : student.getLessons()) {
                for (var exercise : lesson.getExercises()) {
                    try (Connection conn = DriverManager.getConnection(URL);
                         PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                        preparedStatement.setString(1, student.getName());
                        preparedStatement.setString(2, lesson.getName());
                        preparedStatement.setString(3, exercise.getName());
                        preparedStatement.setString(4, exercise.getType().toString());
                        preparedStatement.setInt(5, exercise.getPointsScored());
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public static void saveStudents(List<StudentAllInfo> students) {
        String sql = "INSERT INTO students (fullname,allPoints,friendsCount) VALUES(?,?,?)";
        for (var student : students) {
            try (Connection conn = DriverManager.getConnection(URL);
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, student.getName());
                preparedStatement.setInt(2, student.getAllPoint());
                if (student.getFriendCount() == null) {
                    preparedStatement.setNull(3, Types.INTEGER);
                } else {
                    preparedStatement.setDouble(3, student.getFriendCount());
                }
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void saveLesson(List<StudentAllInfo> students) {
        String sql = "INSERT INTO lessons (lessonName,exerciseName,exerciseType,maxPoints) VALUES(?,?,?,?)";
        var student = students.get(0);
        for (var lesson : student.getLessons()) {
            for (var exercise : lesson.getExercises()) {
                try (Connection conn = DriverManager.getConnection(URL);
                     PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, lesson.getName());
                    preparedStatement.setString(2, exercise.getName());
                    preparedStatement.setString(3, exercise.getType().toString());
                    preparedStatement.setInt(4, exercise.getMaxScore());
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public HashMap<String, Integer> getStudents() {
        String sql = "SELECT fullname,friendsCount FROM students";
        var res = new HashMap<String, Integer>();
        try (Connection conn = DriverManager.getConnection(URL);
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                res.put(rs.getString("name"), rs.getInt("friendsCount"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}

