package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class check {
    Pattern pattern;
    Matcher matcher;
    int check_Stringvorod(String s){
        s.trim();
        if (s.equals("1")){
            return 1;
        }
        else if(s.equals("0")){
            return 0;
        }
        else {
            return -1;
        }
    }
    boolean return_check(String s){
        if (s.equals("return")){
            return true;
        }
        else {
            return false;
        }
    }
    boolean pass_check(String s){
        pattern = Pattern.compile("([0-9]+?)", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(s);
        if (matcher.find()){
            pattern = Pattern.compile("([a-z]+?)", Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(s);
            if (matcher.find()) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}
