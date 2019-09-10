/**
 * Created by shawn on 3/27/2017.
 */

import java.util.*;

public class PostfixEvaluator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        StringBuilder input = new StringBuilder();
        PostfixEvaluator p = new PostfixEvaluator();
        InfixToPostfixConverter i = new InfixToPostfixConverter();
        while(!input.toString().equals("q") && !input.toString().equals("Q")) {
            input.setLength(0);
            System.out.print("Enter an infix expression (if you wish to quit, enter 'q' or 'Q': ");
            input.append(scan.nextLine());
            if (!input.toString().equals("q") && !input.toString().equals("Q")) {
                char[] infixArray = input.toString().toCharArray();
                for (int c = 0; c < infixArray.length; c++){
                    if (infixArray[c] == '{' || infixArray[c] == '['){
                        infixArray[c] = '(';
                    }
                    if (infixArray[c] == '}' || infixArray[c] == ']'){
                        infixArray[c] = ')';
                    }
                }
                String post = i.convert(infixArray);
                System.out.println(post);
                char[] postArray = post.toCharArray();
                System.out.println(p.evaluate(postArray));
            }
        }
    }
    public PostfixEvaluator() {

    }
    public int evaluate(char[] postfix) {
        Stack operandStack = new Stack();
        int counter = 0;
        while (counter < postfix.length) {
            if (Character.isDigit(postfix[counter]) || postfix[counter] == '.'){
                StringBuilder operand = new StringBuilder();
                while (postfix[counter] != ' ') {
                    operand.append(postfix[counter]);
                    counter += 1;
                }
                double value = Double.parseDouble(operand.toString());
                operandStack.push(value);
            }
            else{
                double operand2 = (double)operandStack.pop();
                double operand1 = (double)operandStack.pop();
                double result = 0;
                if (postfix[counter] == '+'){
                    result = operand1 + operand2;
                }
                if (postfix[counter] == '-'){
                    result = operand1 - operand2;
                }
                if (postfix[counter] == '*'){
                    result = operand1 * operand2;
                }
                if (postfix[counter] == '/'){
                    if (operand2 == 0){
                        System.out.println("Evaluation encountered division by zero, try again");
                        return 0;
                    }
                    result = operand1/operand2;
                }
                operandStack.push(result);
                counter += 1;
            }
            counter += 1;
        }
        Double doubleValue = (double)operandStack.pop();
        Integer returnValue = doubleValue.intValue();
        return returnValue;
    }
}
