public class Solution {

    public static void main(String[] args) {

        System.out.println(solution(626331));

    }

    public static int solution(int num) {

        int count = 0;

        long n = num;
        while(n != 1) {
            if(n % 2 == 0) n /= 2;
            else n = n * 3 + 1;
            count++;

            if(count > 500) return -1;
        }

        return count;

    }
}
