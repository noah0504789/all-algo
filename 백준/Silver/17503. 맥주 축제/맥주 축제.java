import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, v, c, k;
    private static long l, r, mid, min;
    private static Bear[] arr;
    private static PriorityQueue<Integer> pq;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        k = readInt();
        
        arr = new Bear[k];        
        for (int i = 0; i < k; i++) arr[i] = new Bear(readInt(), readInt());
        
        Arrays.sort(arr, Comparator.comparingInt(b -> b.c));
        
        l = 1;
        r = arr[k-1].c;   
        min = -1;
        pq = new PriorityQueue<>();
        while (l <= r) {
            mid = l + ((r-l)>>>1);
            if (can(mid)) {
                min = mid;
                r = mid-1;                
            } else {
                l = mid+1;
            }
        }

        System.out.print(min);
    }
    
    private static boolean can(long target) {
        pq.clear();
        long sum = 0;
        for (Bear b : arr) {
            if (b.c > target) break;
            pq.offer(b.v);
            sum += b.v;
            if (pq.size() > n) sum -= pq.poll();
        }
        
        if (pq.size() < n) return false;
        
        return sum >= m;
    }
    
    static class Bear {
        int v, c;
        Bear(int v, int c) {
            this.v = v; // 선호도
            this.c = c; // 도수레벨
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