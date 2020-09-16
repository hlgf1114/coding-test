import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {

        System.out.println(solution(new int[] {4,3,2,1}));

    }

    public static int[] solution(int[] arr) {

        int min = Arrays.stream(arr).min().getAsInt();


        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < arr.length; i++) {
            if (arr[i] != min) result.add(arr[i]);
        }

        int[] re = result.stream().mapToInt(i -> i).toArray();


        if(re.length == 0) return new int[]{-1};



        return re;

    }

}
