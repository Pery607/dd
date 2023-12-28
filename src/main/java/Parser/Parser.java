package Parser;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import Model.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Parser {
    public static List<StudentAllInfo> parseStudents(String filePath) {
        List<StudentAllInfo> students = new ArrayList<>();
        Path path = Paths.get(filePath);
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        Charset cs = StandardCharsets.UTF_8;
        try (var br = Files.newBufferedReader(path, cs);
             var reader = new CSVReaderBuilder(br).withCSVParser(parser).build();) {
            List<String[]> rows = reader.readAll();
            var listTheme = Arrays.stream(rows.get(0))
                    .filter(x -> !Objects.equals(x, ""))
                    .skip(3)
                    .collect(Collectors.toList());
            var exesice = rows.get(1);
            var max = rows.get(2);

            for (var row : rows.stream().skip(3).collect(Collectors.toList())) {
                int countName = 0;
                String name = row[0];
                String group = row[1];
                int allActScores = Integer.parseInt(row[2]);
                int allPractScores = Integer.parseInt(row[3]);
                int allHwScores = Integer.parseInt(row[4]);
                int allSemScores = Integer.parseInt(row[5]);
                ArrayList<Lesson> lessons = new ArrayList<>();
                ArrayList<Exercise> exercises = new ArrayList<>();
                var act = exesice[2];
                var pract = exesice[3] + ":";
                var sem = exesice[5];
                var hW = exesice[4] + ":";
                for (var i = 8; i < row.length; i++) {
                    if (exesice[i].equals(act)) {
                        exercises.add(new Exercise(Exercise_Type.activity, exesice[i], Integer.parseInt(max[i]), Integer.parseInt(row[i])));
                    } else if (exesice[i].contains(pract)) {
                        exercises.add(new Exercise(Exercise_Type.practice, exesice[i], Integer.parseInt(max[i]), Integer.parseInt(row[i])));
                    } else if (exesice[i].contains(hW)) {
                        exercises.add(new Exercise(Exercise_Type.homework, exesice[i], Integer.parseInt(max[i]), Integer.parseInt(row[i])));
                    } else if (exesice[i].equals(sem)) {
                        exercises.add(new Exercise(Exercise_Type.seminar, exesice[i], Integer.parseInt(max[i]), Integer.parseInt(row[i])));
                        lessons.add(new Lesson(listTheme.get(countName), exercises));
                        exercises = new ArrayList<>();
                        countName++;
                    }
                }
                students.add(new StudentAllInfo(name, group, lessons, allActScores, allHwScores, allSemScores, allPractScores));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return students;
    }
}
