/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetworkproject;

import java.util.ArrayList;

/**
 * @author Colby
 */
public class Network {

    private ArrayList<ArrayList<Neuron>> levelsList;
    private int expectedOutput;
    private final int inputNeurons, hiddenLayers, outputNeurons, hiddenNeurons;
    private double learningRate, momentum;

    public Network(int inputNeurons, int hiddenLayers, int outputNeurons, int hiddenNeurons) {
        this.inputNeurons = inputNeurons;
        this.hiddenLayers = hiddenLayers;
        this.outputNeurons = outputNeurons;
        this.hiddenNeurons = hiddenNeurons;
        this.learningRate = 1.0;
        this.momentum = 1.0;
        //noinspection unchecked,unchecked
        levelsList = new ArrayList();
        for (int i = 0; i < hiddenLayers + 2; i++) {
            levelsList.add(new ArrayList<Neuron>());
        }
        initalizeLayers();
    }

    public void feed(ArrayList<Integer> inputs, boolean eoutput) {
        if (eoutput) {
            expectedOutput = 1;
        } else {
            expectedOutput = 0;
        }
        cleanNeurons();
        //populate input neurons with new inputs.
        for (int j = 0; j < levelsList.get(0).size(); j++) {
            for (int i = 0; i < inputs.size(); i++) {
                levelsList.get(0).get(j).insertInput(inputs.get(i) + 0.0); //hack this to double...
            }
        }
    }

    public void cleanNeurons()
    {
        for(int i = 0; i < levelsList.size(); i++)
        {
            for(int j = 0; j < levelsList.get(i).size(); j++)
            {
                levelsList.get(i).get(j).clearInputs();
            }
        }
    }

    public void calculateError() {
        //Calculate error for each output node
        for (int i = 0; i < levelsList.get(levelsList.size() - 1).size(); i++) {
            levelsList.get(levelsList.size() - 1).get(i).calculateSignalError(expectedOutput);
        }

        //Calculate error for each hidden layer node
        for (int i = levelsList.size() - 2; i > 0; i--) {//layer
            for (int j = 0; j < levelsList.get(i).size(); j++) {//node
                double sum = 0.0;

                for (int k = 0; k < levelsList.get(i + 1).size(); k++) {
                    sum = sum + levelsList.get(i + 1).get(k).getWeight(j) * levelsList.get(i + 1).get(k).getError();
                }

                levelsList.get(i).get(j).setSignalError(levelsList.get(i).get(j).getError() * (1 - levelsList.get(i).get(j).getError()) * sum);
            }
        }
    }

    public void backProp() {
        //Update weights
        for (int i = levelsList.size() - 1; i > 0; i--) {
            for (int j = 0; j < levelsList.get(i).size(); j++) {
                Neuron current = levelsList.get(i).get(j);

                current.setThresholdDiff((current.getThresholdDiff()
                        * momentum + current.getError()) * learningRate);


                current.setThreshold(current.getThreshold() + current.getThresholdDiff());

                //update weights
                for (int k = 0; k < levelsList.get(i - 1).size(); k++) {
                    Neuron guyBefore = levelsList.get(i - 1).get(k);

                    //calculate weight different from j to k
                    current.setWeightDiff(k, learningRate * current.getError() * guyBefore.output() +
                            momentum * current.getWeightDiff(k));

                    //update weight from j to k
                    current.setWeight(k, current.getWeight(k) + current.getWeightDiff(k));
                }
            }
        }
    }

    private void initalizeLayers() {
        //create all the neurons in the network
        for (int i = 0; i < inputNeurons; i++) {
            levelsList.get(0).add(new Neuron());
        }
        for (int i = 1; i < hiddenLayers + 1; i++) {
            for (int j = 0; j < hiddenNeurons; j++) {
                levelsList.get(i).add(new Neuron());
            }
        }
        for (int i = 0; i < outputNeurons; i++) {
            levelsList.get(levelsList.size() - 1).add(new Neuron());
        }

        //assign weights
        //populate hidden layer neurons
        for (int i = 1; i < hiddenLayers + 1; i++) {
            for (int j = 0; j < hiddenNeurons; j++) {
                for (int k = 0; k < levelsList.get(i - 1).size(); k++) {
                    levelsList.get(i).get(j).populateInput(levelsList.get(i - 1).get(k));
                }
            }
        }

        for (int i = 1; i < hiddenLayers + 1; i++) {
            for (int j = 0; j < hiddenNeurons; j++) {
                    levelsList.get(i).get(j).initalizeWeights();
            }
        }

        //populate output neurons input list
        for(int i = 0; i < outputNeurons; i++)//number of hidden neurons in layer below output.
        {
            for(int j = 0; j < levelsList.get(levelsList.size()-2).size(); j++)//number of output neurons
            {
                Neuron current = levelsList.get(levelsList.size()-1).get(i);
                current.populateInput(levelsList.get(levelsList.size()-2).get(j));
            }
        }

        //populate output layer neurons weights
        for(int i = 0; i < outputNeurons; i++)
        {
            levelsList.get(levelsList.size()-1).get(i).initalizeWeights();
        }

    }

    public void fireNeurons()
    {
        for(int i = 0; i < levelsList.size(); i++)
        {
            for(int j = 0; j < levelsList.get(i).size(); j++)
            {
                levelsList.get(i).get(j).activate();
            }
        }
    }

    public int answer() {
        fireNeurons();
        double sum = 0.0;
        for (int i = 0; i < levelsList.get(levelsList.size() - 1).size(); i++) {
            sum = sum + levelsList.get(levelsList.size() - 1).get(i).output();
        }
        sum = sum / levelsList.get(levelsList.size() - 1).size();
        System.out.println(sum);
        if (sum >= 0.5) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder bldr = new StringBuilder();
        bldr.append("Input Layer:\n");
        for (int i = 0; i < levelsList.get(0).size(); i++) {
            bldr.append(levelsList.get(0).get(i).toString());
        }
        bldr.append("Hidden Layers:\n");
        for (int i = 1; i < hiddenLayers + 1; i++) {
            bldr.append("Layer #: ").append(i).append("\n");
            for (int j = 0; j < hiddenNeurons; j++) {
                bldr.append(levelsList.get(i).get(j).toString());
            }
        }
        bldr.append("Output Layer:\n");
        for (int i = 0; i < outputNeurons; i++) {
            bldr.append(levelsList.get(levelsList.size() - 1).get(i).toString());
        }
        return bldr.toString();
    }
}
