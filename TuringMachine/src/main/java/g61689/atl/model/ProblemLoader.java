package g61689.atl.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProblemLoader {
    public static List<Problem> loadProblems(String filePath) {
        List<Problem> problems = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath), StandardCharsets.UTF_8)) {

            // Skip the header line
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 5) {
                    int num = Integer.parseInt(values[0]);
                    int difficulty = Integer.parseInt(values[1]);
                    int luck = Integer.parseInt(values[2]);
                    int code = Integer.parseInt(values[3]);

                    List<Integer> validatorNos = new ArrayList<>();
                    for (int i = 4; i < values.length; i++) {
                        validatorNos.add(Integer.parseInt(values[i]));
                    }

                    Problem problem = new Problem(num, difficulty, luck, code, validatorNos);
                    problems.add(problem);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return problems;
    }
}