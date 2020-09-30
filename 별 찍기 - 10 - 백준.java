import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {


    public static char[][] matrix = new char[3000][3000];
    public static void main(String[] args) throws IOException {

        for(char[] c : matrix) Arrays.fill(c, ' ');

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        draw(n, 0, 0);

        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                bw.write(matrix[i][j]);
            bw.write("\n");
        }

        bw.flush();

    }

    public static void draw(int n, int x, int y) {

        if(n == 1) {
            matrix[x][y] = '*';
            return;
        }

        int div = n / 3;

        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) continue;
                draw(div, x + (div * i), y + (div * j));
            }


    }
}
