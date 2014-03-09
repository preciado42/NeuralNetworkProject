/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetworkproject;

import java.util.ArrayList;

/**
 *
 * @author Vikish
 */
public class Network {
    
    private ArrayList<ArrayList<Neuron>> levelsList;
    private final int inputNeurons, hiddenLayers, outputNeurons, hiddenNeurons;
    
    public Network(int inputNeurons, int hiddenLayers, int outputNeurons, int hiddenNeurons, ArrayList<Double> inputs)
    {
        this.inputNeurons = inputNeurons;
        this.hiddenLayers = hiddenLayers;
        this.outputNeurons = outputNeurons;
        this.hiddenNeurons = hiddenNeurons;
        levelsList = new ArrayList();
        for(int i = 0; i < hiddenLayers+2; i++)
        {
            levelsList.add(new ArrayList<Neuron>());
        }
        initalizeLayers(inputs);
    }

    private void initalizeLayers(ArrayList<Double> inputs) 
    {
        //creat all the neurons in the network
        for(int i = 0; i < inputNeurons; i++)
        {
            levelsList.get(0).add(new Neuron());
        }
        for(int i = 1; i < hiddenLayers+1; i++)
        {
            for(int j = 0; j < hiddenNeurons; j++)
            {
                levelsList.get(i).add(new Neuron());
            }
        }
        for(int i = 0; i < outputNeurons; i++)
        {
            levelsList.get(levelsList.size()-1).add(new Neuron());
        }
        
        //populate input neurons
        for(int j = 0; j < levelsList.get(0).size(); j++)
        {
            for(int i = 0; i < inputs.size(); i++)
            {
                levelsList.get(0).get(j).insertInput(inputs.get(i));
            }
            for(int i = 0; i < levelsList.get(1).size(); i ++)
            {
                levelsList.get(0).get(j).populateOutput(levelsList.get(1).get(i), Math.random());
            }
        }
        
        //populate hidden layer neurons
        for(int i = 1; i < hiddenLayers+1; i++)
        {
            for(int j = 0; j < hiddenNeurons; j++)
            {
                for(int k = 0; k < levelsList.get(i+1).size(); k++)
                {
                    levelsList.get(i).get(j).populateOutput(levelsList.get(i+1).get(k), Math.random());
                }
            }
        }
        
        //populate output layer neurons
        //nothing to do really, the neurons exist but won't have weights for anything.
        
    }
   
    public int answer()
    {
        double sum = 0.0;
        for(int i = 0; i < levelsList.get(levelsList.size()-1).size(); i++)
        {
            sum = sum + levelsList.get(levelsList.size()-1).get(i).output();
        }
        sum = sum / levelsList.get(levelsList.size()-1).size();
        if(sum >= 0.5)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    
    @Override
    public String toString()
    {
        StringBuilder bldr = new StringBuilder();
        bldr.append("Input Layer:\n");
        for(int i = 0; i < levelsList.get(0).size(); i++)
        {
            bldr.append(levelsList.get(0).get(i).toString());
        }
        bldr.append("Hidden Layers:\n");
        for(int i = 1; i < hiddenLayers+1; i++)
        {
            bldr.append("Layer #: ").append(i).append("\n");
            for(int j = 0; j < hiddenNeurons; j++)
            {
                bldr.append(levelsList.get(i).get(j).toString());
            }
        }
        bldr.append("Output Layer:\n");
        for(int i = 0; i < outputNeurons; i++)
        {
            bldr.append(levelsList.get(levelsList.size()-1).get(i).toString());
        }
        return bldr.toString();
    }
}
