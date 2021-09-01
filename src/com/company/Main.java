package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        method1();
        method2();

    }

    public static void method1() {
        final int SIZE = 100_000_000;
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5.0) *
                    Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0));
        }
        System.out.println(System.currentTimeMillis() - a);
    }

    public static void method2() {
        final int SIZE = 100_000_000;
        final int HALF = SIZE / 2;
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        float[] firstHalfArray = new float[HALF];
        float[] secondHalfArray = new float[HALF];
        System.arraycopy(arr, 0, firstHalfArray, 0, HALF);
        System.arraycopy(arr, HALF, secondHalfArray, 0, HALF);

        Thread firstThread = new Thread(() -> {
            for (int i = 0; i < firstHalfArray.length; i++) {
                firstHalfArray[i] = firstHalfArray[i] = (float) (firstHalfArray[i] * Math.sin(0.2f + i / 5.0) *
                        Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0));
            }
        });



       Thread secondThread =  new Thread(() -> {
            for (int i = 0, j = HALF; i < secondHalfArray.length; i++, j++) {
                secondHalfArray[i] = secondHalfArray[i] = (float) (secondHalfArray[i] * Math.sin(0.2f + j/ 5.0) *
                        Math.cos(0.2f + j / 5.0) * Math.cos(0.4f + j / 2.0));
            }
        });

       firstThread.start();
       secondThread.start();

        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.arraycopy(firstHalfArray, 0, arr, 0, HALF);
        System.arraycopy(secondHalfArray, 0, arr, HALF, HALF);
        System.out.println(System.currentTimeMillis() - a);
    }


}
