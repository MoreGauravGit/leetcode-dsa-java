class Solution {

    public int calPoints(String[] operations) {

        Stack<Integer> stack = new Stack<>();

        for (String op : operations) {

            if (op.equals("+")) {

                int top = stack.pop();
                int second = stack.peek();

                int sum = top + second;

                stack.push(top);
                stack.push(sum);

            }

            else if (op.equals("D")) {

                stack.push(2 * stack.peek());

            }

            else if (op.equals("C")) {

                stack.pop();

            }

            else {

                stack.push(Integer.parseInt(op));

            }
        }

        int ans = 0;

        while (!stack.isEmpty()) {
            ans += stack.pop();
        }

        return ans;
    }
}