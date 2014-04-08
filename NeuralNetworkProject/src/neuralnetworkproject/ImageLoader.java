/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetworkproject;

import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Alex
 */
public class ImageLoader extends PApplet {

    private PImage img;

    @Override
    public void setup() {
        img = new PImage();
        //System.out.println("calling run!!");
    }

    public void run() {
        img = loadImage("src\\images\\notsquare1.png");
        ImageProcessor ip = new ImageProcessor(img);
        int[] ar = ip.process();
        for (int i = 0; i < ar.length; i++) {
            System.out.print((ar[i]) + " ");
            if ((i + 1) % 8 == 0) {
                System.out.println("");
            }
        }
    }

    public int[][] loadAllImages(int dataSize, int dataNum) {
        int[][] imageData = new int[dataNum][dataSize];
        File dataDir = new File("src\\images");
        File[] list = dataDir.listFiles();
        System.out.println("size of list:" + list.length);
        for (int i = 0; i < list.length; i++) {
            img = loadImage(list[i].getAbsolutePath());
            ImageProcessor ip = new ImageProcessor(img);
            int[] ar = ip.process();
            imageData[i] = ar;
//            for (int z = 0; z < ar.length; z++) {
//                System.out.print((ar[z]) + " ");
//                if ((z + 1) % 8 == 0) {
//                    System.out.println("");
//                }
//            }
//            System.out.println("\n-------------------\n");
        }

        return imageData;
    }

    public ArrayList<TestPair> createTestPairs(String path) {
        ArrayList<TestPair> testData = new ArrayList<TestPair>();
        File dataDir = new File(path);
        File[] list = dataDir.listFiles();
        for (int i = 0; i < list.length-1; i++) {
            //load imge
            img = loadImage(list[i].getAbsolutePath());
            ImageProcessor ip = new ImageProcessor(img);
            int[] ar = ip.process();
            //get answer from file name
            boolean ans = true;
            if (list[i].getName().contains("not")) {
                ans = false;
            }
            //add test pair to array list
            testData.add(new TestPair(ar, ans));
        }
        return testData;
    }

    public TestPair loadSingleTestPair(String file) {
        TestPair testData = null;
        File dataDir = new File("src\\images");
        File[] list = dataDir.listFiles();
        boolean ans = false;
        int[] arr = new int[0];
        for (int i = 0; i < list.length; i++) {
            if (list[i].getName().equalsIgnoreCase(file)) {
                //load imge
                img = loadImage(list[i].getAbsolutePath());
                ImageProcessor ip = new ImageProcessor(img);
                arr = ip.process();
                //get answer from file name
                ans = true;
                if (list[i].getName().contains("not")) {
                    ans = false;
                }
            }
            //add test pair to array list
            testData = new TestPair(arr,ans);
        }
        return testData;
    }

}
