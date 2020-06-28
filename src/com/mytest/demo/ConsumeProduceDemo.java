package com.mytest.demo;

/**
 * 生产者与消费者
 * 1、生产者负责信息内容的生产
 * 2、生产者生产完一个完整的信息，消费者进行信息获取处理，即消费
 * 3、消费者要等生产者信息生产完才能获取，同样，生产者要等消费者处理完才能进行下一则信息处理
 */
public class ConsumeProduceDemo {

    public static void main(String[] args){
        //Message msg = new Message();
        //new Thread(new Producer(msg)).start(); //启动生产者线程
        //new Thread(new Consumer(msg)).start(); //启动消费者线程

        Resource res = new Resource();
        new Thread(new ProducerC(res)).start(); //启动生产者线程
        new Thread(new ConsumerC(res)).start(); //启动消费者线程

    }

    //生产者
    static class Producer implements Runnable{

        private Message msg;

        public Producer(Message msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            //信息生产
            for (int i = 0; i < 10; i++) {
                if (i % 2 == 0){ //第一则信息
                    this.msg.set("Drive" + i, "Kamen Rider Drive" + i);
                } else {
                    this.msg.set("Ghost" + i, "Kamen Rider Ghost" + i);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    //消费者
    static class Consumer implements Runnable{

        private Message msg;

        public Consumer(Message msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            //信息处理
            for (int i = 0; i < 10; i++) {
                System.out.println(this.msg.get());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    //信息保存类
    static class Message{
        String title;
        String content;
        boolean flag = true;  //生产或消费的指示 true：表示可以生产，但不能消费  false：不可以生产，但可以消费

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public synchronized void set(String title, String content){
            if (!this.flag){
                try {
                    super.wait();  //等待消费完后被唤醒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.title = title;
            this.content = content;
            this.flag = false;  //信息生产完
            super.notify(); //唤醒等待的消费线程
        }

        public synchronized String get(){
            if (this.flag){
                try {
                    super.wait();  //等待消费完后被唤醒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                return "title: " + this.title + "、content: " + this.content;
            } finally {
                this.flag = true;
                super.notify();  //唤醒等待的生产线程
            }
        }

    }


    /***********生产电脑与消费电脑**************/
    //生产线程
    static class ProducerC implements Runnable{
        Resource resource;

        public ProducerC(Resource resource) {
            this.resource = resource;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    this.resource.create();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //消费线程
    static class ConsumerC implements Runnable{
        Resource resource;

        public ConsumerC(Resource resource) {
            this.resource = resource;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    this.resource.fetch();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //资源处理保存类
    static class Resource{
        Computer computer;
        int number = 0;

        //生产
        public synchronized void create() throws InterruptedException {
            if (computer != null){
                super.wait(); //等消费完再继续
            }
            Thread.sleep(1000);
            computer = new Computer("Computer 0" + (++number), 10000.00);
            System.out.println("此时总共生产了" + Computer.getCount() + "台电脑");
            super.notifyAll(); //唤醒消费的进程
        }

        //消费
        public synchronized void fetch() throws InterruptedException {
            if (computer == null){
                super.wait(); //先等待生产
            }
            Thread.sleep(100);
            System.out.println(this.computer);
            computer = null;
            super.notifyAll();
        }

    }

    //生产与消费的对象
    static class Computer{
        String name;
        double price;
        static int count = 0; //生产的数量

        public Computer(String name, double price) {
            this.name = name;
            this.price = price;
            count++;
        }

        public static int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return "Computer{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }
    }


}
