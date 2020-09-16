import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {

        System.out.println(solution(new int[] {}, 9));

    }

    public static int solution(int[] d, int budget) {

        Arrays.sort(d);

        int ptr = 0;
        while(ptr < d.length) {

            if(d[ptr] <= budget) {
                budget -= d[ptr];
                ptr++;
            }
            else break;

        }

        return ptr;
    }
}
