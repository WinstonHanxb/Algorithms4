package chatpter1.sector3;

import com.algs4.stdlib.StdIn;

/**
 * Created by 韩宪斌 on 2017/7/24.
 */
public class Parentheses {
    
    public static boolean parenthesesMatcher(String input) {
        Stack<Character> cStack = new Stack<Character>();
        for (char c : input.trim().toCharArray()) {
            switch (c) {
                case ' ':
                case '[':
                case '(':
                case '{':
                    cStack.push(c);
                    break;
                case ']':
                case '}':
                case ')': {
                    if (cStack.isEmpty() || (cStack.pop() + 1 == c)) {
                        return false;
                    }
                    break;
                }
                default:
                    return false;
            }
        }
        if (cStack.isEmpty()) {
            return true;
        }
        return false;
        
    }
    
    public static String parenthesesComplement(String input) {
        Stack<String> strings = new Stack<String>();
        for (char c : input.trim().toCharArray()) {
            switch (c) {
                case ' ':
                    break;
                case ')':
                    String s1 = strings.pop();
                    String s2 = strings.pop();
                    String s3 = strings.pop();
                    strings.push(("( " + s3 +" " +s2+" " + s1 + " )"));
                    break;
                default:
                    strings.push(c + "");
                    break;
            }
        }
        return strings.pop();
    }
    
    public static void main(String[] args) {
        while (true) {
            System.out.println("Please input the string without the left parentheses");
            String s = StdIn.readLine();
            System.out.println("It should be :" + parenthesesComplement(s));
        }
        
        
    }
}
