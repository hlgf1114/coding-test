
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        System.out.println(solution(1));
    }

    public static Set<Integer> save = new HashSet<>();
    public static int solution(int n) {

        if(n <= 1) return n;

        dfs(n);

        System.out.println(save.toString());

        return save.stream().reduce((i1, i2) -> i1 + i2).get() + 1;

    }

    public static void dfs(int now) {

        if(now <= 1) return;
        save.add(now);

        for(int i = 2; i < now / 2 + 1; i++) {
            if(now % i == 0) dfs(now / i);

        }

    }
}
