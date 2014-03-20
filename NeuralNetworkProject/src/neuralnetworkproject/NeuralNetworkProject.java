/*
 * An implementation of a Neural Network with the objective of teaching
 * the network to recognize patterns in images.
 *  
 *  Bio Computing
 *  March 4th, 2014
 */
package neuralnetworkproject;

import java.util.ArrayList;

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
        //networkCreationTest();
        //networkFirstTest();
        this.trainNetworkSquare();
    }

    public void networkFirstTest() {
        // Network network = new Network();
        testPairLoad("\\NeuralNetworkProject\\src\\images");
        network = new Network(1, 2, 1, 3);
        network.feed(this.testPairs.get(0).getDataArrayList(), this.testPairs.get(0).getAnswer());
        network.answer();
        System.out.println("Answer provided by Network: " + network.answer());
        System.out.println("Correct Answer: " + this.testPairs.get(0).getAnswer());
        network.calculateError();
        network.backProp();
        //next test;
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

    /**
     * This method will train the network using square2.png which is the image of
     * a big square int he middle of an 8x8 grid.  This will be its only true answer
     * and a random assortment of other images will be tested against this one image.
     */
    public void trainNetworkSquare(){
        
        //begin training of network
        int sessions = 1; //number of training sessions to run
        this.network = this.setUpNetwork(); //set up layers, random weights, etc
        this.testPairLoad("src\\training\\simpleSquare");  //load up all test cases, we know which pngs in this list are not squares
        for (int i = 0; i < sessions; i++) {
            System.out.println("Training Session Number: "+i);
            //test against a set number of pngs that ARENT squares
            for (int j = 0; j < this.testPairs.size(); j++) {
                this.network.feed(this.testPairs.get(j).getDataArrayList(), this.testPairs.get(j).getAnswer());
                //check if network got the right answer
                int ans = network.answer();
                if(ans == 1 && !this.testPairs.get(j).getAnswer()){
                    //network guessed true, answer is false
                    System.out.println("Network answered " + ans);
                    System.out.println("Correct answer was " + this.testPairs.get(j).getAnswer() + "\n");
                    this.network.calculateError();
                    this.network.backProp();
                } else if(ans == 0 && this.testPairs.get(j).getAnswer()){
                    //network guessed false, answer is true
                    System.out.println("Network answered " + ans);
                    System.out.println("Correct answer was " + this.testPairs.get(j).getAnswer() + "\n");
                    this.network.calculateError();
                    this.network.backProp();
                }
            }
        }
    }

    private Network setUpNetwork() {
        System.out.println("Network is Set up");
        return new Network(1,2,1,3);
    }
    
    private void printWeights(){
        
    }
    
}
