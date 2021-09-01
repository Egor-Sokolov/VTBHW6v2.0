package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        method1();
        method2();
    }

    public static void method1() {
        final int SIZE = 10_000_000;
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
        final int SIZE = 10_000_000;
        final int HALF = SIZE / 2;
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        float[] a1 = new float[SIZE];
        float[] a2 = new float[SIZE];
        System.arraycopy(arr, 0, a1, 0, HALF);
        System.arraycopy(arr, HALF, a2, 0, HALF);

        new Thread(() -> {
            for (int i = 0; i < a1.length; i++) {
                a1[i] = a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5.0) *
                        Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0));
            }
        }).start();


        new Thread(() -> {
            for (int i = 0; i < a2.length; i++) {
                a2[i] = a2[i] = (float) (a2[i] * Math.sin(0.2f + i / 5.0) *
                        Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0));
            }
        }).start();


        System.arraycopy(a1, 0, arr, 0, HALF);
        System.arraycopy(a2, 0, arr, HALF, HALF);


        System.out.println(System.currentTimeMillis() - a);
    }


}
