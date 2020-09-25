import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static int zero, one, minus;
    public static int[][] matrix;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

         matrix = new int[n][];

        for(int i = 0; i < n; i++)
            matrix[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

        divideAndConquer(new int[] {0, 0}, new int[] {n, n});

        System.out.println(minus);
        System.out.println(zero);
        System.out.println(one);

    }

    public static void divideAndConquer(int[] start, int[] end) {

        // 지금 있는 영역에서 모두 같은 숫자인지 확인
        boolean oneExist = false;
        boolean zeroExist = false;
        boolean minusExist = false;
        boolean sameAll = true;

        for (int i = start[0]; i < end[0]; i++)
            for (int j = start[1]; j < end[1]; j++) {


                if(matrix[i][j] == 0) zeroExist = true;
                if(matrix[i][j] == 1) oneExist = true;
                if(matrix[i][j] == -1) minusExist = true;

                if(oneExist && (minusExist || zeroExist)) sameAll = false;
                if(zeroExist && (oneExist || minusExist)) sameAll = false;
                if(minusExist && (zeroExist || oneExist)) sameAll = false;

            }


        if(sameAll) {

            if(oneExist) one++;
            if(zeroExist) zero++;
            if(minusExist) minus++;
            return;
        }

        int leng = (end[0] - start[0]) / 3;

        // 맨 위쪽
        for (int i = 0; i < 3; i++)
            divideAndConquer(
                    new int[]{start[0] + i * leng, start[1]},
                    new int[]{start[0] + i * leng + leng, start[1] + leng}
            );

        // 중간
        for (int i = 0; i < 3; i++)
            divideAndConquer(
                    new int[]{start[0] + i * leng, start[1] + leng},
                    new int[]{start[0] + i * leng + leng, start[1] + leng * 2}
            );

        // 맨 아래
        for (int i = 0; i < 3; i++)
            divideAndConquer(
                    new int[]{start[0] + i * leng, start[1] + leng * 2},
                    new int[]{start[0] + i * leng + leng, start[1] + leng * 3}
            );



    }
}
