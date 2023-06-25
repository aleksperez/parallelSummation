public class thread extends Thread {

    private int[] arr;
    private int low;
    private int high;
    private int partial;

    public thread(int[] arr, int low, int high) {
        this.arr = arr;
        this.low = low;
        this.high = Math.min(high, arr.length);

    }

    public void run() {
        partial = calculateSum(arr, low, high);
    }

    public int getPartialSum() {
        return partial;
    }

    public static int getSum(int[] arr) {
        return calculateSum(arr, 0, arr.length);
    }

    public static int calculateSum(int[] arr, int low, int high) {
        int total = 0;
        for (int i = low; i < high; i++) {
            total += arr[i];
        }
        return total;
    }

    public static int getParallelSum(int[] arr) {
        //my jvm has 8 processors so Runtime.getRuntime().availableProcessors() will yield 8
        return parallelSum(arr, Runtime.getRuntime().availableProcessors());
    }

    public static int parallelSum(int[] arr, int threads) {
        int size = (int) Math.ceil(arr.length * 1.0 / threads);
        thread[] sums = new thread[threads];

        //initiating new threads and storing them within the sums array and running them after appending to sums
        for (int i = 0; i < threads; i++) {
            sums[i] = new thread(arr, i * size, (i + 1) * size);
            sums[i].start();
        }
        //when one thread is done with getting sum, the .join starts the next thread and so on
        try {
            for (thread sum : sums) {
                sum.join();
            }
        } catch (InterruptedException ignored) {
        }
        //adding together the sum of each thread
        int total = 0;
        for (thread sum : sums) {
            total += sum.getPartialSum();
        }
        return total;
    }

}