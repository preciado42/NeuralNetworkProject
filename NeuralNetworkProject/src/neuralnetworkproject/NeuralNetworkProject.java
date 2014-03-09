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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
        for(int i = 0; i < 5; i++)
        {
            array.add(1.0);
        }
        System.out.println("");
        //#input neurons, #hidden layers, #output neurons, #hidden neurons per layer, input array
        Network network = new Network(1, 2, 1, 3, array);
        System.out.println(network.answer());
        System.out.println(network.toString());
    }
    
}
