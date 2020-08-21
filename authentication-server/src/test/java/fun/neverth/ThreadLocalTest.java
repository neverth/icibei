package fun.neverth;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/13 21:22
 */
public class ThreadLocalTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadLocal threadLocal = new ThreadLocal();
        ThreadLocal threadLocal2 = new ThreadLocal();
        threadLocal.set("123");
        threadLocal2.set("234");
        System.out.println(threadLocal.get());
        System.out.println(threadLocal2.get());

//        threadLocal.set("2323");
//        Thread a = new Thread(()->{
//            ThreadLocal b = new ThreadLocal();
//            b.set("1111");
//            System.out.println(b.get());
//        });
//        a.start();
//        a.join();
//
//        System.out.println(threadLocal.get());
//        System.out.println(threadLocal.get());
//
//        threadLocal = null;
//
//        System.gc();
//        Thread.sleep(10000);

    }
}
