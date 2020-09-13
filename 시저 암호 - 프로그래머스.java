public class Solution {

    public static void main(String[] args) {
        System.out.println(solution("zZ", 1));
    }

    public static String solution(String s, int n) {

        StringBuilder result = new StringBuilder();
        for(char c : s.toCharArray()) {
            if(c >= 'a' && c <= 'z') {
                int push = (c - 'a' + n) % 26;
                result.append((char) ('a' + push));
            }
            else if(c >= 'A' && c <= 'Z') {
                int push = (c - 'A' + n) % 26;
                result.append((char) ('A' + push));
            }
            else if(c == " ".charAt(0)) result.append(c);
        }
        return result.toString();
    }
}
