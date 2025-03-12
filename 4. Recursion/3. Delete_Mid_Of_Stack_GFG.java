import java.util.Stack;

class Solution {
    void deleteMidFromStack(Stack<Integer> stack,int mid){
        
        if(stack.size()==0) return;
        if(stack.size()==mid){
            stack.pop();
            return;
        }
        
        int top = stack.pop();
        deleteMidFromStack(stack,mid);
        stack.push(top);
        
    }
    
    public void deleteMid(Stack<Integer> stack) {
        // code here
        
        int mid = (stack.size() + 1) / 2;
        
        deleteMidFromStack(stack,mid);
    }
}

