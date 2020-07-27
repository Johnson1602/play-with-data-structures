package map;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    // 内部节点类
    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    // BSTMap 成员变量
    private Node root;
    private int size;

    // BSTMap 构造函数
    public BSTMap() {
        root = null;
        size = 0;
    }

    // 为了方便成员函数的实现，这里先实现一个方法， 递归
    // 给定一个根节点，查找并返回这棵树中指定 key 值的 Node 引用
    // 不存在则返回 null
    private Node getNode(Node root, K key) {
        // 递归终止
        if (root == null) {
            return null;
        }

        if (key.compareTo(root.key) == 0) {
            return root;
        } else if (key.compareTo(root.key) < 0) {
            return getNode(root.left, key);
        } else {   // key.compareTo(root.key) > 0
            return getNode(root.right, key);
        }
    }

    @Override
    public void add(K key, V value) {
        // 如果 key 不存在则添加新节点，如果 key 存在则更新 value
        root = add(root, key, value);
    }

    // 递归，向指定根节点的树中添加新节点
    // 返回添加完成之后的新的树的根
    private Node add(Node root, K key, V value) {
        // 递归终止
        if (root == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(root.key) == 0) {
            root.value = value;
        } else if (key.compareTo(root.key) < 0) {
            root.left = add(root.left, key, value);
        } else {
            root.right = add(root.right, key, value);
        }

        return root;
    }

    // 为了实现删除功能，需要两个辅助方法
    // 1. 找到一棵树中的最小值所在的节点
    // 2. 删除一棵树中的最小值所在的节点
    private Node getMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return getMin(node.left);
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node right = node.right;
            node.right = null;
            size--;
            return right;
        }

        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            // 如果左子树为空
            if (node.left == null) {
                Node right = node.right;
                node.right = null;
                size--;
                return right;
            }
            // 如果右子树为空
            if (node.right == null) {
                Node left = node.left;
                node.left = null;
                size--;
                return left;
            }
            // 左右子树都不为空
            Node successor = getMin(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = null;
            node.right = null;
            return successor;
        }
    }


    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " does not exist!");
        }
        node.value = value;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
