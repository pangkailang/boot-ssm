package com.pkl.boot.common.test;

public class SequenceTest {
    private static int x = 0,y = 0;
    private static int a = 0,b = 0;
    public static void main(String[] args) throws Exception{
        int i=0;
        for (;;){
            i++;
            x = 0;y = 0;
            a = 0; b = 0;
            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    shortWait(100000);
                    a = 1;
                    x = b;
                }
            });

            Thread other = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;
                }
            });
            one.start();other.start();
            one.join();other.join();
            String reulst = "第"+i +"次("+x+","+ y + ")";
            if(x==0 && y ==0){
                System.err.println(reulst);
            }
        }
    }
    public static void shortWait(long interval){
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        } while (start + interval >= end);
    }
}
