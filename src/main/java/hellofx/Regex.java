package hellofx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static String returnDigit(String str) {
        if (str == null) {
            return null;
        }
            String res = null;
            String paternStr = "(?<digit>\\d*\\.*\\d{1,2}) *(?<text>.*)";

            Pattern pattern = Pattern.compile(paternStr);

            Matcher match = pattern.matcher(str);
            if (match.find()) {
                res = match.group("digit");
                return res;
            }
            return null;


        }

        public static String returnText (String str){
            if (str == null){
                return null;
            }
            String res = null;
            String paternStr = "(?<digit>\\d*\\.*\\d{1,2}) *(?<text>.*)";

            Pattern pattern = Pattern.compile(paternStr);

            Matcher match = pattern.matcher(str);
            if (match.find()) {
                res = match.group("text");
                return res;
            }
            return null;

        }
    }
