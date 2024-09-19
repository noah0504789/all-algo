import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int maxMeeting = Integer.parseInt(br.readLine());        
        int[][] meetings = new int[maxMeeting][2];

        StringTokenizer st;     
        for (int i = 0; i < maxMeeting; i++) {
            st = new StringTokenizer(br.readLine());            
            meetings[i][0] = Integer.parseInt(st.nextToken());
            meetings[i][1] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(meetings, (m1, m2) -> {
            if (m1[1] == m2[1]) {
                return m1[0] - m2[0];
            }
            return m1[1] - m2[1];
        });
        
        int cnt = 0;
        int end = 0;

        for (int i = 0; i < maxMeeting; i++) {
            if(end <= meetings[i][0]) {
                end = meetings[i][1];
                cnt++;
            }
        }
        System.out.println(cnt);
    }    
}