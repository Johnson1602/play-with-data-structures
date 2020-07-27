package map;

public class LinkedListMap<K, V> implements Map<K, V> {

    // 使用链表作为底层数据结构实现映射，创建内部类 Node
    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value) {
            this(key, value, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + ": " + value.toString();
        }
    }

    // LinkedListMap 的成员变量
    private Node dummyHead;
    private int size;

    // LinkedListMap 构造函数
    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    // 为了方便下面成员方法的实现，这里多实现一个方法，用来查找给定 key 的 Node
    // 返回 Node 的引用
    private Node getNode(K key) {
        Node current = dummyHead.next;
        while (current != null) {
            if (current.key.equals(key)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public void add(K key, V value) {
        // 如果已经存在这个 key 了，那么就更新 value 值
        // 否则就新建一个 Node，连接在头节点
        Node node = getNode(key);
        if (node != null) {
            node.value = value;
        } else {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        }
    }

    @Override
    public V remove(K key) {
        // 如果不存在，就 return null，否则删掉
        Node previous = dummyHead;
        while (previous.next != null) {
            if (previous.next.key.equals(key)) {
                break;
            }
            previous = previous.next;
        }

        if (previous.next != null) {
            Node delNode = previous.next;
            previous.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }

        return null;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            throw new IllegalArgumentException(key + " does not exist!");
        }
        node.value = value;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
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
