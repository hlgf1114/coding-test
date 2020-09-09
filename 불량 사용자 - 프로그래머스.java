import java.util.*;

public class Solution {

    public static void main(String[] args) {

        /*
        System.out.println(solution(
                new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[]{"fr*d*", "abc1**"}
        ));

         */

        System.out.println(solution(
                new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[]{"fr*d*", "*rodo", "******", "******"}
        ));
    }

    public static String[] user;
    public static String[] banned;
    public static Set<String> set = new HashSet<>();
    public static boolean[] visited;
    public static int solution(String[] user_id, String[] banned_id) {

        user = user_id;
        banned = banned_id;
        visited = new boolean[user.length];

        dfs(0, new int[banned.length]);

        System.out.println(set.toString());

        return set.size();

    }

    public static void dfs(int cnt, int[] result) {

        if(cnt == banned.length) {
            int[] r = result.clone();
            Arrays.sort(r);
            String now = "";
            for(int i : r) now += i;
            set.add(now);
            return;
        }

        for(int i = 0; i < user.length; i++) {
            String now = user[i];

            boolean ok = check(banned[cnt], now);

            if(ok && !visited[i]) {
                visited[i] = true;
                result[cnt] = i;
                dfs(cnt + 1, result);
                result[cnt] = 0;
                visited[i] = false;
            }
        }


    }

    public static boolean check(String ban, String id) {
        // 같을 경우
        int ptr = 0;

        char[] c = id.toCharArray();
        char[] p = ban.toCharArray();

        boolean ok = true;
        if(c.length == p.length) {
            while (ptr < c.length) {
                if (p[ptr] == '*') {
                    ptr++;
                    continue;
                }
                if (c[ptr] != p[ptr]) {
                    ok = false;
                    break;
                }

                ptr++;
            }
        }
        else ok = false;

        return ok;
    }
}
