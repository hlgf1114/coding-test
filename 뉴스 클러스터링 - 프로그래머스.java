import java.util.*;

public class Solution {

    public static void main(String[] args) {

        System.out.println(solution(
                "FRANCE", "french"
        ));

    }

    public static Map<String, Integer> one = new HashMap<>();
    public static Map<String, Integer> two = new HashMap<>();
    public static Map<String, Integer> all = new HashMap<>();
    public static Map<String, Integer> inner = new HashMap<>();
    public static int solution(String str1, String str2) {



        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        if(str1.equals(str2)) return 65536;

        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();

        for(int i = 0; i < c1.length - 1; i++) {
            char a = c1[i];
            char b = c1[i + 1];

            if(a >= 'a' && a <= 'z' && b >= 'a' && b <= 'z') {
                String now = "" + a + b;
                if(one.getOrDefault(now, null) == null) one.put(now, 1);
                else one.replace(now, one.get(now) + 1);
            }
        }

        for(int i = 0; i < c2.length - 1; i++) {
            char a = c2[i];
            char b = c2[i + 1];

            if(a >= 'a' && a <= 'z' && b >= 'a' && b <= 'z') {
                String now = "" + a + b;
                if(two.getOrDefault(now, null) == null) two.put(now, 1);
                else two.replace(now, two.get(now) + 1);
            }
        }

        System.out.println(one.toString());
        System.out.println(two.toString());


        // 합집합
        for(String key : one.keySet()) {
            if(all.getOrDefault(key, null) == null) all.put(key, one.get(key));
        }

        for(String key : two.keySet()) {
            if(all.getOrDefault(key, null) == null) all.put(key, two.get(key));
            else all.replace(key, Math.max(one.get(key), two.get(key)));
        }

        for(String key : one.keySet()) {
            boolean exist = two.getOrDefault(key, null) == null ? false : true;

            if(exist && inner.getOrDefault(key, null) == null)
                inner.put(key, Math.min(one.get(key), two.get(key)));
        }

        System.out.println(all.toString());
        System.out.println(inner.toString());

        int a = 0;
        int b = 0;

        for(String key : inner.keySet()) a += inner.get(key);
        for(String key : all.keySet()) b += all.get(key);


        int result = (int)(((float)a / b) * 65536);

        return result;
    }
}
