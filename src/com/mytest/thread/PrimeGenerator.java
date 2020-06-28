package com.mytest.thread;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-10-22
 */
public class PrimeGenerator implements Runnable{
    @Override
    public void run() {
        long number = 2L;

        while (true){
            if (isPrime(number)){
                System.out.printf("number %d is prime\n", number);
            }
            if (Thread.currentThread().isInterrupted()){
                System.out.println("The prime-generator has been interrupted");
                return;
            }
            number++;
        }

    }

    /**
     * 判断是否为素数/质数的最有效方法
     * 1.小于5的2和3
     * 2.大于等于5的素数一定和6的倍数相邻，例如5和7，11和13,17和19等等。
     */
    private boolean isPrime(long number){

        if (number == 2 || number == 3){
            return true;
        }

        //不在6的倍数两侧的一定不是素数
        if (number % 6 != 1 && number % 6 != 5){
            return false;
        }

        int sqr = (int) Math.sqrt(number);
        for (int i = 5; i <= sqr; i+=6) {
            if (number % i == 0 || number % (i + 2) == 0){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new PrimeGenerator());
        thread.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();

    }

}
