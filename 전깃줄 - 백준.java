import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] connected = new int[n + 1][2];
        for (int i = 0; i < n; i++) {
            int[] line = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            connected[i][0] = line[0];
            connected[i][1] = line[1];
        }

        Arrays.sort(connected, (o1, o2) -> {
            return Integer.compare(o1[0], o2[0]);
        });

        // 해당 위치까지 설치할 수 있는 선의 개수
        int[] dp = new int[n + 1];

        // 가장 많이 설치 가능한 개수
        int max = 1;
        for(int i = 1; i <= n; i++) {
            dp[i] = 1;
            for(int j = 1; j < i; j++) {

                if(connected[j][1] < connected[i][1])
                    dp[i] = Math.max(dp[i], dp[j] + 1);

            }

            max = Math.max(max, dp[i]);
        }

        System.out.println(n - max);

    }
}
