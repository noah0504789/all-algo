import java.util.*;

class Solution {
    public String solution(String p) {
        if (p.isEmpty() || isRight(p)) return p;
        
        int cut = balance(p);
        String u = p.substring(0, cut+1);
        String v = p.substring(cut+1);
        
        if (isRight(u)) return u + solution(v);
        
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(solution(v));
        sb.append(")");
        sb.append(invert(u.substring(1, u.length()-1)));
        
        return sb.toString();
    }
    
    private boolean isRight(String p) {
        int cnt = 0;
        
        for (int i = 0; i < p.length(); i++) {
            cnt += (p.charAt(i) == '(') ? 1 : -1;
            if (cnt < 0) return false;
        }
        
        return cnt == 0;
    }
    
    private int balance(String p) {
        int cnt = 0;
        
        for (int i = 0; i < p.length(); i++) {
            cnt += (p.charAt(i) == '(') ? 1 : -1;
            if (cnt == 0) return i;
        }
        
        return p.length() - 1;
    }
    
    private String invert(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< s.length(); i++) {
            sb.append(s.charAt(i) == '(' ? ')' : '(');
        }
        return sb.toString();
    }
}