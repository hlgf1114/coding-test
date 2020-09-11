import java.util.*;

public class Solution {

    public static void main(String[] args) {

        System.out.println(solution(new int[][]
                {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}}
        ));

    }

    public static class Node {
        int x;
        int y;
        int data;
        Node left;
        Node right;

        public Node(int x, int y, int data) {
            this.x = x;
            this.y = y;
            this.data = data;
        }
    }

    public static List<Integer> pre = new LinkedList<>();
    public static List<Integer> post = new LinkedList<>();
    public static int[][] solution(int[][] nodeinfo) {

        List<Node> nodes = new LinkedList<>();
        for(int i = 1; i < nodeinfo.length + 1; i++)
            nodes.add(new Node(nodeinfo[i - 1][0], nodeinfo[i - 1][1], i));


        nodes.sort((n1, n2) -> {
            if(n1.y == n2.y) {
                if(n1.x > n2.x) return -1;
                else return 1;
            }
            else if(n1.y < n2.y) return 1;
            else return -1;
        });

        for(Node n : nodes) {
            System.out.print(n.data + " ");
        }

        List<Integer> height = new LinkedList<>();
        for(int[] a : nodeinfo)
            if(!height.contains(a[1])) height.add(a[1]);

        height.sort((o1, o2) -> {
            return -Integer.compare(o1, o2);
        });

        Node root = nodes.get(0);

        for(Node n : nodes) {
            insertNode(root, n);
        }


        preOrder(root);
        postOrder(root);

        System.out.println(pre.toString());
        System.out.println(post.toString());

        int[][] result = new int[2][nodeinfo.length];

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < nodeinfo.length; j++) {

                if(i == 0)
                    result[i][j] = pre.get(j);
                else result[i][j] = post.get(j);
            }
        }

        return result;

    }

    public static void insertNode(Node node, Node insert) {

        if(node.x == insert.x) return;

        if(node.x > insert.x) {
            if(node.left == null) node.left = insert;
            else insertNode(node.left, insert);
        }

        if(node.x < insert.x) {
            if(node.right == null) node.right = insert;
            else insertNode(node.right, insert);
        }
    }

    public static void preOrder(Node node) {

        pre.add(node.data);

        if(node.left != null)
            preOrder(node.left);
        if(node.right != null)
            preOrder(node.right);

    }

    public static void postOrder(Node node) {

        if(node.left != null) {
            postOrder(node.left);
        }
        if(node.right != null) {
            postOrder(node.right);
        }
        post.add(node.data);

    }

}
