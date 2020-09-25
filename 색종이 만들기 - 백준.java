import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static int white, blue;
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] matrix = new int[n][];
        for(int i = 0; i < n; i++)
            matrix[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

        divideAndCount(matrix);

        System.out.println(white);
        System.out.println(blue);

    }

    public static void divideAndCount(int[][] matrix) {

        // 먼저 현재 범위에서 하얀색과 파란색이 있는 지 확인
        boolean sameAll = true;
        boolean existWhite = false;
        boolean existBlue = false;
        for(int i = 0; i < matrix.length; i++)
            for(int j = 0; j < matrix.length; j++) {
                if(matrix[i][j] == 0) existWhite = true;
                else existBlue = true;

                if(matrix[i][j] == 0 && existBlue) sameAll = false;
                if(matrix[i][j] == 1 && existWhite) sameAll = false;
            }

        if(sameAll) {
            if(existWhite) white++;
            if(existBlue) blue++;
            return;
        }

        // 아니라면 4조각으로 나눈다.


        int mid = matrix.length / 2;

        // 왼쪽 상단
        divideAndCount(divide(matrix, new int[] {0, 0}, new int[] {mid, mid}));
        // 오른쪽 하단
        divideAndCount(divide(matrix, new int[] {mid, mid}, new int[] {matrix.length, matrix.length}));
        // 왼쪽 하단
        divideAndCount(divide(matrix, new int[] {mid, 0}, new int[] {matrix.length , mid}));
        // 오른쪽 상단.
        divideAndCount(divide(matrix, new int[] {0, mid}, new int[] {mid, matrix.length}));


    }

    public static int[][] divide(int[][] matrix, int[] start, int[] end) {

        //System.out.println(Arrays.toString(start) + " " + Arrays.toString(end));

        int[][] result = new int[end[0] - start[0]][end[1] - start[1]];

        for(int i = 0; i < result.length; i++)
            for(int j = 0; j < result.length; j++)
                result[i][j] = matrix[start[0] + i][start[1] + j];

        return result;

    }

}
