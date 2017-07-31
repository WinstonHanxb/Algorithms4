package chatpter1.section3;

import com.algs4.stdlib.StdIn;

import java.io.File;

/**
 * Created by 韩宪斌 on 2017/7/27.
 */
public class FileListPrinter {
    private Queue<String> fileListQueue;
    private String pathname;
    
    public FileListPrinter(String pathname) {
        this.pathname = pathname;
        
    }
    
    public void print(){
        fileListQueue=new Queue<String>();
        fileTraverser(pathname,0);
        while(!fileListQueue.isEmpty()){
            System.out.println(fileListQueue.dequeue());
        }
    }
    
    public void redirect(String pathname){
        this.pathname = pathname;
    }
    
    /**
     * 文件列表遍历函数
     * @param pathname
     * @param depth
     */
    private void fileTraverser(String pathname,int depth){
        File rootFile=new File(pathname);
        if(rootFile.isFile()){//如果就是一个文件地址
            fileListQueue.enqueue(stringBuilder(rootFile.getName(),depth));
        }
        if(rootFile.isDirectory()){
            fileListQueue.enqueue(stringBuilder(rootFile.getName(),depth)+"...");
            for(File subFile:rootFile.listFiles()){
                fileTraverser(subFile.toString(),depth+1);
            }
        }
    }
    
    /**
     * 根据递归的深度，产生最后打印的字符串
     * @param filename
     * @param depth
     * @return
     */
    private static String stringBuilder(String filename,int depth){
        StringBuilder outputString=new StringBuilder();
        for (int i = 0; i < 4 * depth; i++) {//保持输出的格式递归
            outputString.append(" ");
        }
        outputString.append("|--");
        outputString.append(filename);
        return outputString.toString();
    }
    
    
    public static void main(String[] args) {
        System.out.print("file path:");
        String pathname = StdIn.readLine().trim();
        FileListPrinter fp = new FileListPrinter(pathname);
        fp.print();
        while(true){
            System.out.print("new file path:");
            pathname = StdIn.readLine().trim();
            fp.redirect(pathname);
            fp.print();
        }
    }
}
