import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] first = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int N = first[0];
        int M = first[1];

        int[][] matrix = new int[N][];
        for(int i = 0; i < N; i++)
            matrix[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

        // dp[i][j] = i, j 번째 방에서 가질 수 있는 최대 사탕 개수
        int[][] dp = new int[N][M];

        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++) {

                // 현재 위치에서 상하좌우로 올 때 가질 수 있는 사탕중 최대를 구한다.
                try {dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + matrix[i][j]);} catch (Exception e) {}
                try {dp[i][j] = Math.max(dp[i][j], dp[i + 1][j] + matrix[i][j]);} catch (Exception e) {}
                try {dp[i][j] = Math.max(dp[i][j], dp[i][j + 1] + matrix[i][j]);} catch (Exception e) {}
                try {dp[i][j] = Math.max(dp[i][j], dp[i][j - 1] + matrix[i][j]);} catch (Exception e) {}


            }


        System.out.println(dp[N - 1][M - 1]);
    }
}
