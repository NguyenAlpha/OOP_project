package test;

import java.util.ArrayList;

public class test4 {
    private static int count = 3;
    private static ArrayList<test3> testList = new ArrayList<>();

    public test4() {
        test3 t1 = new test3(1,"nha");
        test3 t2 = new test3(2,"nhasdas");
        test3 t3 = new test3(3,"nhagggg");
        testList.add(t1);
        testList.add(t2);
        testList.add(t3);
    }

    public int getCount() {
        return count;
    }

    public test3 KKK(int n) {
        return testList.get(n);
    }
}