import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder(), temp = new StringBuilder();
//    private static final Set<String> set = new HashSet<>();
    private static final BitSet visited = new BitSet();
    private static String word, anagram;
    private static char[] wordArr;
    private static int length, n;
    
    public static void main(String... args) throws IOException {
        n = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < n; i++) {
            word = br.readLine();
            
            wordArr = word.toCharArray();
            Arrays.sort(wordArr);
                        
            length = word.length();
//            set.clear();
            visited.clear();
            
            dfs(0);
        }
        
        System.out.print(sb);
    }
    
    public static void dfs(int depth) {
        if (depth == length) {          
            sb.append(temp.toString()).append('\n');            
            return;
        }
        
        char prev = '\0';
        for (int i = 0; i < length; i++) {
            if (visited.get(i) || wordArr[i] == prev) continue;

            prev = wordArr[i];
            
            visited.set(i);           
            temp.append(wordArr[i]);
            
            dfs(depth+1);
            
            temp.setLength(temp.length()-1);
            visited.clear(i);
        }
    }
}