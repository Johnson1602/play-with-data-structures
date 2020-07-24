package bst;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        traversal();
        removeMinOrMax();
        removeSelected();

    }

    // 测试遍历算法
    public static void traversal() {
        int[] nums = {5, 3, 6, 8, 4, 2};
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < nums.length; i++) {
            bst.add(nums[i]);
        }
        bst.preOrder();
        bst.preOrderNR();
        bst.inOrder();
        bst.postOrder();
        bst.levelOrder();
    }

    // 测试删除最大值/最小值
    public static void removeMinOrMax() {
        BST<Integer> bst = new BST<>();
        Random random = new Random();
        int n = 1000;
//        int[] n = {5, 3, 6, 8, 4, 2};
        ArrayList<Integer> arrayList = new ArrayList<>();
        // 向 BST 中添加 1000 个随机数
        for (int i = 0; i < n; i++) {
            bst.add(random.nextInt(10000));
        }

        // 测试 removeMax()
        // 从 BST 中每次取出最大值，放入 ArrayList 中，结果应该是从从大到小排序
        // 由于向 BST 添加过程中可能出现重复的数，所以 BST 最后大小可能小于 1000
        while (!bst.isEmpty()) {
//            System.out.println("Tree Size: " + bst.getSize());
//            bst.inOrder();
            arrayList.add(bst.removeMax());
//            System.out.println("List Size: " + arrayList.size() + ", List: " + arrayList);
//            System.out.println("Tree Size: " + bst.getSize());
//            bst.inOrder();
        }
        System.out.println(arrayList);
        for (int i = 1; i < arrayList.size(); i++) {
            if (arrayList.get(i - 1) < arrayList.get(i)) {
                throw new IllegalArgumentException("Error!");
            }
        }
        System.out.println("removeMax test completed!");

        // 测试 removeMin
//        while (!bst.isEmpty()) {
//            arrayList.add(bst.removeMin());
//        }
//        System.out.println(arrayList);
//        for (int i = 1; i < arrayList.size(); i++) {
//            if (arrayList.get(i - 1) > arrayList.get(i)) {
//                throw new IllegalArgumentException("Error!");
//            }
//        }
//        System.out.println("removeMin test completed!");
    }

    // 测试删除任意元素
    public static void removeSelected() {
        int[] nums = {5, 3, 6, 8, 4, 2};
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < nums.length; i++) {
            bst.add(nums[i]);
        }
        bst.preOrder();
        bst.remove(3);
        bst.preOrder();
        bst.inOrder();
    }

}
