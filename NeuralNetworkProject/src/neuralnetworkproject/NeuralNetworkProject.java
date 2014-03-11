/*
 * An implementation of a Neural Network with the objective of teaching
 * the network to recognize patterns in images.
 *  
 *  Bio Computing
 *  March 4th, 2014
 */
package neuralnetworkproject;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Angel Preciado, Colby Sinnock, Alex Mohr
 */
public class NeuralNetworkProject {

    ArrayList<TestPair> testPairs;
    
    public NeuralNetworkProject(){
        testPairs = new ArrayList<TestPair>();
    }
    
    public void run(){
        //networkCreationTest();
        networkFirstTest();
    }

    public void networkFirstTest(){
       // Network network = new Network();
        testPairLoadTest();
        Network network = new Network(1, 2, 1, 3);
        network.feed(this.testPairs.get(0).getDataArrayList());
        System.out.println("Answer provided by Network: "+network.answer());
        System.out.println("Correct Answer: "+this.testPairs.get(0).getAnswer());
        //System.out.println(network.toString());
    }
    
    private void networkCreationTest() {
        //#input neurons, #hidden layers, #output neurons, #hidden neurons per layer
        Network network = new Network(1, 2, 1, 3);
    }

     /**
     * Code used for loading images begins here, all for testing still.
     */
    private void loadImagesTest() {
        //used for testing the image importer classes.
        ImageLoader il = new ImageLoader();
        //8 = 8x8 pixel images, 4 images total
        int[][] imgData = il.loadAllImages(8, 4);

        //
        //System.out.println("imgdata.length = " + imgData.length);
        //System.out.println("imgdata[0].length = " + imgData[0].length);

        /*for (int z = 0; z < 4; z++) {
            for (int i = 0; i < imgData[z].length; i++) {
                System.out.print((imgData[z][i]) + " ");
                if ((i + 1) % 8 == 0) {
                    System.out.println("");
                }

            }
            System.out.println("\n-------------------\n");
        }*/
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NeuralNetworkProject nnp = new NeuralNetworkProject();
        nnp.run();
    }

    private void testPairLoadTest() {
        ImageLoader il = new ImageLoader();
        this.testPairs = il.createTestPairs();
        /*for (int i = 0; i < testPairs.size(); i++) {
            TestPair temp = testPairs.get(i);
            double[] dat = temp.getDataAsDouble();
            for (int j = 0; j < dat.length; j++) {
                System.out.print(dat[j]+" ");
                if((j+1)%8==0){
                    System.out.println();
                }
            }
            System.out.println("Test Pair Answer: "+temp.getAnswer());
            System.out.println("-----------------------");
        }*/
    }

}
