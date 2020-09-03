import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {

        System.out.println(solution("100-200*300-500+20"));
    }

    public static List<char[]> prior = new ArrayList<>();
    public static char[] ex = {'+', '-', '*'};
    public static boolean[] visited = new boolean[3];
    public static long solution(String expression) {

        // 일단 우선순위 정하기
        setPrior(0, new char[3]);

        long answer = 0;
        for(char[] priority : prior) {
            long now = Math.abs(Long.parseLong(DaQ(priority, 0, expression)));
            answer = Math.max(answer, now);

        }

        return answer;


    }

    public static long calc(long a, long b, char oper) {

        if(oper == '-') return a - b;
        else if(oper == '+') return a + b;
        else return a * b;
    }

    public static String DaQ(char[] priority, int n, String expression) {


        if(n == 2) {

            String split = priority[n] + "";
            if(priority[n] == '+')
                split = "\\+";
            if(priority[n] == '*')
                split = "\\*";

            String[] nums = expression.split(split);

            long calc = Long.MIN_VALUE;
            for(int i = 0; i < nums.length; i++)
                if(calc == Long.MIN_VALUE) calc = Long.parseLong(nums[i]);
                else calc = calc(calc, Long.parseLong(nums[i]), priority[n]);

            return String.valueOf(calc);
        }

        String result = "";

        if(priority[n] == '*') {
            String[] split = expression.split("\\*");

            String[] z = new String[split.length];

            for(int i = 0; i < split.length; i++)
                z[i] = DaQ(priority, n + 1, split[i]);

            long calc = Long.MIN_VALUE;
            for(int i = 0; i < z.length; i++)
                if(calc == Long.MIN_VALUE) calc = Long.parseLong(z[i]);
                else calc = calc(calc, Long.parseLong(z[i]), priority[n]);

            result = String.valueOf(calc);

        }
        if(priority[n] == '-') {
            String[] split = expression.split("-");

            String[] z = new String[split.length];

            for(int i = 0; i < split.length; i++)
                z[i] = DaQ(priority, n + 1, split[i]);

            long calc = Long.MIN_VALUE;
            for(int i = 0; i < z.length; i++)
                if(calc == Long.MIN_VALUE) calc = Long.parseLong(z[i]);
                else calc = calc(calc, Long.parseLong(z[i]), priority[n]);

            result = String.valueOf(calc);
        }
        if(priority[n] == '+') {
            String[] split = expression.split("\\+");

            String[] z = new String[split.length];

            for(int i = 0; i < split.length; i++)
                z[i] = DaQ(priority, n + 1, split[i]);

            long calc = Long.MIN_VALUE;
            for(int i = 0; i < z.length; i++)
                if(calc == Long.MIN_VALUE) calc = Long.parseLong(z[i]);
                else calc = calc(calc, Long.parseLong(z[i]), priority[n]);

            result = String.valueOf(calc);
        }

        return result;

    }

    public static void setPrior(int cnt, char[] a) {
        if(cnt == 3) {
            prior.add(a.clone());
            return;
        }

        for(int i = 0; i < ex.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                a[cnt] = ex[i];
                setPrior(cnt + 1, a);
                visited[i] = false;
            }
        }


    }

}
