import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Set<Integer>[] list = new Set[n + 1];
        for (int i = 0; i < list.length; i++) list[i] = new HashSet<>();

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {

            int[] line = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            list[line[0]].add(line[1]);
            list[line[1]].add(line[0]);

        }

        Queue<Integer> que = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        que.add(1);
        visited[1] = true;

        int answer = 0;
        while (!que.isEmpty()) {

            int now = que.poll();

            for (int next : list[now]) {
                if(visited[next]) continue;

                visited[next] = true;
                que.add(next);
                answer++;
            }

        }

        System.out.println(answer);

    }
}
