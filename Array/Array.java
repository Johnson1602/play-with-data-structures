package Array;

public class Array {

    // 成员变量
    // 这个动态数组 Array 底层使用 Java 内置的静态数组实现
    // 有两个变量来记录动态数组的状态：
    // 1. 当前数组中实际有多少个元素，用变量 size 记录
    // 2. 当前数组的最大容量，可以直接调用 data.length 获得，所以不需要新的变量来记录，省掉维护变量的开销
    private int[] data;
    private int size;

    // 构造函数
    public Array(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    // 假如用户没传参，就默认创建容量为 10 的数组
    public Array() {
        this(10);
    }

    // 查看当前数组中有多少元素
    public int getSize() {
        return size;
    }

    // 查看当前数组的最大容量
    public int getCapacity() {
        return data.length;
    }

    // 查看当前数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }
}
