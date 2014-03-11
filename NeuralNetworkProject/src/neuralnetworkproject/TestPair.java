/*
 * Test pair class which will be used to store an image's data along with its answer.
 */

package neuralnetworkproject;

import java.util.ArrayList;

/**
 *
 * @author Angel Preciado
 */
public class TestPair {
    private int[] imageData;
    private boolean answer;
    
    public TestPair(int[] imgData, boolean ans){
        this.imageData = imgData;
        this.answer = ans;
    }
    
    public boolean getAnswer(){
        return this.answer;
    }
    public int[] getImageData(){
        return this.imageData;
    }
    public void setAnswer(boolean ans){
        this.answer = ans;
    }
    public void setImageData(int[] imgDat){
        this.imageData = imgDat;
    }
    public ArrayList<Double> getDataArrayList(){
        ArrayList<Double> dat = new ArrayList<Double>();
        for (int i = 0; i < this.imageData.length; i++) {
            dat.add(new Double(this.imageData[i]));
        }
        return dat;
    }
    public double[] getDataAsDouble(){
        double[] dat = new double[this.imageData.length];
        for (int i = 0; i < this.imageData.length; i++) {
            dat[i] = this.imageData[i];
        }
        return dat;
    }
}
