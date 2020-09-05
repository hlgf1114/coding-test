public class Solution {

    public static void main(String[] args) {

        System.out.println(solution("hit", "cog",
                new String[] {"hot", "dot", "dog", "lot", "log"}));
    }

    public static int answer = Integer.MAX_VALUE;
    public static boolean[] visited;
    public static String t;
    public static String[] w;
    public static int solution(String begin, String target, String[] words) {

        t = target;
        w = words;

        visited = new boolean[words.length];
        dfs(0, begin);

        if(answer == Integer.MAX_VALUE) answer = 0;

        return answer;
    }

    public static void dfs(int cnt, String now) {

        if(now.equals(t)) {
            answer = Math.min(answer, cnt);
            return;
        }

        for(int i = 0; i < w.length; i++) {
           if(visited[i]) continue;

           visited[i] = true;
           if(hasConnection(now, w[i])) {
               dfs(cnt + 1, w[i]);
           }
           visited[i] = false;
        }

    }

    public static boolean hasConnection(String start, String target) {

        int same = 0;
        for(int i = 0; i < start.length(); i++) {
            if(start.charAt(i) == target.charAt(i)) same++;
        }

        if(same == start.length() - 1) return true;
        else return false;
    }
}
