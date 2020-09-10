import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) {
        System.out.println(solution(
                0,
                new String[]{"Jeju", "Pangyo", "NewYork", "newyork"}
        ));
    }

    public static class Cache {
        public String data;
        public int missed = 0;

        public Cache(String data, int missed) {
            this.data = data;
            this.missed = missed;
        }
        @Override
        public boolean equals(Object o) {

            Cache c = (Cache)o;

            if(this.data.equals(c.data)) return true;
            else return false;
        }
    }

    public static int solution(int cacheSize, String[] cities) {

        if(cacheSize == 0) return cities.length * 5;

        cities = Arrays.stream(cities).map(s -> {
            String n = s.toLowerCase();
            return n;
        }).toArray(String[]::new);

        List<Cache> cache = new LinkedList<>();

        int result = 0;
        for(int i = 0; i< cities.length; i++) {

            boolean isContained = false;
            for(Cache now : cache)
                if(now.equals(new Cache(cities[i], 0))) isContained = true;

            if(cache.size() < cacheSize && !isContained) {

                for(Cache c : cache)
                    if(c.data.equals(cities[i])) c.missed = 0;
                    else c.missed += 1;
                cache.add(new Cache(cities[i], 0));
                result += 5;
            }
            else if(isContained) {

                for(Cache c : cache)
                    if(c.data.equals(cities[i])) c.missed = 0;
                    else c.missed += 1;
                result += 1;
            }
            else if(!isContained && cache.size() == cacheSize) {

                int remove = 0;
                int m = 0;
                for(int j = 0; j < cache.size(); j++)
                    if(cache.get(j).missed > m) {
                        m = cache.get(j).missed;
                        remove = j;
                    }

                cache.remove(remove);
                cache.add(new Cache(cities[i], 0));
                result += 5;
            }
        }

        return result;
    }
}
