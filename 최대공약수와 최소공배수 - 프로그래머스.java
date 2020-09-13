public class Solution {

    public static void main(String[] args) {
        solution(198, 183);
    }

    public static int[] solution(int n, int m) {

        if(n < m) {
            int t = n;
            n = m;
            m = t;
        }

        int[] result = new int[2];
        result[0] = max(n, m);
        result[1] = (n * m) / result[0];

        return result;
    }

    public static int max(int n, int m) {

        while(n != 0 && m != 0) {
            n = n - m;
            m = m % n;
        }

        return n;
    }
}
