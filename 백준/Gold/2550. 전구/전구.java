import java.io.*;
import java.util.*;

public class Main {
        
    private static StringBuilder sb = new StringBuilder();
    private static int n, len, idx;
    private static int[] left, right, left_pos, right_pos, tails, tailsIdx, parent;    
    private static boolean[] isLis;
    private static List<Integer> ans;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        left = new int[n];
        for (int i = 0; i < n; i++) left[i] = readInt();
        
        right = new int[n];
        for (int i = 0; i < n; i++) right[i] = readInt();
        
        //left_pos = new int[n+1];
        //for (int i = 0; i < n; i++) left_pos[left[i]] = i;
        
        right_pos = new int[n+1];
        for (int i = 0; i < n; i++) right_pos[right[i]] = i;
        
        tails = new int[n];
        tailsIdx = new int[n];
        parent = new int[n];
        
        // 전구값을 기준으로 lis구하기
        for (int i = 0; i < n; i++) {
            int b = right_pos[left[i]];
            int pos = lowerBound(len, b);
            tails[pos] = b;
            tailsIdx[pos] = i;
            parent[i] = pos-1>=0?tailsIdx[pos-1]:-1;
            if (pos == len) len++;
        }
        
        isLis = new boolean[n];
        idx = tailsIdx[len-1];
        while (idx != -1) {
            isLis[idx] = true;
            idx = parent[idx];
        }
        
        ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (isLis[i]) ans.add(left[i]);
        }
        
        sb.append(ans.size()+"\n");
        
        Collections.sort(ans);
        for (int a : ans) sb.append(a+" ");

        System.out.print(sb);
    }
    
    private static int lowerBound(int r, int x) {
        int l = 0;
        while (l < r) {
            int m = (l+r)>>>1;
            if (tails[m] >= x) r = m;
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