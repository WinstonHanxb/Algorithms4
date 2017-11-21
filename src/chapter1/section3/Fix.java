package chapter1.section3;

import com.algs4.stdlib.StdIn;

/**
 * Created by 韩宪斌 on 2017/7/24.
 */
public class Fix {
    //中序转后序
    public static String InfixToPostfix(String infix){
        Stack<String> strings = new Stack<String>();
        for (char c : infix.trim().toCharArray()) {
            switch (c){
                case '(':
                case ' ':
                    break;
                case ')':{
                    String s1 = strings.pop();
                    String s2 = strings.pop();
                    String s3 = strings.pop();
                    strings.push(("( " + s3 +" " +s1+" " + s2 + " )"));
                    break;
                }
                default:
                    strings.push(c+"");
            }
        }
        return strings.pop();
    }
    
    public static int EvaluatePostfix(String postfix){
        Stack<String> chars = new Stack<String>();
        for (char c : postfix.trim().toCharArray()) {
            switch (c){
                case ' ':
                case '(':
                    break;
                case ')':
                    String ops=chars.pop();
                    int num2=Integer.parseInt(chars.pop());
                    int num1=Integer.parseInt(chars.pop());
                    int result=0;
                    switch (ops){
                        case "+":
                            result=num1+num2;
                            break;
                        case "-":
                            result=num1-num2;
                            break;
                        case "*":
                            result=num1*num2;
                            break;
                        case "/":
                            result=num1/num2;
                            break;
                    }
                    chars.push(""+result);
                    break;
                default:
                    chars.push(c+"");
            }
        }
        return Integer.parseInt(chars.pop());
    }
    
    public static void main(String[] args) {
        while (true) {
            System.out.println("Please input the string without the left parentheses");
            String s = StdIn.readLine();
            String infix=Parentheses.parenthesesComplement(s);
            String postfix=InfixToPostfix(infix);
            int evaluate=EvaluatePostfix(postfix);
            System.out.println("Infix: " + infix);
            System.out.println("Postfix: " + postfix);
            System.out.println("Evaluation: " + evaluate);
        }
    }
    
    
    
}
