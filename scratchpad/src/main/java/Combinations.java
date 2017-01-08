import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fkruege on 1/7/17.
 */
public class Combinations {

    public static void main(String[] args){
        String[] set = new String[]{"A", "B", "C"};
        List<String> combinations = new ArrayList<String>();

        for(int i = 0; i < set.length; i++){
            for(int j = i+1; j < set.length; j++){
                String combination = set[i] + ", " + set[j];
                combinations.add(combination);
            }
        }

        System.out.println("Running time ~((n^2)/2)");
        System.out.println("O(n^2)");
        System.out.println("Number of combinations: " + combinations.size());
        Arrays.stream(combinations.toArray()).forEach(System.out::println);

    }
}
