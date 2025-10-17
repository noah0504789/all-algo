import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, total, idx, min, l, r, covered;
    private static int[] cnt;
    private static Student[] arr;    
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        total = n*m;
        arr = new Student[total];
        idx = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {                
                arr[idx++] = new Student(i, readInt());                
            }
        }
        
        Arrays.sort(arr, Comparator.comparingInt(s -> s.c));
        cnt = new int[n];
                
        l = 0;
        covered = 0;
        min = arr[idx-1].c - arr[0].c;
        
        for (int r = 0; r < n*m; r++) {
            if (cnt[arr[r].team]++ == 0) covered++;
            
            while (covered == n) {
                min = Math.min(min, arr[r].c-arr[l].c);
                
                if (--cnt[arr[l].team] == 0) covered--;
                l++;
            }
        }           

        System.out.print(min);
    }
    
    static class Student {
        int team, c;
        Student(int team, int c) {
            this.team = team;
            this.c = c;
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