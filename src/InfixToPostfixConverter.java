/**
 * Created by shawn on 3/27/2017.
 */
public class InfixToPostfixConverter {
    public InfixToPostfixConverter() {
    }

    public String convert(char[] infix) {
        Stack operatorStack = new Stack();
        StringBuilder postfix = new StringBuilder();
        char currentC;
        char prevC = 'o';
        int counter = 0;
        int countParen = 0;
        while (counter < infix.length) {
            currentC = infix[counter];
            if (counter > 0){
                prevC = infix[counter - 1];
            }
            if (counter > 0 && isOperator(prevC) && isOperator(currentC)){
                if (currentC != '(' && currentC != ')' && prevC != '(' && prevC !=')') {
                        throw new IllegalArgumentException("Adjacent Operators");
                }
            }
            if (!isOperator(currentC)) {
                if (!Character.isDigit(currentC)) {
                    if (currentC != '.') {
                        throw new IllegalArgumentException("Illegal Operand");
                    }
                }
                postfix.append(currentC);
            }
            else if (currentC == '(') {
                operatorStack.push(currentC);
                countParen += 1;
            }
            else if (currentC == ')') {
                countParen -= 1;
                while (!operatorStack.isEmpty() && (char)operatorStack.peek() != '(') {
                    postfix.append(' ');
                    postfix.append(operatorStack.pop());
                }
                if (!operatorStack.isEmpty() && (char)operatorStack.peek() != '(') {
                    return null;
                }
                else if(!operatorStack.isEmpty()) {
                    operatorStack.pop();
                }
            }
            else if (isOperator(currentC)) {
                postfix.append(' ');
                if (!operatorStack.isEmpty() && (getPriority(currentC) <= getPriority((char)operatorStack.peek()))){
                    postfix.append(operatorStack.pop());
                    postfix.append(' ');
                }
                operatorStack.push(currentC);
            }
            counter += 1;
        }
        while (!operatorStack.isEmpty()) {
            postfix.append(' ');
            postfix.append(operatorStack.pop());
        }
        if (countParen != 0){
            throw new IllegalArgumentException("Unbalanced Parentheses");
        }
        return postfix.toString();
    }

    private static int getPriority(char operator) {
        if (operator == '+' || operator == '-'){
            return 1;
        }
        if (operator == '*' || operator == '/'){
            return 2;
        }
        return -1;
    }

    private static boolean isOperator(char possibleOperator) {
        return possibleOperator == '+' ||
                possibleOperator == '-' ||
                possibleOperator == '*' ||
                possibleOperator == '/' ||
                possibleOperator == '(' ||
                possibleOperator == ')';
    }

}
