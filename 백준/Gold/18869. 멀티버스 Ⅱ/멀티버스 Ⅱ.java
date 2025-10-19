import java.io.*;
import java.util.*;

public class Main {
    
    private static int m, n;
    private static long cnt;
    private static int[][] arr;
    private static Set<Integer> set;
    private static List<Integer>[] list;
    
    public static void main(String... args) throws IOException {        
        m = readInt();
        n = readInt();
        cnt = 0;
        
        arr = new int[m][n];
        set = new HashSet<>();
        list = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            set.clear();
            for (int j = 0; j < n; j++) set.add(arr[i][j] = readInt());
            
            list[i] = new ArrayList<>(set);
            Collections.sort(list[i]);           
        }
        
        for (int i = 0; i < m; i++) {            
            for (int j = 0; j < n; j++) {
                arr[i][j] = lowerBound(list[i], arr[i][j]);
            }
        }
        
        for (int i = 0; i < m-1; i++) {            
            for (int j = i+1; j < m; j++) {
                if (Arrays.equals(arr[i], arr[j])) cnt++;
            }
        }

        System.out.print(cnt);
    }
    
    private static int lowerBound(List<Integer> arr, int target) {
        int l = 0, r = arr.size();
        while (l<r) {
            int m = (l+r)>>>1;
            if (arr.get(m) >= target) r = m;
            else l = m+1;
        }
        return l;
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