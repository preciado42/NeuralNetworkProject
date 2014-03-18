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
 * @author Alex
 */
public class ImageProcessor extends PApplet {
    PImage image;
    int black = color(0, 0, 0);
    int white = color(255, 255, 255);

    public ImageProcessor(PImage img){
        this.image = img;
    }
    
    public void imageProcessor() {
        image = createImage(8, 8, RGB);
        image.loadPixels();
        for (int i = 0; i < image.pixels.length; i++) {
            if (i % 4 == 0) {
                pixels[i] = black;
            } else {
                pixels[i] = white;
            }
        }
        image.updatePixels();
    }

    int[] process() {
        PImage thisImage = this.image;
        int[] ar = new int[thisImage.pixels.length];
        thisImage.loadPixels();
        for (int i = 0; i < thisImage.pixels.length; i++) {
            if (thisImage.pixels[i] == black) {
                ar[i] = 1;
            } else {
                ar[i] = 0;
            }
        }
        //updatePixels();
        return ar;
    }
}
