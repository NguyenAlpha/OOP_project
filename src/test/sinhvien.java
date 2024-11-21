package test;

public class sinhvien {
    // attributes
    private int id = 9999;
    private String name;
    private int age;
    private String khoa;

    public sinhvien() {}

    public sinhvien(int id, String name1, int age, String khoa) {
        this.id = id;
        this.name = name1;
        this.age = age;
        this.khoa = khoa;
    }

    public void setid(int newid) {
        id = id;   
    }

    public int getid() {
        return id;
    }

    public static void main(String[] args) {
        sinhvien sv1 = new sinhvien();
        System.out.println(sv1.id);
        System.out.println(sv1.name);
        System.out.println(sv1.age);
        System.out.println(sv1.khoa);

        
        sv1.id = 100;
        sv1.name = "HT";
        sv1.khoa = "CNTT";
        sv1.age = 19;
        System.out.println(sv1.id);
        System.out.println(sv1.name);
        System.out.println(sv1.age);
        System.out.println(sv1.khoa);
    }
}
