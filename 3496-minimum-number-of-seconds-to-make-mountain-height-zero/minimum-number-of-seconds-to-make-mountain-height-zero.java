class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long low = 1;
        long maxW = 0;
        for (int w : workerTimes) maxW = Math.max(maxW, w);
        
        // Worst case: slowest worker does it all
        long high = maxW * ((long) mountainHeight * (mountainHeight + 1) / 2);
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (canReduceHeight(mountainHeight, workerTimes, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean canReduceHeight(int targetHeight, int[] workerTimes, long timeLimit) {
        long totalReduced = 0;
        for (int w : workerTimes) {
            totalReduced += getMaxHeightForWorker(w, timeLimit, targetHeight);
            if (totalReduced >= targetHeight) return true;
        }
        return totalReduced >= targetHeight;
    }

    private long getMaxHeightForWorker(int w, long timeLimit, int mountainHeight) {
        long l = 0, r = mountainHeight; // A worker can't reduce more than the total height
        long bestX = 0;
        
        while (l <= r) {
            long x = l + (r - l) / 2;
            // Total time for height x: w * x * (x + 1) / 2
            // Rearranged to avoid overflow: (x * (x + 1) / 2) <= (timeLimit / w)
            if (x == 0) {
                l = 1;
                continue;
            }
            
            // Check if current x is feasible within timeLimit
            // We use a double cast or careful multiplication to avoid long overflow
            if ((double)w * x * (x + 1) / 2 <= timeLimit) {
                bestX = x;
                l = x + 1;
            } else {
                r = x - 1;
            }
        }
        return bestX;
    }
}