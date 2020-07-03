package test;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jingqing
 */
public class T {

    public static   Thread t1=null;
    public static   Thread t2 = null;

    public static void main(String[] args) {

    }

    /**
     * LockSupport方式
     *
     * @param letters
     * @param numbers
     */
    public static void lockSupportImp(String[] letters, String[] numbers) {

        t1= new Thread(()->{
            for(String lettler:letters) {
                System.out.println(lettler);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        t2= new Thread(()->{
            for(String number : numbers){
                LockSupport.park();
                System.out.println(number);
                LockSupport.unpark(t1);
            }
        });

        t1.start();
        t2.start();
    }

    @Test
    public void test(){
        String[] letters = {"A", "B", "C", "D", "E"};
        String[] numbers = {"1", "2", "3", "4", "5"};
        reentrantLockImp(letters,numbers);
    }

    /**
     * wait notify方式
     *
     * @param letters
     * @param numbers
     */
    public void waitNotifyImpl(String[] letters, String[] numbers){

       new Thread(()->{
            synchronized (this){
                for(String letter:letters){
                    System.out.println(letter);
                    try {
                        this.notify();
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.notify();
            }
        },"t1").start();

       new Thread(()->{
            synchronized (this){
                for(String number:numbers){
                    try{
                        System.out.println(number);
                        this.notify();
                        this.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                this.notify();
            }
        },"t2").start();
    }

    public void reentrantLockImp(String[] letters,String[] numbers){
        Lock lock=new ReentrantLock();
        Condition condition1= lock.newCondition();
        Condition condition2= lock.newCondition();

       new Thread(()->{
           lock.lock();
           try {
               for(String letter:letters){
                   System.out.println(letter);
                   condition2.signal();
                   condition1.await();
               }
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
               lock.unlock();


       }).start();

        new Thread(()->{
            lock.lock();
            try {
                for(String number:numbers){
                    System.out.println("---------");
                    condition2.wait();
                    System.out.println(number);
                    condition1.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                lock.unlock();


        }).start();
    }
}
