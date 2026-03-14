class Solution {
    int count = 0;
    String ans;
    public String getHappyString(int n, int k) {
        char[] l = {'a', 'b', 'c'};
        helper(l, n, k, new StringBuilder());
        return (k <= count) ? ans : "";
    }
    private void helper(char[] l, int n, int k, StringBuilder sb){
        int m = sb.length();
        if(m == n){
            count++;
            if(count == k){
                ans = sb.toString();
            }
            return;
        }
        for(char ch : l){
            if(m > 0 && sb.charAt(m - 1) == ch) continue;
            helper(l, n, k, sb.append(ch));
            sb.deleteCharAt(sb.length()-1);
            if(count == k) return;
        }
    }
}