import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {

        solution(new int[]{5,0,2,7});

    }

    public static Set<Integer> result = new HashSet<>();
    public static int[] n;
    public static boolean[] visited;
    public static int[] solution(int[] numbers) {
        n = numbers;
        visited = new boolean[numbers.length];

        dfs(0, 0, 0);


        return result.stream().mapToInt(i -> i).sorted().toArray();

    }

    public static void dfs(int cnt, int idx, int now) {

        if(cnt == 2) {
            result.add(now);
            return;
        }

        for(int i = 0; i < n.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(cnt + 1, i, now + n[i]);
                visited[i] = false;
            }
        }
    }
}
