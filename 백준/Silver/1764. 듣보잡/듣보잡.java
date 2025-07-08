import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String[] arr1, arr2;
    private static int n, m;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();

        n = readInt();
        m = readInt();
                
        arr1 = new String[n];
        for (int i = 0; i < n; i++) arr1[i] = br.readLine();
        
        arr2 = new String[m];
        for (int i = 0; i < m; i++) arr2[i] = br.readLine();
        
        Arrays.sort(arr1);
        Arrays.sort(arr2);      

        List<String> ans = new ArrayList<>();
        
        int i = 0, j = 0;
        
        while (i < n && j < m) {
            int cmp = arr1[i].compareTo(arr2[j]);
            
            if (cmp == 0) {
                ans.add(arr1[i]);
                i++;
                j++;
            } else if (cmp < 0) {
                i++;
            } else {
                j++;
            }
        }        
        
        sb.append(ans.size()).append("\n");              
        
        for (String s : ans) sb.append(s).append("\n");

        System.out.print(sb);
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();
        boolean negative = false;       

        while (c <= ' ') c = System.in.read();

        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return r;
    }
}
