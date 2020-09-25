import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] matrix = new int[n][];

        for(int i = 0; i < n; i++)
            matrix[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();


        System.out.println(compression(matrix));
    }

    public static String compression(int[][] matrix) {

        // 같은 것만 있는지 확인.
        boolean sameAll = true;
        boolean zeroExist = false;
        boolean oneExist = false;
        for(int i = 0; i < matrix.length; i++)
            for(int j = 0; j < matrix.length; j++) {

                if(matrix[i][j] == 1) oneExist = true;
                if(matrix[i][j] == 0) zeroExist = true;

                if(zeroExist && matrix[i][j] == 1) sameAll = false;
                if(oneExist && matrix[i][j] == 0) sameAll = false;

            }

        if(sameAll) {
            if(zeroExist) return "0";
            if(oneExist) return "1";
        }

        int mid = matrix.length / 2;

        // 왼쪽 상단
        String leftUp = compression(divide(matrix, new int[] {0, 0}, new int[] {mid, mid}));
        // 오른쪽 하단
        String rightDown = compression(divide(matrix, new int[] {mid, mid}, new int[] {matrix.length, matrix.length}));
        // 왼쪽 하단
        String leftDown = compression(divide(matrix, new int[] {mid, 0}, new int[] {matrix.length , mid}));
        // 오른쪽 상단.
        String rightUp = compression(divide(matrix, new int[] {0, mid}, new int[] {mid, matrix.length}));

        return "(" + leftUp + rightUp + leftDown + rightDown + ")";
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
