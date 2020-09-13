public class Solution {

    public static void main(String[] args) {

        System.out.println(solution("1234"));
    }

    public static boolean solution(String s) {

        if(!(s.length() == 4 || s.length() == 6)) return false;

        boolean ok = true;
        for(char c : s.toCharArray()) {
            if(!(c >= '0' && c <= '9')) ok = false;
        }


        return ok;

    }
}
