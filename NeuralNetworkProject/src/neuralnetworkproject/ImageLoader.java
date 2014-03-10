/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetworkproject;

import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author Room
 */
public class ImageLoader extends PApplet{
    private PImage test;

    @Override
    public void setup() {
        test = new PImage();
        System.out.println("calling run!!");
    }
    
    public void run(){
        test = loadImage("src\\images\\notsquare1.png");
        ImageProcessor ip = new ImageProcessor(test);
        int[] ar = ip.process();
        for (int i = 0; i < ar.length; i++) {
            System.out.print((ar[i]) + " ");
            if ((i + 1) % 8 == 0) {
                System.out.println("");
            }
        }
    }
    
}
