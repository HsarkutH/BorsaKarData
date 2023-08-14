package textRead;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class read {
    private static String path = "C:/robotsibirya(4)/robot1/Yapılan İşlemler.txt";
    private static String date = "\\d{2}.\\d{2}.\\d{4}"; //DD.MM.YYYY formatında tarih

    public static String getPath() { return path; }
    public static String getDate() { return date; }

    public static ArrayList<String> readText(String path, String date) {
        ArrayList<String> kar = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            LocalDate today = LocalDate.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String dateToday = today.format(format);

            String tarih = "";

            String row;
            Pattern patternDate = Pattern.compile(date);
            String sell = "SATIŞ";
            Pattern patternSell = Pattern.compile(sell);
            String gainLow = "Kar=\\d{1}.\\d{2}";
            Pattern patternGainLow = Pattern.compile(gainLow);
            String gainHigh = "Kar=\\d{2}.\\d{2}";
            Pattern patternGainHigh = Pattern.compile(gainHigh);

            while ((row = bufferedReader.readLine()) != null) {
                Matcher dateMatcher = patternDate.matcher(row);
                Matcher sellMatcher = patternSell.matcher(row);
                Matcher gainLowMatcher = patternGainLow.matcher(row);
                Matcher gainHighMatcher = patternGainHigh.matcher(row);


                while (dateMatcher.find() && sellMatcher.find()) {
                    String matched = dateMatcher.group();

                    String sellMatched = sellMatcher.group();

                    //System.out.println(sellMatched + " - "+ matched);

                    if (gainLowMatcher.find()) {
                        String gainLowMatched = gainLowMatcher.group();
                        //System.out.println(sellMatched + " - " + gainLowMatched + " - " + matched);
                        String gainLowLast = gainLowMatched.split("=")[1];
                        kar.add(gainLowLast);
                    }
                    if (gainHighMatcher.find()) {
                        String gainHighMatched = gainHighMatcher.group();
                        //System.out.println(sellMatched + " - " + gainHighMatched + " - " + matched);
                        String gainHighLast = gainHighMatched.split("=")[1];
                        kar.add(gainHighLast);
                    }
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.getMessage();
        }
        //System.out.println(kar);
        return kar;
    }
}
