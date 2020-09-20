public class Solution {

    public static void main(String[] args) {

        longestPalindrome("cbbd");


    }

    public static String result = "";
    public static String longestPalindrome(String s) {

        if(s.length() <= 1) return s;

        String longest = "";
        for (int i = 0; i < s.length(); i++) {
            String tmp = sub(s, i, i);
            // 중앙에서 왼쪽
            if(tmp.length() > longest.length()) longest = tmp;

            // 중앙에서 오른쪽
            tmp = sub(s, i, i + 1);
            if(tmp.length() > longest.length()) longest = tmp;
        }

        return longest;

    }

    public static String sub(String s, int i, int j) {

        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }

        return s.substring(i + 1, j);

    }
}
