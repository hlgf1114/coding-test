import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws ParseException {

        System.out.println(solution(new String[]
                {
                        "2016-09-15 20:59:57.421 0.351s",
                        "2016-09-15 20:59:58.233 1.181s",
                        "2016-09-15 20:59:58.299 0.8s",
                        "2016-09-15 20:59:58.688 1.041s",
                        "2016-09-15 20:59:59.591 1.412s",
                        "2016-09-15 21:00:00.464 1.466s",
                        "2016-09-15 21:00:00.741 1.581s",
                        "2016-09-15 21:00:00.748 2.31s",
                        "2016-09-15 21:00:00.966 0.381s",
                        "2016-09-15 21:00:02.066 2.62s"
                }));


        /*
        System.out.println(solution(new String[]
                {
                        "2016-09-15 00:00:00.000 2.3s", "2016-09-15 23:59:59.999 0.1s"
                }));

         */

    }

    public static class Process {
        int start;
        int end;

        public Process(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static List<Process> list = new LinkedList<>();
    public static int solution(String[] lines) throws ParseException {


        getTimes(lines);

        list.sort((o1, o2) -> {
            if(o1.end == o2.end) {
                if(o1.start < o2.start) return -1;
                else return 1;
            }
            else if(o1.end > o2.end) return 1;
            else return -1;
        });

        for(Process p : list) System.out.println(p.start + " " + p.end);

        int answer = 0;

        for(int i = 0; i < list.size(); i++) {
            Process cur = list.get(i);

            int range = cur.end + 999;

            int cnt = 0;
            for(int j = i; j < list.size(); j++) {
                Process next = list.get(j);
                if(range >= next.start) cnt++;
            }

            answer = Math.max(answer, cnt);
        }

        return answer;

    }

    public static void getTimes(String[] lines) {

        // 일단 시간은 3초 안에 모든 일이 일어난다.
        // 예를 들어 날짜가 바뀌는 수준에서 달라진다면???
        // 그럴 필요 없음. 어차피 날짜는 고정됨

        for(String a : lines) {

            String[] big = a.split(" ");
            String[] time = big[1].split(":");

            String go = big[2].substring(0, big[2].length() - 1);

            // 밀리 세컨드 단위로 자른다.
            int hour = Integer.parseInt(time[0]) * 60 * 60 * 1000;
            int min = Integer.parseInt(time[1]) * 60 * 1000;
            int sec = (int)(Double.parseDouble(time[2]) * 1000);

            int end = hour + min + sec;

            int start = (end - (int)(Double.parseDouble(go) * 1000)) + 1;

            list.add(new Process(start, end));

        }
    }
}
