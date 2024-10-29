import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {        
    private static BufferedReader br;
        
    private static StringBuilder sb;
    private static HashMap<String, Integer> map;
    private static String tree;
    private static double totalCnt;
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        map = new HashMap<>();
        
        totalCnt = 0;        

        while ((tree = br.readLine()) != null) {
            map.put(tree, map.getOrDefault(tree, 0) + 1);
            
            totalCnt++;
        }

        List<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        
        for (String key : list) {
            sb.append(key).append(" ").append(String.format("%.4f", map.get(key) / totalCnt * 100)).append("\n");
        }
       
        System.out.print(sb);
    }
}