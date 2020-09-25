import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int cases = Integer.parseInt(br.readLine());


        for (int k = 0; k < cases; k++) {

            int n = Integer.parseInt(br.readLine());


            int[][] dp = new int[2][n + 1];
            int[][] stickers = new int[2][100001];

            for(int i = 0; i < 2; i++) {
                int[] stick = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();

                for(int j = 1; j <= n; j++)
                    stickers[i][j] = stick[j - 1];

            }

            dp[0][1] = stickers[0][1];
            dp[1][1] = stickers[1][1];

            for (int i = 2; i <= n; i++) {

                dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + stickers[0][i];
                dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + stickers[1][i];

            }

            bw.write(Math.max(dp[0][n], dp[1][n]) + "\n");
        }

        bw.flush();
    }
}
