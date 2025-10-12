import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, ans;
    private static int[] arr;
    private static ArrayList<Pair> pairs;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        Arrays.sort(arr);
                        
        pairs = new ArrayList<>();
        
        for (int l = 0; l < n-1; l++) {
            int r = l+1;
            while (r < n) {
                int sum = arr[l] + arr[r];
                pairs.add(new Pair(sum, l, r));      
                r++;
            }
        }
        
        pairs.sort(Comparator.comparingInt(p -> p.sum));
        
        ans = Integer.MAX_VALUE;
        for (int i = 0; i < pairs.size(); i++) {
            Pair p = pairs.get(i);
            if (ans == 0) break;
            for (int j = i+1; j < pairs.size(); j++) {
                Pair q = pairs.get(j);
                int diff = q.sum - p.sum;
                if (diff >= ans) break;
                if (q.i == p.i || q.i == p.j || q.j == p.i || q.j == p.j) continue;
                ans = diff;
                if (ans == 0) break;
            }
        }        

        System.out.print(ans);
    }
    
    static class Pair {
        int sum, i, j;
        
        public Pair(int sum, int i, int j) {
            this.sum = sum;
            this.i = i;
            this.j = j;
        }
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();
        boolean negative = false;       

        while (c <= ' ') c = System.in.read();
        if (c == '-') {
            negative = true;
            c = System.in.read();
        }
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return negative ? -r : r;
    }
}                                                                                                                                                                    