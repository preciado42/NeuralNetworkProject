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
        networkCreationTest();
        testPairLoadTest();
    }

    private void networkCreationTest() {
        ArrayList<Double> array = new ArrayList();
        Random rand = new Random();
        //four inputs for testing
        System.out.println("Inputs");
//        for(int i = 0; i < 5; i++)
//        {
//            double dub = rand.nextInt(2) + 0.0;
//            System.out.println(dub);
//            array.add(dub);
//        }
        for (int i = 0; i < 5; i++) {
            array.add(1.0);
        }
        System.out.println("");
        //#input neurons, #hidden layers, #output neurons, #hidden neurons per layer, input array
        Network network = new Network(1, 2, 1, 3, array);
        System.out.println(network.answer());
        System.out.println(network.toString());
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
        System.out.println("imgdata.length = " + imgData.length);
        System.out.println("imgdata[0].length = " + imgData[0].length);

        for (int z = 0; z < 4; z++) {
            for (int i = 0; i < imgData[z].length; i++) {
                System.out.print((imgData[z][i]) + " ");
                if ((i + 1) % 8 == 0) {
                    System.out.println("");
                }

            }
            System.out.println("\n-------------------\n");
        }
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
        for (int i = 0; i < testPairs.size(); i++) {
            TestPair temp = testPairs.get(i);
            int[] dat = temp.getImageData();
            for (int j = 0; j < dat.length; j++) {
                System.out.print(dat[j]+" ");
                if((j+1)%8==0){
                    System.out.println();
                }
            }
            System.out.println("Test Pair Answer: "+temp.getAnswer());
            System.out.println("-----------------------");
        }
    }

}
