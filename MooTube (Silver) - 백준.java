import java.io.*;
import java.util.*;

public class Main {
    public static List<int[]>[] connect;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] first = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int N = first[0];
        int Q = first[1];

        connect = new List[N + 1];
        for(int i = 0; i < connect.length; i++)
            connect[i] = new ArrayList<>();

        for(int i = 0; i < N - 1; i++) {
            int[] line = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            connect[line[0]].add(new int[] {line[1], line[2]});
            connect[line[1]].add(new int[] {line[0], line[2]});
        }


        for (int i = 0; i < Q; i++) {

            int[] com = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            int K = com[0];
            int start = com[1];

            int answer = minUSADO(start, K, N);

            bw.write(answer + "\n");

        }

        bw.flush();
    }

    public static int minUSADO(int start, int k, int n) {

        Queue<Integer> que = new ArrayDeque<>();
        que.add(start);

        boolean[] visited = new boolean[n + 1];
        visited[start] = true;


        int ans = 0;
        while (!que.isEmpty()) {

            int now = que.poll();

            for(int[] next : connect[now]) {

                int go = next[0];
                int cost = next[1];

                if(!visited[go] && k <= cost) {
                    visited[go] = true;
                    que.add(go);
                    ans++;
                }

            }

        }

        return ans;

    }
}
