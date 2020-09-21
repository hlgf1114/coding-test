import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {

        convert("AB", 1);
    }

    public static String convert(String s, int numRows) {

        if(numRows == 1) return s;


        char[][] zig = new char[numRows][s.length()];

        int[] ptrs = new int[numRows];
        int zag = 0;
        int ptr = 0;
        boolean flag = true;
        while(ptr < s.length()) {


            zig[zag][ptrs[zag]++] = s.charAt(ptr);

            ptr++;
            if(zag == numRows - 1) flag = false;
            if(zag == 0) flag = true;

            if(flag) zag++;
            else zag--;

        }

        StringBuilder sb = new StringBuilder();

        for (char[] line : zig) {

            for(char c : line)
                if(c != 0) sb.append(c);

        }

        return sb.toString();

    }
}
