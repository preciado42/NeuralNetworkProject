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
 * @author Angel Preciado, Colby Sinnock, Alex Mohr
 */
public class NeuralNetworkProject {

    Network network;
    ArrayList<TestPair> testPairs;

    public NeuralNetworkProject() {
        testPairs = new ArrayList<TestPair>();
    }

    public void run() {
        this.trainNewNetwork();
    }

//    public void networkFirstTest() {
//        // Network network = new Network();
//        testPairLoad("\\NeuralNetworkProject\\src\\images");
//        network = new Network(1, 2, 1, 3);
//        network.feed(this.testPairs.get(0).getDataArrayList(), this.testPairs.get(0).getAnswer());
//        network.answer();
//        System.out.println("Answer provided by Network: " + network.answer());
//        System.out.println("Correct Answer: " + this.testPairs.get(0).getAnswer());
//        network.calculateError();
//        network.backProp();
//        //next test;
//    }
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

    private void testPairLoad(String path) {
        ImageLoader il = new ImageLoader();
        this.testPairs = il.createTestPairs(path);
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

    private Network setUpNetwork() {
        System.out.println("Network is Set up");
        return new Network(64, 2, 3, 1);
    }

    private void reorderTestPairs() {
        Random r = new Random();
        int range = this.testPairs.size();
        for (int i = 0; i < 250; i++) {
            int first = r.nextInt(range);
            int second = r.nextInt(range);
            TestPair temp = this.testPairs.get(first);
            this.testPairs.get(first).setAnswer(this.testPairs.get(second).getAnswer());
            this.testPairs.get(first).setImageData(this.testPairs.get(second).getImageData());
            this.testPairs.get(second).setAnswer(temp.getAnswer());
            this.testPairs.get(second).setImageData(temp.getImageData());
        }
    }

    public void trainNewNetwork() {
        //setup network
        this.network = this.setUpNetwork();

        //load images as test pairs
        this.testPairLoad("C:\\Users\\Room\\Desktop\\nueralNet\\netGit\\NeuralNetworkProject\\src\\images");

        //loop through tests
        for (int z = 0; z < 1000; z++) {
            for (int i = 0; i < this.testPairs.size(); i++) {
                //feed image
                this.network.setInput(this.testPairs.get(i).getDataAsDouble());
                //activate nuerons
                this.network.activate();
                double[] o = this.network.getOutput();
                for (int j = 0; j < o.length; j++) {
                    System.out.println("output: " + o[j]);
                }
                System.out.println("answer = " + this.testPairs.get(i).getAnswer());
                //backprop
                this.network.backPropagate(this.testPairs.get(i).getAnswerNumber());

            }
        }
        
        System.out.println("test network");
        this.network.setInput(this.testPairs.get(0).getDataAsDouble());
        System.out.println("net answered: "+this.network.getOutput()[0]);
        System.out.println("actual answer:"+this.testPairs.get(0).getAnswer());
    }

}
