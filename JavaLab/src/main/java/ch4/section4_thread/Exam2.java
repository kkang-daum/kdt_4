package ch4.section4_thread;

import java.util.ArrayList;
import java.util.List;

//스레드에 의해 호출될 함수 및 데이터를 가지는 객체..
class Pool {
    List<String> products = new ArrayList<>();

    public synchronized void get() throws InterruptedException {
        if(products.size() == 0){
            wait();//스래드 대기상태로 만드는 함수..
            //wait 에 의해 대기상태가 되면 자신의 힘으로는 못 깨어난다.
            //누군가가 이 스레드를 깨어줘야 한다..
        }
        products.remove(0);
        System.out.println("소비 / 재고 = "+products.size());
    }
    public synchronized void add(String value) throws InterruptedException {
        products.add(value);
        System.out.println("생산 / 재고 = " + products.size());
        notifyAll();
    }
}

class ProductGet implements Runnable {
    Pool pool;
    ProductGet(Pool pool){
        this.pool = pool;
    }

    @Override
    public void run() {
        try{
            for(int i = 1; i<= 10;i++){
                pool.get();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
class ProductAdd implements Runnable {
    Pool pool;
    ProductAdd(Pool pool){
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                pool.add("상품 " + i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

public class Exam2 {
    public static void main(String[] args) {
        Pool pool = new Pool();
        Thread getThread = new Thread(new ProductGet(pool));
        Thread addThread = new Thread(new ProductAdd(pool));

        getThread.start();
        addThread.start();
    }
}
