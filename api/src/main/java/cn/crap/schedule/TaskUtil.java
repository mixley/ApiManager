package cn.crap.schedule;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Ehsan
 * @date 2018/10/5 15:42
 */
public class TaskUtil {
    private static ThreadPoolExecutor executor ;
    private TaskUtil(){
        init();
    }
    public static void init(){
        if (null==executor){
            executor = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        }
    }
    public static void execute(AbstractTask task){
        init();
        executor.execute(task);
    }
    public static void shutdownNow(){
        init();
        executor.shutdownNow();
    }
}
