import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {        
    private static BufferedReader br;    
    private static StringTokenizer st;
    private static StringBuilder sb;
    
    private static TreeSet<String> set;
    private static String name, activity;
    private static int n;
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        n = Integer.parseInt(br.readLine());
        
        set = new TreeSet<>();
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            
            name = st.nextToken();
            activity = st.nextToken();
            
            if (activity.equals("enter")) set.add(name);
            else set.remove(name);
        }
                
        for (Iterator<String> it = set.descendingIterator(); it.hasNext(); ) sb.append(it.next()).append("\n");
        
        System.out.print(sb);
    }   
}