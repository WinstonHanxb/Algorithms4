package extras.MultiThread;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ExclusiveLock  {
    public static void main(String[] args) {
        String s1 = "test";
        String s2 = new String("test");
        System.out.println(s2 == "test");
    }

}
