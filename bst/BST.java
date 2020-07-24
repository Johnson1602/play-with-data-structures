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

    // 寻找并返回 BST 中的最小元素
    public E min() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty!");
        }
        return min(root).e;
    }

    // 返回以 node 为根的 BST 中的最小值所在的节点
    private Node min(Node node) {

        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    // 寻找并返回 BST 中的最大元素
    public E max() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty!");
        }
        return max(root).e;
    }

    // 返回以 node 为根的 BST 中的最大值所在的节点
    private Node max(Node node) {

        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    // 从 BST 中删除最小值所在的节点，并返回最小值
    public E removeMin() {
        E min = min();
        root = removeMin(root);
        return min;
    }

    // 注意这里的逻辑不能这样写
    // 虽然第一眼看上去好像是对的，但是如果此时树中只剩一个节点
    // 那么删除的过程会正常进行，但是删除完之后树为空（size == 0）
    // 那么在 return 的过程中调用 min() 会检查树是否为空，此时已经为空就回抛出异常
//    public E removeMin() {
//        root = removeMin(root);
//        return min();
//    }

    // 返回以 node 为根节点的 BST 在删除完最小值所在节点后，新的根节点
    private Node removeMin(Node node) {
        // 由于在 public 中调用 min() 的过程中已经检查过了是否为空，此处就不用再检查一遍了
//        if (size == 0) {
//            throw new IllegalArgumentException("BST is Empty");
//        }

        // 删除以 node 为根的树的最小节点
        // 如果这个 node 就是这棵树的最小节点
        // 那么删除完之后返回的新树的根节点，就是这个 node 的右子树的根节点
        if (node.left == null) {
            Node right = node.right;
            node.right = null;
            size--;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    // 从 BST 中删除最大值所在的节点，并返回最大值
    public E removeMax() {
        E max = max();
        root = removeMax(root);
        return max;
    }

    // 返回以 node 为根节点的 BST 在删除完最大值所在节点后，新的根节点
    private Node removeMax(Node node) {
        if (size == 0) {
            throw new IllegalArgumentException("BST is Empty");
        }
        if (node.right == null) {
            Node left = node.left;
            node.left = null;
            size--;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    // Hibbard Deletion
    // 从 BST 中删除给定节点（如果不存在则什么都不做）
    public void remove(E e) {
        root = remove(root, e);
    }

    // 从以 node 为根的树中删除节点 e，返回删除完成后的新树的根节点
    private Node remove(Node node, E e) {

        // 递归终止：node 为空
        if (node == null) {
            return null;
        }

        // 如果待删除节点小于当前节点，则从当前节点则左子树中删除指定节点
        if (e.compareTo(node.e) < 0) {
            node.left =  remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) { // 若大于则从右子树中删除
            node.right =  remove(node.right, e);
            return node;
        } else { // e.compareTo(node.e) == 0, 删除当前节点
            // 如果该节点只有一棵子树，就很容易处理
            if (node.left == null) {
                Node right = node.right;
                node.right = null;
                size--;
                return right;
            }
            if (node.right == null) {
                Node left = node.left;
                node.left = null;
                size--;
                return left;
            }

            // 如果该节点左右子树都不为空
            // 那么先找到该节点的后继节点（右子树中的最小节点）
            // 用后继节点替换掉这个节点
            Node successor = min(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = null;
            node.right = null;
            return successor;
        }
    }


}
