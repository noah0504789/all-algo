import java.io.*;

public class Main {
    private static final StringBuilder sb = new StringBuilder();
    private static boolean[][] dp;
    private static int[] nums;    
    private static int n, m, s, e, result;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        nums = new int[n];
        dp = new boolean[n][n];
        
        for (int i = 0; i < n; i++) nums[i] = readInt();
        
        m = readInt();
        
        for (int i = 0; i < m; i++) {
            s = readInt()-1;
            e = readInt()-1;
            
            result = isPalindrome() ? 1 : 0;
            
            sb.append(result).append('\n');
        }        
        
        System.out.print(sb);
    }
    
    private static boolean isPalindrome() {
        int left = s, right = e;
        while (left < right) {
            if (dp[left][right]) return true;
            if (nums[left] != nums[right]) return dp[s][e] = false;
            left++;
            right--;
        }
        
        return dp[s][e] = true;
    }
    
    private static int readInt() throws IOException {
        int result = 0, c = System.in.read();
        
        while (c <= ' ') c = System.in.read();
        
        while (c >= '0' && c <= '9') {
            result *= 10;
            result += (c - '0');
            c = System.in.read();
        }
        
        return result;
    }
}