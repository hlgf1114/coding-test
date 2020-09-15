import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {

        System.out.println(lengthOfLongestSubstring("abca"));

    }

    public static int lengthOfLongestSubstring(String s) {
        if(s.equals("")) return 0;

        int[] pos = new int[3000];

        Arrays.fill(pos, -1);

        int lo = 0;
        int hi = 0;

        int leng = 0;
        String result = "";
        while(true) {

            System.out.println(result + " " + lo + " " + hi + " " + leng);

            if(hi == s.length()) break;

            // 처음 hi 늘어난다.
            if(pos[s.charAt(hi)] == -1) {
                pos[s.charAt(hi)] = hi;
                hi++;
            }
            // 만약 전 단계를 이미 거쳤다면
            else {

                if(pos[s.charAt(hi)] >= lo)
                    lo = pos[s.charAt(hi)] + 1;
                pos[s.charAt(hi)] = hi;
                //if(hi - 1 == lo) lo++;
                hi++;
            }

            result = s.substring(lo, hi);

            leng = Math.max(leng, hi - lo);

        }

        return leng;

    }
}
