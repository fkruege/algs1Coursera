import java.util.HashMap;

/**
 * Created by fkruege on 1/26/2017.
 */
public class SimpleTest {


    public static void main(String[] args){

        int[] a = new int[]{0, 1, 2, 3, 4, 5, 6, 7 ,8, 9, 10};
        print(a);
        print2(a);

    }


    public static void print(int[] n) {
        for (int i = 0; i < n.length; i++) {
            System.out.println(n[i]);
        }
    }

    public static void print2(int[] n) {
        for (int i = 0; i < n.length; i++) {
            for(int j = i+1; j < n.length; j++){
                System.out.println(n[i] + ", " + n[j]);
            }
        }
    }
    private class Author{
        int Id;
        String Name;
    }


}
