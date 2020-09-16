import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(solution(2, 5)));
    }

    public static long[] solution(int x, int n) {

        long[] result = new long[n];

        result[0] = x;
        for(int i = 1; i < n; i++)
            result[i] = result[i - 1] + x;

        return result;

    }
}
