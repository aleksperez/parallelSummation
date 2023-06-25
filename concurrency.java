import java.util.Random;

public class concurrency {
    public static int[] array = new int[20000*10000];


    public static void main(String[] args)
    {
        Random rand = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(10) + 1;
        }

        long start = System.currentTimeMillis();
        System.out.println("\nSingle thread summation total: "+thread.getSum(array));
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start+"milliseconds"));

        System.out.println("--------------");

        start = System.currentTimeMillis();
        System.out.println("Parallel thread summation total: "+thread.getParallelSum(array));
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start+"milliseconds"));
    }
}

