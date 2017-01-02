
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * Created by fkruege on 1/1/2017.
 */
public class Permutation {

    public static void main(String[] args) {

        int itemsToPrint = Integer.parseInt(args[0]);


        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            queue.enqueue(s);
        }

        Iterator<String> iterator = queue.iterator();
        int i = 0;
        while (iterator.hasNext() && i < itemsToPrint) {
            StdOut.println(iterator.next());
            i++;
        }


    }
}
