import java.util.Arrays;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5});
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public static TreeNode constructMaximumBinaryTree(int[] nums) {

        if(nums.length == 0) return null;

        return setNode(nums, 0, nums.length);

    }

    public static TreeNode setNode(int[] nums, int start, int end) {

        if(start == end) return null;

        int maxIdx = start;
        for(int i = start; i < end; i++) {
            if(nums[i] > nums[maxIdx])
                maxIdx = i;
        }

        TreeNode node = new TreeNode(nums[maxIdx]);
        node.left = setNode(nums, start, maxIdx);
        node.right = setNode(nums, maxIdx + 1, end);

        return node;
    }

}
