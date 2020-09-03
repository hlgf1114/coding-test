import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {

    public static void main(String[] args) {

        System.out.println(solution(new int[] {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}, "left"));
    }

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};
    public static int[][] matrix;
    public static int[] left = {3, 0};
    public static int[] right = {3, 2};
    public static String solution(int[] numbers, String hand) {

        StringBuilder stringBuilder = new StringBuilder();

        // 나눠보자
        // 엄지 손가락은 상하좌우로만 이동 가능

        // 매트릭스
        matrix = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {-1, 0, -2}};

        for(int go : numbers) {


            if(go == 2 || go == 5 || go == 8 || go == 0) {

                int[] leftBackup = left.clone();
                int[] rightBackup = right.clone();

                // 누구 손가락이 더 가까운지 판별
                int leftDistance = moves(go, true);
                int rightDistance = moves(go, false);

                if(leftDistance == rightDistance) {
                    if(hand.equals("left")) {
                        stringBuilder.append("L");
                        right = rightBackup;
                    }
                    else {
                        stringBuilder.append("R");
                        left = leftBackup;
                    }
                }
                else if(leftDistance < rightDistance) {
                    stringBuilder.append("L");
                    right = rightBackup;
                }
                else {
                    stringBuilder.append("R");
                    left = leftBackup;
                }
            }
            else if(go == 1 || go == 4 || go == 7) {

                int leftDistance = moves(go, true);
                stringBuilder.append("L");

            } else {

                moves(go, false);
                stringBuilder.append("R");
            }

        }

        return stringBuilder.toString();

    }

    public static int moves(int goal, boolean isLeft) {

        int[] start = null;
        if(isLeft) start = left.clone();
        else start = right.clone();

        Queue<int[]> que = new ArrayDeque<>();
        int[][] visited = new int[matrix.length][matrix[0].length];
        que.add(start);
        visited[start[0]][start[1]] = 1;

        while(!que.isEmpty()) {

            int[] now = que.poll();

            for(int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                try {
                    if(visited[nx][ny] == 0) {
                        visited[nx][ny] = visited[now[0]][now[1]] + 1;

                        if(matrix[nx][ny] == goal) {
                            if(isLeft) left = new int[] {nx, ny};
                            else right = new int[] {nx, ny};

                            return visited[nx][ny] - 1;
                        }
                        que.add(new int[] {nx, ny});
                    }

                } catch (Exception e) {}
            }

        }

        return 0;

    }
}