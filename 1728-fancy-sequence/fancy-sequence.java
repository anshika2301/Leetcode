class Fancy {
    int MOD = 1_000_000_007;
    List<Long> ll;      
    long add;           
    long mul;          

   
    private long power(long a, long n) {
        if (n == 0) return 1;
        long ans = power(a, n / 2);
        ans = (ans * ans) % MOD;
        if (n % 2 == 1) {
            ans = (ans * a) % MOD;
        }
        return ans;
    }

    public Fancy() {
        ll = new ArrayList<>();
        add = 0;
        mul = 1;
    }
    
    public void append(int val) {
       
        long x = ((val - add + MOD) % MOD) * power(mul, MOD - 2) % MOD;
        ll.add(x);
    }
    
    public void addAll(int inc) {
       
        add = (add + inc) % MOD;
    }
    
    public void multAll(int m) {
       
        add = (add * m) % MOD;
        mul = (mul * m) % MOD;
    }
    
    public int getIndex(int idx) {
        if (idx >= ll.size()) {
            return -1;
        }
        
        return (int) ((ll.get(idx) * mul + add) % MOD);
    }
}