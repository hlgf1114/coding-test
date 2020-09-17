import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {

        System.out.println(solution(new int[] {70, 50, 80, 50}, 100));

    }

    public static int solution(int[] people, int limit) {

        Arrays.sort(people);

        int count = 0;

        int left = 0;
        int right = people.length - 1;

        while(left < right) {

            if(people[left] + people[right] > limit) right--;
            else {
                left++;
                right--;
            }

            count++;

            if(left == right) count++;

        }

        return count;
    }
}
