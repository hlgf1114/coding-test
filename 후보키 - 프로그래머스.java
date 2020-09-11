import java.util.*;

public class Solution {

    public static void main(String[] args) {

        /*
        System.out.println(solution(new String[][]
                {
                        {"100","ryan","music","2"},
                        {"200","apeach","math","2"},
                        {"300","tube","computer","3"},
                        {"400","con","computer","4"},
                        {"500","muzi","music","3"},
                        {"600","apeach","music","2"}
                }));

         */

        System.out.println(solution(new String[][]
                {
                        {"a", "aa"},
                        {"aa", "a"},
                        {"a", "a"}
                }));

    }

    public static class Min {
        int[] contain;

        public Min(int[] contain) {
            this.contain = contain;
        }

        @Override
        public boolean equals(Object obj) {

            Min compare = (Min)obj;

            if(compare.contain.length != this.contain.length) return false;

            int[] t = this.contain;
            int[] c = compare.contain;

            for(int i = 0; i < t.length; i++) {
                if(t[i] != c[i]) return false;
            }
            return true;

        }

        // 항상 비교 받는 것이 더 크게 되어 있다.
        public boolean contains(Min min) {

            int[] c = min.contain;
            int[] t = this.contain;

            Map<Integer, Boolean> con = new HashMap<>();
            for(int n : c)
                if(con.getOrDefault(n, null) == null)
                    con.put(n, false);

            // 이제 비교를 해 본다.
            for(int i = 0; i < c.length; i++) {
                int find = c[i];
                boolean found = false;
                for(int compare : t)
                    if(compare == find) found = true;

                if(found) con.replace(find, true);
            }

            // 만약 다 가지고 있다면
            boolean allContain = true;
            for(int key : con.keySet())
                if(!con.get(key)) allContain = false;

            return allContain;
        }


    }

    public static boolean[] minOk;
    public static boolean[] visited;
    public static Set<Min> min = new HashSet<>();
    public static List<String> mixed = new LinkedList<>();
    public static List<String> temp = new LinkedList<>();
    public static int solution(String[][] relation) {

        minOk = new boolean[relation[0].length];
        visited = new boolean[relation[0].length];

        for(int i = 1; i <= relation[0].length; i++)
            dfs(0, 0, i);

        for(String now : mixed) {

            int[] col = Arrays.stream(now.split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            boolean[] colMin = new boolean[col.length];
            for(int j = 0; j < colMin.length; j++) colMin[j] = true;
            //이제 각 칼럼에 대한 최소성을 확인 한다.
            for(int k = 0; k < col.length; k++) {

                int ptr = col[k];
                Set<String> check = new HashSet<>();
                for(int i = 0; i < relation.length; i++) {
                    if(!check.contains(relation[i][ptr]))
                        check.add(relation[i][ptr]);
                    else colMin[k] = false;
                }
            }

            boolean minExist = false;
            for(boolean n : colMin)
                if(n) minExist = true;

            // 추가로 만약에 칼럼이 3개라면,
            // 조합으로 안에 있는 6가지의 수를 계산해 최소성을 만족하는 지 판단해야한다.
            temp.clear();
            for(int i = 1; i < col.length; i++) {
                dfsSeg(0, 0, i, col);
            }

            // 6가지의 경우의 수로 나눠 이미 증명된 최소성을 찾는다.
            for (String n : temp) {
                int[] ni = Arrays.stream(n.split(""))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                Min compare = new Min(ni);

                boolean contains = false;
                for(Min m : min) {
                    if(m.contain.length <= compare.contain.length) {
                        if(compare.contains(m)) contains = true;
                    }
                }

                // 결국 최소성을 만족 못 했으므로
                if(contains) minExist = true;
            }


            // 만약 칼럼이 1개만이고 최소성을 만족한다면 진행.
            // 만약 칼럼이 2개 이상이고 최소성을 포함하지 않아야 한다.
            // 자기 자체가 최소성이 되어야 한다.
            if(col.length != 1 && minExist) continue;
            if(col.length == 1 && !minExist) continue;

            // 다 만족한다면 비교 해본다.

            System.out.println(Arrays.toString(col));

            Map<String, Integer> rows = new HashMap<>();

            for(int i = 0; i < relation.length; i++) {

                String row = "";
                for(int j = 0; j < col.length; j++) {
                    row += relation[i][col[j]] + " ";
                }

                // 중복이 존재 하는지 확인.
                if(rows.getOrDefault(row, null) == null)
                    rows.put(row, 1);
                else rows.replace(row, rows.get(row) + 1);

            }

            // 만약 값이 2 이상일 경우, 그 조합 자체가 유일성을 만족하지 못한다.
            boolean ok = true;
            for(String key : rows.keySet()) {
                if(rows.get(key) >= 2) ok = false;
            }

            System.out.println(rows.toString());

            // 만약 유일성도 만족하고 최소성도 만족 한다면.
            if(ok) {
                min.add(new Min(col));
            }

        }

        for(Min m : min) System.out.println(Arrays.toString(m.contain) + "ddd");
        return min.size();
    }

    public static void dfsSeg(int cnt, int idx, int leng, int[] original) {
        if(cnt == leng) {
            String result = "";
            for (int i = 0; i < visited.length; i++) {
                if(visited[i]) result += i;
            }
            temp.add(result);
            return;
        }

        for (int i = idx; i < visited.length; i++) {

            boolean contains = false;
            for(int com : original)
                if(i == com) contains = true;

            if (visited[i] == false && contains) {
                visited[i] = true;
                dfsSeg(cnt + 1, i, leng, original);
                visited[i] = false;
            }
        }
    }

    public static void dfs(int cnt, int idx, int leng) {
        if(cnt == leng) {
            String result = "";
            for (int i = 0; i < visited.length; i++) {
                if(visited[i]) result += i;
            }
            mixed.add(result);
            return;
        }

        for (int i = idx; i < visited.length; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                dfs(cnt + 1, i, leng);
                visited[i] = false;
            }
        }
    }
}
