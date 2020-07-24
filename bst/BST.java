package bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 使 BST 支持范型，且范型要求可比较
public class BST<E extends Comparable<E>> {

    // BST 的内部类，声明实现 BST 节点的 Node 类
    // 用户不需要关心 BST 的节点是如何实现的，所以设置为 private
    private class Node {
        // 成员变量：Node 的值，以及指向两个孩子的节点引用
        public E e;
        public Node left, right;

        // 构造函数
        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    // 声明 BST 的成员变量：根节点 & 节点个数
    private Node root;
    private int size;

    // BST 构造函数
    public BST() {
        root = null;
        size = 0;
    }

    // 返回 BST 中节点个数
    public int getSize() {
        return size;
    }

    // 返回 BST 是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 向 BST 添加元素
    public void add(E e) {
        // 此处用 root 接收返回值，是为了在树原本为空的情况下，接收新的根
        // 若原本树就不为空，在插入新元素后 root 并不会改变
        root = add(root, e);
    }

    // 向以 node 为根的 BST 中插入元素 e，并返回插入之后的新 BST 的根节点
    // 如果树为空，那么就 return 新节点
    // 如果树不为空，那么就 return 它自己
    private Node add(Node node, E e) {

        // 如果找到的节点为空，那么就在这个位置插入新节点
        // 递归在此终止
        if (node == null) {
            size++;
            // 直接 return 匿名对象
            return new Node(e);
        }

        // 否则，递归地向某一棵子树添加元素 e
        // 不考虑新增节点和树中节点相等
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }

        return node;
    }

    // 查找 BST 中是否包含某个元素 e
    public boolean contains(E e) {
        return contains(root, e);
    }

    // 查找以 node 为根的 BST 中是否包含元素 e，递归算法
    private boolean contains(Node node, E e) {

        // 递归终止
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else { // e.compareTo(node.e) > 0
            return contains(node.right, e);
        }
    }

    // BST 的前中后序遍历，本质上都是深度优先遍历
    // 前序遍历 BST，用户调用
    public void preOrder() {
        System.out.println("BST Preorder: ");
        preOrder(root);
        System.out.println();
    }

    // 前序遍历以 node 为根节点的 BST，递归算法
    private void preOrder(Node node) {

        // 终止条件，遍历到 null 就直接 return
        if (node == null) {
            return;
        }

        // 否则先访问当前节点，再依次访问左子树和右子树
        System.out.print(node.e + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    // 前序遍历 BST，非递归算法，使用栈作为辅助
    public void preOrderNR() {
        System.out.println("BST Preorder (NR): ");
        Stack<Node> stack = new Stack<>();
        if (root == null) {
            return;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.print(current.e + " ");

            // 由于栈后进先出，前序遍历是先遍历左子树，所以左子树后入栈
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
        System.out.println();
    }

    // 中序遍历 BST，用户调用
    // 中序遍历 BST 得到的结果是这棵树中所有元素从小到大排序的结果
    // 所以"二分搜索树"也叫"排序树"
    public void inOrder() {
        System.out.println("BST Inorder: ");
        inOrder(root);
        System.out.println();
    }

    // 中序遍历以 node 为根节点的 BST，递归算法
    private void inOrder(Node node) {

        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.print(node.e + " ");
        inOrder(node.right);
    }

    // 后序遍历 BST，用户调用
    public void postOrder() {
        System.out.println("BST Postorder: ");
        postOrder(root);
        System.out.println();
    }

    // 后序遍历以 node 为根节点的 BST，递归算法
    private void postOrder(Node node) {

        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.e + " ");
    }

    // BST 的层序优先遍历，即广度优先遍历，使用队列作为辅助 (非递归)
    public void levelOrder() {
        System.out.println("BST Levelorder: ");
        Queue<Node> queue = new LinkedList<>();
        if (root == null) {
            return;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            Node current = queue.remove();
            System.out.print(current.e + " ");

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        System.out.println();
    }

}
