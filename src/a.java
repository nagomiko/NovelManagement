public class a {
    private static int a=0;


    public static void main(String[] args) {
        System.out.println(a);
        a+=1;
        System.out.println(a);
        s();
        System.out.println(a);
    }

    private static void s(){
        System.out.println(a);
        a+=1;
    }
}
