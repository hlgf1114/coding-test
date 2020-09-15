import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] first = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] list = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int S = first[1];

        int s = 0;
        int e = 0;
        long sum = 0;
        int leng = Integer.MAX_VALUE;
        while(true) {

            if(e == list.length && s >= e) break;
            else if(sum < S && e != list.length) sum += list[e++];
            else sum -= list[s++];

            if(sum >= S) leng = Math.min(leng, e - s);

        }

        if(leng == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(leng);
    }
}
