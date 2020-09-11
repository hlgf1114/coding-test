import java.util.*;

public class Solution {

    public static void main(String[] args) {

        System.out.println(solution(new int[] {3,1,2}, 5));
        //System.out.println(solution(new int[] {1,2,3,4}, 9));
    }

    public static class Food {
        int idx;
        int amount;

        public Food(int idx, int amount) {
            this.idx = idx;
            this.amount = amount;
        }
    }

    public static int solution(int[] food_times, long k) {

        List<Food> list = new LinkedList<>();
        int n = food_times.length;

        for(int i = 0; i < n; i++)
            list.add(new Food(i + 1, food_times[i]));


        list.sort((f1, f2) -> {
            return Integer.compare(f1.amount, f2.amount);
        });

        int before = 0;
        int idx = 0;

        for (Food f : list) {

            long diff = f.amount - before;
            if(diff != 0) {
                long spent = diff * n;

                if(spent <= k) {
                    k -= spent;
                    before = f.amount;
                }
                else {

                    k %= n;

                    List<Food> result = list.subList(idx, food_times.length);
                    result.sort(((o1, o2) -> {
                        return Integer.compare(o1.idx, o2.idx);
                    }));

                    return result.get((int)k).idx;
                }
            }
            System.out.println(k);
            idx++;
            n--;
        }

        return -1;

    }
}
