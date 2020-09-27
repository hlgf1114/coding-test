import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] line = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        // 0번째의 증가하는 부분수열 길이
        int[] dp = new int[n];

        // 맨 처음은 무조건 1
        for(int i = 0; i < n; i++) dp[i] = 1;

        for(int i = 1; i < n; i++) {

            for(int j = 0; j < i; j++) {
                if(line[j] < line[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }

        }

        System.out.println(Arrays.stream(dp).max().getAsInt());


    }
}
