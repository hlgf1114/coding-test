import java.util.*;

public class Solution {

    public static void main(String[] args) {

        System.out.println(solution(new String[][]
                {{"ICN", "A"}, {"A", "C"}, {"A", "D"}, {"D", "B"}, {"B", "A"}}));
    }

    public static Map<String, Integer> mapToInt = new HashMap<>();
    public static Map<Integer, String> mapToStr = new HashMap<>();
    public static int[][] canGo;
    public static List<String> answer = new ArrayList<>();
    public static int fullPath;
    public static String[] solution(String[][] tickets) {

        int count = 0;
        for(String[] now : tickets)
            for (String n : now) {

                if (mapToInt.getOrDefault(n, null) == null) {
                    mapToInt.put(n, count);
                    mapToStr.put(count, n);
                    count++;
                }

            }


        fullPath = tickets.length;
        canGo = new int[count][count];
        for (String[] now : tickets) {
            int start = mapToInt.get(now[0]);
            int end = mapToInt.get(now[1]);

            canGo[start][end] += 1;
        }

        List<String> start = new LinkedList<>();
        start.add("ICN");
        dfs("ICN", start);

        System.out.println(answer.toString());
        return answer.toArray(String[]::new);

    }

    public static void dfs(String now, List<String> result) {

        System.out.println(now);

        int nowInt = mapToInt.get(now);

        if(result.size() == fullPath + 1) {

            if(answer.size() == 0)
                for(String a : result) answer.add(a);
            return;
        }

        // 갈 수 있는 곳 판별
        List<String> go = new LinkedList<>();
        for(int i = 0; i < canGo.length; i++) {
            if(canGo[nowInt][i] >= 1) go.add(mapToStr.get(i));
        }

        // 갈 곳이 없다면
        if(go.size() == 0) return;
        else if(go.size() == 1) {
            canGo[nowInt][mapToInt.get(go.get(0))] -= 1;
            result.add(go.get(0));
            dfs(go.get(0), result);
            result.remove(result.size() - 1);
            canGo[nowInt][mapToInt.get(go.get(0))] += 1;
        }
        else { // 여러 개라면

            // 가장 글자 배열 중 위쪽인 걸로 간다.
            go.sort(String::compareTo);

            for(String next : go) {

                canGo[nowInt][mapToInt.get(next)] -= 1;
                result.add(next);
                dfs(next, result);
                result.remove(result.size() - 1);
                canGo[nowInt][mapToInt.get(next)] += 1;
            }

        }

    }
}
