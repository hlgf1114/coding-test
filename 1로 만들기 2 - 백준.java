import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];
        Arrays.fill(dp, 10000000);

        dp[n] = 0;

        for (int i = n; i > 0; i--) {

            int min = dp[i] + 1;

            if(i % 3 == 0) dp[i / 3] = Math.min(min, dp[i / 3]);
            if(i % 2 == 0) dp[i / 2] = Math.min(min, dp[i / 2]);
            dp[i - 1] = Math.min(dp[i - 1], min);

        }

        Stack<Integer> stack = new Stack<>();

        int min = dp[1];

        for (int i = 0; i <= n; i++) {
            if(min == dp[i]) {
                stack.push(i);

                if(i * 3 <= n && dp[i * 3] == min - 1) i = i * 3 - 1;
                else if(i * 2 <= n && dp[i * 2] == min - 1) i = i * 2 - 1;


                min--;
            }

        }

        bw.write(stack.size() - 1 + "\n");
        while (!stack.isEmpty()) bw.write(stack.pop() + " ");

        bw.flush();

    }

}
