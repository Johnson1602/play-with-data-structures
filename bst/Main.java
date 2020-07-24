package bst;

public class Main {

    public static void main(String[] args) {
        int[] nums = {5, 3, 6, 8, 4, 2};
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < nums.length; i++) {
            bst.add(nums[i]);
        }

        bst.preOrder();
        bst.inOrder();
        bst.postOrder();
    }

}
