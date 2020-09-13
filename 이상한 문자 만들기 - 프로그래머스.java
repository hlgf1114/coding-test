import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {

        System.out.println(solution("try hello world"));

    }

    static class Convert {
        String s;
        int jump;

        public Convert(String s, int jump) {
            this.s = s;
            this.jump = jump;
        }
    }

    public static String solution(String s) {
        String result = "";
        boolean flag = true;
        for(char c : s.toCharArray()) {
            if(c == 32) {
                result += " ";
                flag = true;
                continue;
            }

            if(flag) {
                result += String.valueOf(c).toUpperCase();
                flag = false;
            }
            else {
                result += String.valueOf(c).toLowerCase();
                flag = true;
            }
        }

        return result;
    }
}

