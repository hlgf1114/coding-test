import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] first = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int[][] world = new int[first[0]][first[1]];

        int[] height = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < world[0].length; i++) {
            int h = height[i];
            for(int j = world.length - 1; j >= world.length - h; j--) {
                world[j][i] = 1;
            }

        }

        int answer = 0;
        for (int i = 0; i < world.length; i++) {

            int[] now = world[i];

            int s = -1;
            int e = 0;
            int count = 0;

            while(s <= e && e != now.length) {
                if(now[e] == 1) {
                    if(s == -1) s = e;
                    count += e - s == 0 ? 0 : e - s - 1;
                    s = e;
                }
                e++;
            }

            answer += count;

        }

        System.out.println(answer);


    }
}
