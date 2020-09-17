import java.io.*;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[][] dist = new int[n + 1][n + 1];

        int m = Integer.parseInt(br.readLine());

        for(int i = 0; i < m; i++) {

            int[] now = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            if(dist[now[0]][now[1]] == 0)
                dist[now[0]][now[1]] = now[2];
            else dist[now[0]][now[1]] = Math.min(dist[now[0]][now[1]], now[2]);

        }

        int[][] d = new int[n + 1][n + 1];

        for(int i = 0; i < n + 1; i++)
            for(int j = 0; j < n + 1; j++)
                d[i][j] = dist[i][j];

        // 거쳐가는 노드
        for(int k = 1; k < n + 1; k++) {

            // 출발 노드
            for(int i = 1; i < n + 1; i++) {

                if(d[i][k] == 0) continue;

                // 도착 노드
                for(int j = 1; j < n + 1; j++) {

                    if(d[k][j] == 0 || i == j) continue;


                    if(d[i][j] == 0 || d[i][k] + d[k][j] < d[i][j])
                        d[i][j] = d[i][k] + d[k][j];
                }
            }
        }

        for(int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n; j++) {
                bw.write(d[i][j] + " ");
            }
            bw.write(d[i][n] + "\n");
        }

        bw.flush();

    }


}
