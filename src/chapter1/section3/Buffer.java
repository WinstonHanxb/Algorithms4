package chapter1.section3;

/**
 * Created by 韩宪斌 on 2017/7/27.
 * 文本编辑器的缓冲区
 */
public class Buffer {
    private Stack<Character> beforeCursorStack = new Stack<Character>();
    private Stack<Character> afterCursorStack = new Stack<Character>();
    private int N;
    
    
    /**
     * 在光标位置插入字符c
     *
     * @param c
     */
    public void insert(char c) {
        beforeCursorStack.push(c);
        N++;
    }
    
    
    /**
     * 删除并返回光标位置的字符
     *
     * @return
     */
    public char delete() {
        if (!beforeCursorStack.isEmpty()) {
            N--;
            return beforeCursorStack.pop();
        }
        return '\0';
    }
    
    /**
     * 将光标向左移动k个位置
     *
     * @param k
     */
    public void left(int k) {
        while(k-->0){
            afterCursorStack.push(beforeCursorStack.pop());
        }
    }
    
    /**
     * 将光标向右移动k个位置
     *
     * @param k
     */
    public void right(int k) {
        while(k-->0){
            beforeCursorStack.push(afterCursorStack.pop());
        }
    }
    
    /**
     * 缓冲区中的字符数量
     *
     * @return
     */
    public int size() {
        return N;
    }
}
