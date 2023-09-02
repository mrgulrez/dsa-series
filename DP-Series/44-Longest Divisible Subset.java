import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Main {

    public static List<Integer> divisibleSet(List<Integer> arr) {

        int n = arr.size();

        // Sort the array
        Collections.sort(arr);

        List<Integer> dp = new ArrayList<>(Collections.nCopies(n, 1));
        List<Integer> hash = new ArrayList<>(Collections.nCopies(n, 1));

        for (int i = 0; i <= n - 1; i++) {

            hash.set(i, i); // Initializing with the current index
            for (int prev_index = 0; prev_index <= i - 1; prev_index++) {

                if (arr.get(i) % arr.get(prev_index) == 0 && 1 + dp.get(prev_index) > dp.get(i)) {
                    dp.set(i, 1 + dp.get(prev_index));
                    hash.set(i, prev_index);
                }
            }
        }

        int ans = -1;
        int lastIndex = -1;

        for (int i = 0; i <= n - 1; i++) {
            if (dp.get(i) > ans) {
                ans = dp.get(i);
                lastIndex = i;
            }
        }

        List<Integer> temp = new ArrayList<>();
        temp.add(arr.get(lastIndex));

        while (hash.get(lastIndex) != lastIndex) { // Until not reach the initialization value
            lastIndex = hash.get(lastIndex);
            temp.add(arr.get(lastIndex));
        }

        return temp;
    }

    public static void main(String[] args) {

        List<Integer> arr = Arrays.asList(1, 16, 7, 8, 4);

        List<Integer> ans = divisibleSet(arr);

        System.out.print("The longest divisible subset elements are: ");
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
    }
}
// Output:

//  The longest divisible subset elements are: 1 4 8 16

// Time Complexity: O(N*N)

// Reason: There are two nested loops.

// Space Complexity: O(N)

// Reason: We are only using two rows of size n.