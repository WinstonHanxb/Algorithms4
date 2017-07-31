package chatpter1.section3;

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
    
    /**
     * 给定一个输入序列和指定的输出序列，判断此输入序列能否产生这个输出序列
     *
     * @param inputSequence
     * @param outputSequence
     * @return
     */
    public boolean isPossibleOutput(Queue<Item> inputSequence, Queue<Item> outputSequence) {
//        if (isUnderflowSequence(inputSequence)) return false;
        //首先输入序列不能向下溢出
        Stack<Item> memStack = new Stack<Item>();
        
        while (!outputSequence.isEmpty() && !inputSequence.isEmpty()) {
            Item nextOut = outputSequence.peek();
            //首先下一个假设输出的元素已在栈中则必在栈顶，且输入序列的操作为出栈操作，则可按照顺序执行下一位判断
            if (!memStack.isEmpty() && memStack.peek().equals(nextOut) && inputSequence.peek().toString().equals("-")) {
                memStack.pop();
                inputSequence.dequeue();
                outputSequence.dequeue();
            } else {
                //若栈顶中没找到该元素，则必须按照输入序列操作栈，直到将该元素压入栈顶
                
                while (!inputSequence.isEmpty() && (null == memStack.peek() ? true : !memStack.peek().equals(nextOut))) {
                    if (inputSequence.peek().toString().equals("-")) {
                        inputSequence.dequeue();
                        memStack.pop();
                    } else {
                        memStack.push(inputSequence.dequeue());
                    }
                }
                
                
            }
        }
        
        if (!outputSequence.isEmpty()) {
            return false;
        }
        return true;
        
    }
    
    /**
     * 给定一个输出序列，判断这个序列是不是一个合法的序列
     * 序列只支持整型
     *
     * @param outputSequence
     * @return
     */
    public static boolean isLegalOutput(Queue<Integer> outputSequence) {
        while (!outputSequence.isEmpty()) {
            int c = outputSequence.dequeue();
            /**
             * 注意由于Java的泛型是类型擦除，outputSequence.toArray()事实上返回的是一个Object[]并不是理想的Integer[]数组
             * 而又由于Java的数组是协变的，这种行为不会带来编译期错误，但会带来运行期异常
             */
            Object[] nums = outputSequence.toArray();
            for (int i = 0; i < nums.length; i++) {
                if (Integer.parseInt(String.valueOf(nums[i])) < c) {
                    int a = Integer.parseInt(String.valueOf(nums[i]));
                    for (int j = i + 1; j < nums.length; j++) {
                        if (Integer.parseInt(String.valueOf(nums[j])) > a && Integer.parseInt(String.valueOf(nums[j])) < c) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return true;
    }
    
    
    public static void main(String[] args) {
//        Integer[][] testCases = {{1, 2, 3}, {3, 1, 2}, {3, 2, 1}};
//        for (Integer[] testcase : testCases) {
//            Queue<Integer> testQueue = new Queue<Integer>();
//            for (Integer num : testcase) {
//                testQueue.enqueue(num);
//            }
//
//            System.out.println(StackSequenceChecker.isLegalOutput(testQueue));
//        }
        
        StackSequenceChecker<Character> cS = new StackSequenceChecker<Character>();
        
        Character[][] testInputs = {{'a', 'd', '-', '-', 'c'}, {'a', 'b', '-', '-', 'c', '-', '-'}, {'a', 'b', 'd',
                '-', 'c', '-', '-'}};
        Character[] testOutput = {'b', 'a'};
        Queue<Character> outputSequence = new Queue<Character>();
        for (Character c : testOutput) {
            outputSequence.enqueue(c);
        }
        for (Character[] testInput : testInputs) {
            Queue<Character> inputSequence = new Queue<Character>();
            for (Character c : testInput) {
                inputSequence.enqueue(c);
            }
            
            System.out.println(cS.isPossibleOutput(inputSequence, outputSequence));
        }
    }
    
}
