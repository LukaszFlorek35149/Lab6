package pl.lublin.wsei.java.cwiczenia;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Noblista {

    public String FirstName;
    public String LastName;
    public String Year;
    public String Category;
    public String Motivation;
    public String Country;
    public String DataRow;

    public Noblista(String text) {

        Pattern pattern = Pattern.compile("(?:([^,]*)(?:[^,-]|))");
        DataRow = text;
        Matcher matcher = pattern.matcher(text);
        List<String> data = new ArrayList<String>();
        while (matcher.find()) {
            for (int j = 1; j <= matcher.groupCount(); j++) {
                if (matcher.group(0) != "") data.add(matcher.group(0));
            }
        }

        FirstName = data.get(1);
        LastName = data.get(2);
        Country = data.get(5);
        Year = data.get(12);
        Category = data.get(13);
        Motivation = data.get(16);
    }
}
