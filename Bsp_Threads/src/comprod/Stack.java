package comprod;

public class Stack {
    
    private int[] values;
    private int tos = 0;

    public Stack(int size) {
        values = new int[size];
    }
    
    public boolean isFull() {
        return values.length == tos;
    }
    
    public boolean isEmpty() {
        return tos == 0;
    }
    
    public int pop() {
        if(isEmpty())
            throw new RuntimeException("Stack is empty");
        return values[--tos];
    }
    
    public void push(int value) {
        if(isFull())
            throw new RuntimeException("Stack is full");
        values[tos++] = value;
    }

    @Override
    public String toString() {
        if(isEmpty())
            return "[]";
        
        StringBuilder sb = new StringBuilder("[");
        
        for (int i = 0; i < tos; i++) {
            sb.append(values[i]+",");
        }
        
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        Stack stack = new Stack(5);
        System.out.println(stack.toString());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.toString());
        
    }
    
}
