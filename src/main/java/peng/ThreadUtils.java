package peng;

import java.util.concurrent.CountDownLatch;

/**
 * 线程工具
 * @author wsp
 * @since 2017/07/28
 */
public class ThreadUtils {
    private static final CountDownLatch Semaphore = new CountDownLatch(1);

    public static void wait_()throws InterruptedException{
       Semaphore.await();
    }

    public static void notify_(){
        Semaphore.countDown();
    }
}
