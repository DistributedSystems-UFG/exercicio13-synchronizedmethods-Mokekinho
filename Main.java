public class Main {

    static class UnsynchronizedCounter {
        private int c = 0;
        
        public void increment() {
            c++;
        }
        
        public void decrement() {
            c--; 
        }
        
        public int value() {
            return c;
        }
    }

    static class SynchronizedCounter {
        private int c = 0;
        
        
        public synchronized void increment() {
            c++;
        }
        
        public synchronized void decrement() {
            c--;
        }
        
        public synchronized int value() {
            return c;
        }
    }
  

    public static void main(String[] args) throws InterruptedException {
        int n = 100000;

        System.out.println("Sem Sync");
        UnsynchronizedCounter unsyncCounter = new UnsynchronizedCounter();
        
        
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < n; i++) unsyncCounter.increment();
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < n; i++) unsyncCounter.decrement();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join(); 

        System.out.println("Valor esperado: 0");
        System.out.println("Valor obtido Não Sincronizad): " + unsyncCounter.value());
        System.out.println();

        System.out.println("Com Sync");
        SynchronizedCounter syncCounter = new SynchronizedCounter();
        
       
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < n; i++) syncCounter.increment();
        });
        
        Thread t4 = new Thread(() -> {
            for (int i = 0; i < n; i++) syncCounter.decrement();
        });

        t3.start();
        t4.start();
        t3.join();
        t4.join();

        System.out.println("Valor esperado: 0");
        System.out.println("Valor obtido Sincronizado: " + syncCounter.value());
    }
}
