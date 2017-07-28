package chatpter1.sector3;

/**
 * Created by 韩宪斌 on 2017/7/27.
 * 针对栈的序列可生成问题的解答
 */
public class StackSequenceChecker<Item> {
    /**
     * 给定一个输入序列用来表示入栈和出栈操作
     *  e.g:
     *     {1,-,2,3,...,N-1,-,-,-,...}
     * 其中数字表示对应序号的元素，‘-’符号表示出栈操作
     */
    
    /**
     * 输入一个输入序列，判断这个序列会不会向下溢出
     *
     * @param inputSequence
     * @return
     */
    public boolean isUnderflowSequence(Queue<Item> inputSequence) {
        int count = 0;
        while (!inputSequence.isEmpty()) {
            if (inputSequence.dequeue().toString().equals("-")) {
                count--;
            } else {
                count++;
            }
            if (count < 0) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isPossibleOutput(Queue<Item> inputSequence, Queue<Item> outputSequence) {
        if (!isUnderflowSequence(inputSequence)) {//首先输入序列不能向下溢出
            Stack<Item> memStack = new Stack<Item>();
            while (!outputSequence.isEmpty() && !inputSequence.isEmpty()) {
                Item nextOut = outputSequence.peek();
                //首先下一个假设输出的元素已在栈中则必在栈顶，且输入序列的操作为出栈操作，则合法
                if (memStack.peek().equals(nextOut) && inputSequence.peek().toString().equals("-")) {
                    memStack.pop();
                    inputSequence.dequeue();
                    outputSequence.dequeue();
                    continue;
                } else if{
                    //若栈顶中没找到该序列，则必须按照输入序列的操作栈，直到将
                }
            }
        } else return false;
    }
}
