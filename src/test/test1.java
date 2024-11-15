package test;

public class test1 {
    public static void main(String[] args) {
        String s = "Ao so mi nam , 1  |   Quan jean , 2 |      Ao ba lo HHH  , 1   |       Giay trang , 2";
        String[] ss = s.split("[ ]*[|][ ]*");
        System.out.println(ss[0]);
        System.out.println(ss[1]);
        System.out.println(ss[2]);
        System.out.println("=======================");
        
        for(String temp : ss) {
            String[] temp2 = temp.split("[ ]*[,][ ]*");
            String name = temp2[0];
            int sl = Integer.parseInt(temp2[1]);
            System.out.println(name);
            System.out.println(sl);
            System.out.println("=======================");
        }
        
    }
}
