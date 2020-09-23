import java.io.*;
import java.util.*;

public class Main {


    public static int[][] cave;
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
    public static int[] broke = new int[2];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] first = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int R = first[0];
        int C = first[1];

        cave = new int[R][C];

        for (int i = 0; i < R; i++) {

            int[] line = cave[i];

            String[] l = br.readLine().split("");

            for (int j = 0; j < l.length; j++)
                if(l[j].equals("x")) line[j] = 1;
        }

        int t = Integer.parseInt(br.readLine());
        int[] ts = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        for(int i = 0; i < t; i++) {

            // 왼쪽인지 오른쪽인지 확인
            boolean isLeft = i % 2 == 0 ? true : false;

            // 일단 던져서 블록을 부순다.
            if(!breakBlock(ts[i], isLeft)) continue;

            for(int k = 0; k < 4; k++) {
                try {
                    int nx = broke[0] + dx[k];
                    int ny = broke[1] + dy[k];

                    if(cave[nx][ny] == 1) {
                        findClusterAndGoDown(nx, ny);
                    }

                } catch (Exception e) {}
            }


        }

        for(int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {

                if (cave[i][j] == 0) bw.write(".");
                else bw.write("x");

            }
            bw.write("\n");
        }

        bw.flush();
    }

    public static boolean findClusterAndGoDown(int i, int j) {

        if(cave[i][j] == 0) return false;

        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{i, j});

        boolean[][] visited = new boolean[cave.length][cave[0].length];
        visited[i][j] = true;


        List<int[]> cluster = new ArrayList<>();
        if(cave[i][j] == 1) cluster.add(que.peek());

        while (!que.isEmpty()) {

            int[] now = que.poll();

            for(int k = 0; k < 4; k++) {

                int nx = now[0] + dx[k];
                int ny = now[1] + dy[k];

                try {

                    if(cave[nx][ny] == 1 && !visited[nx][ny]) {

                        visited[nx][ny] = true;
                        int[] next = new int[]{nx, ny};
                        que.add(next);
                        cluster.add(next);

                    }


                } catch (Exception e) {}

            }

        }

        // 클러스터를 얻었으므로 아래로 내려가는 것이 가능한 지 본다.
        int[][] temp = cave;

        // 일단 클러스터의 모든 부분을 지운다.
        for(int[] n : cluster) temp[n[0]][n[1]] = 0;

        int go;
        for (go = 1; go < cave.length; go++) {
            for(int[] n : cluster) {
                if(n[0] + go == cave.length || temp[n[0] + go][n[1]] == 1) {
                    go--;

                    for(int[] k : cluster) temp[k[0] + go][k[1]] = 1;
                    cave = temp;
                    return true;
                }
            }
        }

        return true;

    }

    public static boolean breakBlock(int height, boolean isLeft) {

        height = cave.length - height;

        int[] line = cave[height];

        if(isLeft) {

            for(int i = 0; i < line.length; i++) {
                if(line[i] == 1) {
                    line[i] = 0;
                    broke[0] = height;
                    broke[1] = i;
                    return true;
                }
            }

        }
        else {

            for(int i = line.length - 1; i >= 0; i--) {
                if(line[i] == 1) {
                    line[i] = 0;
                    broke[0] = height;
                    broke[1] = i;
                    return true;
                }
            }

        }

        return false;

    }

}
