package g61689.atl.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for loading problem data from a resource file.
 */
public class ProblemLoader {
    /**
     * Loads problems from a specified resource path.
     *
     * @param resourcePath The path to the resource file containing problem data.
     * @return A list of problems loaded from the file.
     */
    public static List<Problem> loadProblems(String resourcePath) {
        List<Problem> problems = new ArrayList<>();

        // Use getResourceAsStream to read from the classpath
        try (InputStream is = ProblemLoader.class.getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

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
        } catch (NullPointerException e) {
            System.err.println("Resource not found: " + resourcePath);
        }

        return problems;
    }
}
