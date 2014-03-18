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
public class Neuron {

    private ArrayList<Neuron> outputNeurons;
    private ArrayList<Integer> inputs;
    private ArrayList<Double> weights;
    private ArrayList<Double> weightDiff;
    private int output;
    private double bias;
    private double signalError;
    private double thresholdDiff;
    private double threshold;

    public Neuron() {
        outputNeurons = new ArrayList<Neuron>();
        inputs = new ArrayList<Integer>();
        weights = new ArrayList<Double>();
        weightDiff = new ArrayList<Double>();
    }

    public void populateOutput(Neuron neuron) {
        outputNeurons.add(neuron);
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double thresh) {
        threshold = thresh;
    }

    public void calculateSignalError(Integer expectedOutput) {
        signalError = (expectedOutput - output) * output * (1 - output);
    }

    public void initalizeWeights() {
        System.out.println("input size:"+inputs.size());
        for (int i = 0; i < inputs.size(); i++) {
            weights.add(Math.random());
        }
    }

    public void setWeightDiff(int pos, double diff) {
        weightDiff.set(pos, diff);
    }

    public void setWeight(int pos, double wei) {
        weights.set(pos, wei);
    }

    public double getWeightDiff(Integer pos) {
        return weightDiff.get(pos);
    }

    public double getWeight(Integer pos) {
        return weights.get(pos);
    }

    public double getError() {
        return signalError;
    }

    public void setSignalError(double sig) {
        signalError = sig;
    }

    public boolean insertInput(Integer input) {
        return inputs.add(input);
    }

    public void setBias(Double dub) {
        bias = dub;
    }

    public boolean pushOut() {
        boolean allTrue = true;

        for (Neuron neuron : outputNeurons) {
            if (!neuron.insertInput(output)) {
                allTrue = false;
            }
        }
        return allTrue;
    }

    public void setThresholdDiff(double diff) {
        thresholdDiff = diff;
    }

    public double getThresholdDiff() {
        return thresholdDiff;
    }

    public void activate() {
        //implement activation function here, could be many different kinds
        double sum = 0.0;
        double temp;
        for (int i = 0; i < inputs.size(); i++) {
            sum = sum + inputs.get(i);
        }
        sum = sum + bias;
        temp = (1.0 / (1.0 + Math.pow(Math.E, sum)));
        if (temp >= .5) {
            output = 1;
        } else {
            output = 0;
        }
        pushOut();
    }

    //should only be called to get the answer from output neurons
    public double output() {
        activate();
        return output;
    }

    @Override
    public String toString() {
        StringBuilder bldr = new StringBuilder();
        bldr.append("Inputs:\n");
        for (int i = 0; i < inputs.size(); i++) {
            bldr.append(inputs.get(i));
            bldr.append("\n");
        }
        bldr.append("Output:\n");
        activate();
        bldr.append(output);
        bldr.append("\n");
        return bldr.toString();
    }
}
