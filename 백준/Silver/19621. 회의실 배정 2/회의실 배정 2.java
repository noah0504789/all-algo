import java.io.*;
import java.util.*;

public class Main {

    static class Meeting implements Comparable<Meeting> {
        int s, e, v;
        Meeting(int s, int e, int v) { this.s = s; this.e = e; this.v = v; }
        
        public int compareTo(Meeting o) {
            // 시작 시간 기준 정렬 (끝 시간으로 정렬해도 됨)
            int c = Integer.compare(this.s, o.s);
            if (c != 0) return c;
            return Integer.compare(this.e, o.e);
        }
    }

    public static void main(String[] args) throws Exception {
        int n = readInt();
        Meeting[] a = new Meeting[n];
        for (int i = 0; i < n; i++) {
            int s = readInt();
            int e = readInt();
            int v = readInt();
            a[i] = new Meeting(s, e, v);
        }

        Arrays.sort(a); // 체인 순서 보장 용

        long[] dp = new long[n + 1];
        // dp[0] = 0 (회의 0개)
        if (n >= 1) dp[1] = a[0].v;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + a[i - 1].v);
        }

        System.out.println(dp[n]);
    }

    // 빠른 입력
    static int readInt() throws IOException {
        int r = 0, c = System.in.read();
        boolean neg = false;
        while (c <= ' ') c = System.in.read();
        if (c == '-') { neg = true; c = System.in.read(); }
        while ('0' <= c && c <= '9') { r = r * 10 + (c - '0'); c = System.in.read(); }
        return neg ? -r : r;
    }
}
