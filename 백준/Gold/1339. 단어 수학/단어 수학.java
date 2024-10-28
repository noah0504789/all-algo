import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {        
    private static BufferedReader br;

    private static Map<Character, Long> map;
    private static char[] cArr;
    private static long ans;
    private static int n;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        map = new HashMap<>();

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            cArr = br.readLine().toCharArray();

            long weight = 1;
            for (int j = cArr.length-1; j >= 0; j--) {
                map.put(cArr[j], map.getOrDefault(cArr[j], 0L) + weight);
                weight *= 10;
            }
        }

        map = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1,
                LinkedHashMap::new
            ));

        int weight = 9;
        for (long num : map.values()) {
            ans += num * weight;
            weight--;
        }

        System.out.print(ans);
    }
}