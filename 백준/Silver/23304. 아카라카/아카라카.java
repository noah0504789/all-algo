import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br;
    private static char[] arr;
    private static int n;
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        arr = br.readLine().toCharArray();
        n = arr.length;
        
        System.out.print(akaraka(0, n-1) ? "AKARAKA" : "IPSELENTI");        
    }
    
    private static boolean akaraka(int l, int r)  {
        int length = r-l+1;
        if (length == 1) return true;
        if (!isPalindrome(l, r)) return false;
        
        int half = length/2;
        
        return akaraka(l, l+half-1) && akaraka(r-half+1, r);
    }
    
    private static boolean isPalindrome(int l, int r) {
        while (l < r) {
            if (arr[l] != arr[r]) return false;
            l++;r--;
        }
        return true;
    }
}