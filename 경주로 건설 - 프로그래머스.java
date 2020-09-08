import java.util.*;

public class Solution {

    public static void main(String[] args) {

        /*
        System.out.println(solution(new int[][]
                {
                        {0,0,0,0,0,0,0,1},
                        {0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,1,0,0},
                        {0,0,0,0,1,0,0,0},
                        {0,0,0,1,0,0,0,1},
                        {0,0,1,0,0,0,1,0},
                        {0,1,0,0,0,1,0,0},
                        {1,0,0,0,0,0,0,0}
                }));


         */

        /*
        System.out.println(solution(new int[][]
                {
                        {0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}
                }));

         */

        System.out.println(solution(new int[][]
                {{0, 0, 1, 0}, {0, 0, 0, 0}, {0, 1, 0, 1}, {1, 0, 0, 0}}));
    }

    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
    public static int[][] matrix;
    public static int[][] visited;

    public static int answer = Integer.MAX_VALUE;

    public static int solution(int[][] board) {
        matrix = board;
        visited = new int[board.length][board[0].length];

        bfs();

        for (int[] now : visited) {
            System.out.println(Arrays.toString(now));
        }


        return visited[visited.length - 1][visited[0].length - 1];
    }

    public static class Pos {
        public int x, y;
        public int direction, cost;

        public Pos(int x, int y, int direction, int cost) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.cost = cost;
        }
    }

    public static void bfs() {

        Queue<Pos> que = new ArrayDeque<>();

        for(int i = 0; i < 4; i++)
            que.add(new Pos(0, 0, i, 0));

        while(!que.isEmpty()) {

            Pos now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                boolean isCorner = (now.direction == i) ? false : true;
                try {

                    if(isCorner) {
                        int newCost = now.cost + 600;
                        if(matrix[nx][ny] == 0 && (visited[nx][ny] > newCost || visited[nx][ny] == 0)) {
                            visited[nx][ny] = newCost;
                            que.add(new Pos(nx, ny, i, newCost));
                        }
                    }
                    else {
                        int newCost = now.cost + 100;
                        if(matrix[nx][ny] == 0 && (visited[nx][ny] > newCost || visited[nx][ny] == 0)) {
                            visited[nx][ny] = newCost;
                            que.add(new Pos(nx, ny, i, newCost));
                        }
                    }

                } catch (Exception e) {}
            }

        }
    }
}
