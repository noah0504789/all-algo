import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static StringTokenizer st;
    //private static Map<Integer, Integer> map;
    private static int[] friends, map;
    private static long ans;
    private static int n, k;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        friends = new int[n];
        for (int i = 0; i < n; i++) friends[i] = br.readLine().length();

        //map = new HashMap<>();
        map = new int[21];
//        for (int i = 0; i <= k; i++) map.put(friends[i], map.getOrDefault(friends[i], 0) + 1);
        for (int i = 0; i <= k; i++) map[friends[i]]++;

        ans = 0;
        for (int checkIdx = 0; checkIdx < n; checkIdx++) {
            if (map[friends[checkIdx]] > 1) ans += map[friends[checkIdx]]-1;

            int addIdx = checkIdx+k+1;
            if (addIdx < n) map[friends[addIdx]]++;
            
            map[friends[checkIdx]]--;
        }

        System.out.print(ans);
    }
}