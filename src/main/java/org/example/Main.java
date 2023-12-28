package org.example;

import Db.DataBase;
import Db.Mapper.LessonsFromDBMapper;
import Db.Mapper.StudentFromDBMapper;
import Db.Mapper.StudentLessonsFromDBMapper;
import Db.ORM;
import Model.*;
import Parser.Parser;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import visual.drawer.*;
import vkApi.VkRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Добро пожаловать в мой проект");
            System.out.println("Хотите поработать с проектом? ДА/НЕТ");
            if (scanner.nextLine().equals("НЕТ")) {
                break;
            }
            boolean flag = true;
            while (flag) {
                System.out.println("Для работы с проектом напишите интересующию команду");
                System.out.println("Parser - парсить файл");
                System.out.println("VK search friends - ищет количество друзей у человека из распарсеного фаула");
                System.out.println("Write in sqldb - записывет распарсиных людей и уроки в sqldb");
                System.out.println("Write in ormdb - записывет распарсиных людей и уроки в ormdb");
                System.out.println("Create chart - создает графики из ormdb");
                System.out.println("EXIT-выход из проекта");
                switch (scanner.nextLine()) {
                    case "Parser": {
                        System.out.println("Для работы с parser, необходим путь файла");
                        var students = Parser.parseStudents(scanner.nextLine());
                        System.out.println("Файл распарсен");
                        System.out.println("Хотите увидеть результат (ДА/НЕТ)");
                        if (scanner.nextLine().equals("ДА")){
                            students.forEach(x->System.out.print(x.toString()));
                            break;
                        }
                        break;
                    }
                    case "VK search friends": {
                        System.out.println("Для работы с vk, необходим путь файла с интересуюшеми людьми");
                        var students = Parser.parseStudents(scanner.nextLine());
                        System.out.println("Поиск окончен");
                        if (scanner.nextLine().equals("ДА")){
                            students.forEach(x->System.out.println(x.toString()));
                            break;
                        }
                        break;
                    }
                    case "Write in sqldb": {
                        System.out.println("Для работы с db, необходим путь файла который мы добвим в базу");
                        var students = Parser.parseStudents(scanner.nextLine());
                        System.out.println("Хотите найти количество друзей из вк? (ДА/НЕТ)");
                        if (scanner.nextLine().equals("ДА")) {
                            searchInfoInVK(students);
                        }
                        createDBSQL(students);
                        System.out.println("Информация в db");
                        break;
                    }
                    case "Write in ormdb": {
                        System.out.println("Для работы с db, необходим путь файла который мы добвим в базу");
                        var students = Parser.parseStudents(scanner.nextLine());
                        System.out.println("Хотите найти количество друзей из вк? (ДА/НЕТ)");
                        if (scanner.nextLine().equals("ДА")) {
                            searchInfoInVK(students);
                        }
                        createDBORM(students,new ORM());
                        System.out.println("Информация в db");
                        break;
                    }
                    case "Create chart": {
                        System.out.println("Вы можете построить:");
                        System.out.println("Friend - график человек/его количество друзей, нужно выбрать нижнюю границу кол-ва друзей(>=0).");
                        System.out.println("Sem - человек/кол-во баллов за семенарские занятия, нужно выбрать нижную границу балла (от 0 до 32)");
                        System.out.println("Act - человек/кол-во баллов за активность на занятияч, нужно выбрать нижную границу балла (от 0 до 32)");
                        System.out.println("Must Beneficial Lesson - кол-во баллов за каждый урок");
                        System.out.println("Point for lesson - как хорошо сдутенты решали задания, нужно выбрать тип заданий (упр/дз)");
                        System.out.println("Points student - все баллы студента за задания");
                        var ormDb=new ORM();
                        ormDb.connect();
                        drawchart(scanner.nextLine(),ormDb);
                        break;
                    }
                    case "EXIT": {
                        flag = false;
                        break;
                    }
                    default: {
                        System.out.println("Для работы с Parser напишите интересующию команду");
                        System.out.println("getExpenseSum - вычисляет сумму расходов по всем операциям");
                        System.out.println("getIncomeSum - вычисляеь сумму доходов по операциям");
                        System.out.println("getListOfExpenses - выдает расходы по организациям в виде строк");
                        System.out.println("EXIT-выход из нынешней больницы");
                        break;
                    }
                }

            }
        }
    }

    public static void searchInfoInVK(List<StudentAllInfo> students) {
        VkRepository vk = new VkRepository();
        try {
            for (var student : students) {
                var count = vk.getsearchPeple(student.getName());
                student.setFriendCount(count);
                Thread.sleep(600);
            }
        } catch (ClientException | ApiException | InterruptedException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createDBSQL(List<StudentAllInfo> students) {
        DataBase.connect();
        DataBase.createTableStudent();
        DataBase.createTableStudentLesson();
        DataBase.createTableLesson();
        DataBase.saveStudents(students);
        DataBase.saveStudentsLesson(students);
        DataBase.saveLesson(students);
    }

    public static void createDBORM(List<StudentAllInfo> students, ORM orm) throws Exception {
        orm.connect();
        orm.createTable();
        orm.saveStudents(students);
        orm.saveLessons(students);
        orm.saveStudentLesson(students);
        orm.close();
    }

    public static void drawchart(String parametr, ORM dbOrm) {
        Scanner scanner = new Scanner(System.in);
        switch (parametr) {
            case "Friend": {
                if(dbOrm.studentsIsNull()) {
                    System.out.println("В базе эта таблица пуста");
                    break;
                }
                System.out.println("Для поиска графика нужен нижний порог друзей >0");
                var minFriendsCount = scanner.nextInt();
                if (minFriendsCount<0) {
                    System.out.println("Неверний нижний порог друзей");
                    break;
                }
                var students = getStudents(dbOrm);
                new BarCharDrawer("chart", students, minFriendsCount).setVisible(true);
                break;
            }
            case "Sem": {
                if(dbOrm.studentsIsNull()) {
                    System.out.println("В базе эта таблица пуста");
                    break;
                }
                System.out.println("Для графика нужен нижний порог балла (от 0 до 32)");
                var minFriendsCount = scanner.nextInt();
                if (minFriendsCount<0 | minFriendsCount>32) {
                    System.out.println("Неверний нижний порог друзей");
                    break;
                }
                var studentsFromDB = getStudents(dbOrm);
                new BarCharDrawerForStudentsSem("chart", studentsFromDB,minFriendsCount).setVisible(true);
                break;
            }
            case "Act":{
                if(dbOrm.studentsIsNull()) {
                    System.out.println("В базе эта таблица пуста");
                    break;
                }
                System.out.println("Для графика нужен нижний порог балла (от 0 до 32)");
                var minFriendsCount = scanner.nextInt();
                if (minFriendsCount<0 | minFriendsCount>32) {
                    System.out.println("Неверний нижний порог друзей");
                    break;
                }
                var studentsFromDB = getStudents(dbOrm);
                new BarCharDrawerForStudentsAct("chart", studentsFromDB,minFriendsCount).setVisible(true);
                break;
            }
            case "Must Beneficial Lesson":{
                if(dbOrm.lessonsIsNull()) {
                    System.out.println("В базе эта таблица пуста");
                    break;
                }
                var lessonsFromDB=getLessons(dbOrm);
                new PieChartDrawerFoeLessons("chart",lessonsFromDB).setVisible(true);
                break;
            }
            case "Point for lesson":{
                if(dbOrm.studentsLessonsIsNull()) {
                    System.out.println("В базе эта таблица пуста");
                    break;
                }
                System.out.println("Для графика нужен тип заданий (упр/дз)");
                var exerciseType = scanner.nextLine();
                Exercise_Type type;
                if ((!exerciseType.equals("упр"))&(!exerciseType.equals("дз"))) {
                    System.out.println("Неверний тип задания");
                    break;
                }
                else if (exerciseType.equals("упр")) {
                    type=Exercise_Type.practice;
                }
                else {
                    type=Exercise_Type.homework;
                }
                var studentsFromDB=getStudentsLessons(dbOrm);
                new PieChartDraiwer("chart",studentsFromDB,type).setVisible(true);
                break;
            }
            case "Points student":{
                if(dbOrm.studentsLessonsIsNull()) {
                    System.out.println("В базе эта таблица пуста");
                    break;
                }
                System.out.println("Для получение баллов студента,укажаите его имя и фамилию");
                var nameNeedStudent=scanner.nextLine();
                var students=getStudents(dbOrm);
                var needstudents=students.stream().filter(x->x.getName().equals(nameNeedStudent)).collect(Collectors.toList());
                if(needstudents.isEmpty()){
                    System.out.println("Такого студента нет");
                    break;
                }
                System.out.println("Также укажаите за какой вид заданий хотите видеть баллы (упр/дз)");
                var exerciseType = scanner.nextLine();
                Exercise_Type type;
                if ((!exerciseType.equals("упр"))&(!exerciseType.equals("дз"))) {
                    System.out.println("Неверний тип задания");
                    break;
                }
                else if (exerciseType.equals("упр")) {
                    type=Exercise_Type.practice;
                }
                else {
                    type=Exercise_Type.homework;
                }
                var studentsLessonsFromDB=getStudentsLessons(dbOrm);
                new BarCharDrawForStudentsPoints("chart", studentsLessonsFromDB,needstudents.get(0).getName(),type).setVisible(true);
                break;
            }
            default:
                System.out.println("Not find this chart");
                break;
        }
    }
    public static ArrayList<Studentinfo> getStudents(ORM dbOrm){
        var studentsFromDB = new ArrayList<Studentinfo>();
        try {
            studentsFromDB = dbOrm.getStudents()
                    .stream().map(StudentFromDBMapper::map)
                    .collect(Collectors.toCollection(ArrayList::new));
            dbOrm.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return studentsFromDB;
    }
    public static ArrayList<StudentLessons> getStudentsLessons(ORM dbOrm){
        var studentsLessonsFromDB=new ArrayList<StudentLessons>();
        try {
            studentsLessonsFromDB=dbOrm.getStudentLessons()
                    .stream().map(StudentLessonsFromDBMapper::map)
                    .collect(Collectors.toCollection(ArrayList::new));
            dbOrm.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return studentsLessonsFromDB;
    }
    public static ArrayList<Lessons> getLessons(ORM dbOrm){
        var lessonsFromDB=new ArrayList<Lessons>();
        try {
            dbOrm.connect();
            lessonsFromDB=dbOrm.getLessons()
                    .stream().map(LessonsFromDBMapper::map)
                    .collect(Collectors.toCollection(ArrayList::new));
            dbOrm.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lessonsFromDB;
    }
}

