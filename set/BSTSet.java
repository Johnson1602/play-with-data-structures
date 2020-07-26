package set;

import bst.BST;

public class BSTSet<E extends Comparable<E>> implements Set<E> {

    // 私有成员变量
    private BST<E> bst;

    // 构造函数
    public BSTSet() {
        bst = new BST<>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }
}