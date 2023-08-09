package textRead;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class read {
    private static String path = "C:/robotsibirya(4)/robot1/Yapılan İşlemler.txt";
    private static String date = "\\d{2}.\\d{2}.\\d{4}"; //DD.MM.YYYY formatında tarih

    public static String getPath() { return path; }
    public static String getDate() { return date; }

    public static String readText(String path, String date) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String row;
            Pattern pattern = Pattern.compile(date);

            while ((row = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(row);

                while (matcher.find()) {
                    String matched = matcher.group();
                    System.out.println(matched);
                }
            }
        } catch (IOException e) {

        }

        return "";
    }
}
