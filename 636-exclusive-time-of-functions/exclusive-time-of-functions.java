class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();

        int lastTime = 0;

        for (String log : logs) {
            // tách 3 phần: id, start/end, timestamp
            String[] parts = log.split(":", 3);

            int id = Integer.parseInt(parts[0]);
            boolean isStart = parts[1].charAt(0) == 's';  // nhanh hơn equals
            int time = Integer.parseInt(parts[2]);

            if (isStart) {
                // cộng thời gian cho hàm đang chạy (nếu có)
                if (!stack.isEmpty()) {
                    ans[stack.peek()] += time - lastTime;
                }

                stack.push(id);
                lastTime = time;

            } else { 
                // kết thúc hàm
                ans[stack.pop()] += time - lastTime + 1;
                lastTime = time + 1;
            }
        }

        return ans;
    }
}
