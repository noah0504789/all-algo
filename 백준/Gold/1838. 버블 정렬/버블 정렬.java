import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        String[] input = br.readLine().split(" ");
        int[] arr = new int[N];
        Map<Integer, Integer> notSortArr = new HashMap<>();
        
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
            notSortArr.put(arr[i], i);
        }
        
        Arrays.sort(arr);
        Map<Integer, Integer> sortArr = new HashMap<>();
        
        for (int j = 0; j < N; j++) {
            sortArr.put(arr[j], j);
        }
        
        int result = 0;
        for (int k : arr) {
            result = Math.max(result, notSortArr.get(k) - sortArr.get(k));
        }
        
        System.out.println(result);
    }
}
