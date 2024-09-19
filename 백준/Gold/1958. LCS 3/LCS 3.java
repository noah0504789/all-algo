import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][][] dp;
    private static String s1, s2, s3;

    public static void main(String... args) throws IOException {
        s1 = br.readLine();    
        s2 = br.readLine();    
        s3 = br.readLine();    
        
        int s1Length = s1.length(), s2Length = s2.length(), s3Length = s3.length();
        
        dp = new int[s1Length+1][s2Length+1][s3Length+1];
        
        for (int i = 1; i <= s1Length; i++) {
            for (int j = 1; j <= s2Length; j++) {
                for (int k = 1; k <= s3Length; k++) {
                    if (s1.charAt(i-1) == s2.charAt(j-1) && s2.charAt(j-1) == s3.charAt(k-1)) dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    else dp[i][j][k] = Math.max(Math.max(dp[i-1][j][k], dp[i][j-1][k]), dp[i][j][k-1]);
                }
            }
        }
            
        System.out.print(dp[s1Length][s2Length][s3Length]);
    }
}
