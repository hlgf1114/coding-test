import java.util.*;

public class Solution {

    public static void main(String[] args) {

        System.out.println(solution(
                9,
                new int[][] {{0,1},{0,3},{0,7},{8,1},{3,6},{1,2},{4,7},{7,5}},
                new int[][] {{8,5},{6,7},{4,1}}
        ));



        System.out.println(solution(
                9,
                new int[][] {{0,1},{0,3},{0,7},{8,1},{3,6},{1,2},{4,7},{7,5}},
                new int[][] {{4,1},{8,7},{6,5}}
        ));
    }


    public static List<Integer>[] graph;
    public static int[] prev;
    public static int[] next_visit;
    public static int[] visited;
    public static int[] saved;
    public static boolean solution(int n, int[][] path, int[][] order) {

        graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for(int[] now : path) {
            graph[now[0]].add(now[1]);
            graph[now[1]].add(now[0]);
        }

        prev = new int[n];
        next_visit = new int[n];
        visited = new int[n];
        saved = new int[n];

        for(int[] now : order) {

            if(now[1] == 0) return false;

            prev[now[1]] = now[0];
            next_visit[now[0]] = now[1];
        }

        Queue<Integer> que = new ArrayDeque<>();

        for(int next : graph[0]) que.add(next);
        visited[0] = 1;

        while (!que.isEmpty()) {

            System.out.println(que.toString());
            int now = que.poll();

            if(visited[now] == 1) continue;

            if(visited[prev[now]] != 1) {
                saved[prev[now]] = now;
                continue;
            }

            visited[now] = 1;

            for (int next : graph[now]) {
                if(visited[next] == 0)
                    que.add(next);
            }

            que.add(saved[now]);

        }

        System.out.println(Arrays.toString(visited));
        int count = 0;
        for(int i = 0; i < visited.length; i++)
            if(visited[i] == 1) count++;

        return count == n ? true : false;

    }


}
