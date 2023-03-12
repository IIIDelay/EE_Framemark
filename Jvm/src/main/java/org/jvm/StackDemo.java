package org.jvm;

public class StackDemo {
    public static void main(String[] args) {
        StackDemo sd = new StackDemo();
        String A = "A";
        sd.A(A);
    }

    public void A(String A) {
        int a = 10;
        System.out.println(" method A start");
        System.out.println(a);
        B();
        System.out.println("method A end");
    }

    public void B() {
        int b = 20;
        System.out.println(" method B start");
        C();
        System.out.println("method B end");
    }

    private void C() {
        int c = 30;
        System.out.println(" method C start");
        System.out.println("method C end");
    }
}
