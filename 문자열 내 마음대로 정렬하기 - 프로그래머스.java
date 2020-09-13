import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        solution(new String[]{"abce", "abcd", "cdx"}, 2);
    }

    public static String[] solution(String[] strings, int n) {

        Arrays.sort(strings, (s1, s2) -> {

            char c1 = s1.charAt(n);
            char c2 = s2.charAt(n);

            if(c1 > c2) return 1;
            else if(c1 < c2) return -1;
            else {
                return s1.compareTo(s2);
            }

        });
        return strings;

    }
}
