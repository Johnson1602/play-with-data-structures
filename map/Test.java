package map;

public class Test {

    private int value;
    private Test next;

    public Test(int value, Test next) {
        this.value = value;
        this.next = next;
    }

    public Test(int value) {
        this(value, null);
    }

    public static void main(String[] args) {
        Test test = new Test(5);
        Test test2 = new Test(7, test);
        System.out.println(test2);
        System.out.println(test2.value);
        System.out.println(test2.next);

        test2 = null;
        System.out.println("===");
        System.out.println(test2);
//        System.out.println(test2.value);
    }

}
