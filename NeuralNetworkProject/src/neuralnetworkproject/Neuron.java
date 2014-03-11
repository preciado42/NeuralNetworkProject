/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetworkproject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Vikish
 */
public class Neuron {

    private HashMap<Neuron,Double> outputNeurons;
    private ArrayList<Double> inputs;
    private double output;
    private double bias;
    
    public Neuron()
    {
        outputNeurons = new HashMap();
        inputs = new ArrayList();
    }
    
    public void populateOutput(Neuron neuron, Double weight)
    {
        outputNeurons.put(neuron, weight);
    }
    
    public boolean insertInput(Double input)
    {
        return inputs.add(input);
    }

    public void setBias(Double dub)
    {
        bias = dub;
    }
    
    public boolean pushOut()
    {
        boolean allTrue = true;
        
        for(Neuron neuron : outputNeurons.keySet())
        {
            if(!neuron.insertInput(outputNeurons.get(neuron) * output))
            {
                allTrue = false;
            }
        }
        return allTrue;
    }
    
    public void activate()
    {
        //implement activation function here, could be many different kinds
        /*double sum = 0.0;
        for(int i = 0; i < inputs.size(); i++)
        {
            sum = sum + inputs.get(i);
        }
        sum = sum / inputs.size();
        if(sum >= 0.5)
        {
            output = 1;
        }
        else
        {
            output = 0;
        }
        pushOut();*/
        double sum = 0.0;
        for(int i = 0; i < inputs.size(); i++)
        {
            sum = sum + inputs.get(i);
        }
        sum = sum + bias;
        output = (1.0/(1.0 + Math.pow(Math.E,sum)));
        pushOut();
    }
    
    //should only be called to get the answer from output neurons
    public double output()
    {
        activate();
        return output;
    }
    
    @Override
    public String toString()
    {
        StringBuilder bldr = new StringBuilder();
        bldr.append("Inputs:\n");
        for(int i =0 ; i < inputs.size(); i++)
        {
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
