/*
 * Test pair class which will be used to store an image's data along with its answer.
 */

package neuralnetworkproject;

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
}
