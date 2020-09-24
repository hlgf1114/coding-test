import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] first = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int n = first[0];
        int k = first[1];

        int[][] list = new int[n + 1][2];

        for (int i = 0; i < n; i++) {

            int[] now = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            list[i + 1] = now;
        }

        int[][] dp = new int[102][100003];

        for (int i = 1; i <= n; i++) {

            int weight = list[i][0];
            int val = list[i][1];

            for (int j = 0; j <= k; j++) {

                if(j < list[i][0]) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight] + val);

            }

        }

        System.out.println(dp[n][k]);

    }
}
