package neuralnetworkproject;
import java.util.*;

/**
 * Created by Colby on 4/13/14.
 */
public class Network {

    private final Random rand = new Random();
    private final ArrayList<ArrayList<Neuron>> levelsList;
    private final Neuron bias = new Neuron();
    private final int randomWeightMultiplier = 1;

    //number of input neurons, number of hidden layers, number of hidden neurons, number of output neruons
    private int input, hidden, hiddenNeurons, output;

    private double learningRate = 0.4;
    private double momentum = 0.7;

    private HashMap<String, Double> weightUpdate = new HashMap<String, Double>();

    public Network(int input, int hidden, int hiddenNeurons, int output)
    {
        this.input = input;
        this.hidden = hidden;
        this.hiddenNeurons = hiddenNeurons;
        this.output = output;
        
        levelsList = new ArrayList<ArrayList<Neuron>>();
        for (int i = 0; i < hidden+2; i++) {
            levelsList.add(new ArrayList<Neuron>());
        }

        createNeurons();
        initalizeWeights();
    }

    private void createNeurons()
    {
        for(int i = 0; i < 2+hidden; i++)
        {
            if(i == 0)//input layer
            {
                for(int j = 0; j < input; j++)
                {
                    levelsList.get(i).add(new Neuron());
                }
            }
            else if(i == 1+hidden)//output layer
            {
                for(int j = 0; j < output; j++)
                {
                    Neuron neuron = new Neuron();
                    neuron.addInConnections(levelsList.get(i-1));
                    neuron.addBiasConnection(bias);
                    levelsList.get(i).add(neuron);
                }
            }
            else
            {
                for(int j = 0; j < hiddenNeurons; j++)
                {
                    Neuron neuron = new Neuron();
                    neuron.addInConnections(levelsList.get(i-1));
                    neuron.addBiasConnection(bias);
                    levelsList.get(i).add(neuron);
                }
            }
        }
    }

    private void initalizeWeights()
    {
        //set weights for hidden layers
        for(int i = 1; i < hidden+1; i++)
        {
            for(Neuron neuron : levelsList.get(i))
            {
                ArrayList<Connection> connections = neuron.getAllInConnections();
                for(Connection con : connections)
                {
                    double newWeight = getRandom();
                    con.setWeight(newWeight);
                }
            }
        }

        //set weights for output layer
        for(Neuron neuron : levelsList.get(hidden+1))
        {
            ArrayList<Connection> connections = neuron.getAllInConnections();
            for(Connection con : connections)
            {
                double newWeight = getRandom();
                con.setWeight(newWeight);
            }
        }
    }

    private double getRandom()
    {
        return randomWeightMultiplier * (rand.nextDouble() * 2 - 1); //between -1, inclusive and 1 exclusive.
    }

    //feed, same # of input neurons as there are inputs
    public void setInput(double inputs[])
    {
        for(int i = 0; i < input; i++)
        {
            levelsList.get(0).get(i).setOutput(inputs[i]);
        }
    }

    public double[] getOutput()
    {
        double[] outputs = new double[output];
        for(int i = 0; i < output; i++)
        {
            outputs[i] = levelsList.get(hidden+1).get(i).getOutput();
        }
        return outputs;
    }

    //activates all the neruons
    public void activate()
    {
        for(int i = 1; i < hidden+1; i++)
        {
            for(Neuron n : levelsList.get(i))
            {
                n.calculateOutput();
            }
        }
        for(Neuron n : levelsList.get(hidden+1))
        {
            n.calculateOutput();
        }
    }

    public void backPropagate(int expectedOuput)
    {
        //calculate the partial derivative of the error with respect to each of the weights leading into the output neurons
        for(Neuron n : levelsList.get(hidden+1))
        {
            ArrayList<Connection> connections = n.getAllInConnections();
            for(Connection con : connections)
            {
                double ak = n.getOutput();
                double ai = con.getLeftNeuron().getOutput();
                double desiredOuput = expectedOuput;
                double partialDerivative = -ak * (1 - ak) * ai
                       * (desiredOuput - ak);
                double changeWeight = -learningRate * partialDerivative;
                double newWeight = con.getWeight() + changeWeight;
                con.setCHangeInWeight(changeWeight);
                con.setWeight(newWeight + momentum * con.getPrevChangeInWeight());
             }
        }

        //update weights for the hidden layers
        for(int i = 1; i < hidden+1; i++)
        {
            for(Neuron n : levelsList.get(i))
            {
                ArrayList<Connection> connections = n.getAllInConnections();
                for(Connection con : connections)
                {
                    double aj = n.getOutput();
                    double ai = con.getLeftNeuron().getOutput();
                    double sumKoutputs = 0;
                    for(Neuron out_neu : levelsList.get(i+1))
                    {
                        double wjk = out_neu.getConnection(n.ID).getWeight();
                        double desiredOutput = (double) expectedOuput;
                        double ak = out_neu.getOutput();

                        sumKoutputs = sumKoutputs + (-(desiredOutput - ak) * ak * (1 - ak) * wjk);
                    }

                    double partialDerivative = aj * (1 - aj) * ai * sumKoutputs;
                    double changeWeight = -learningRate * partialDerivative;
                    double newWeight = con.getWeight() + changeWeight;
                    con.setCHangeInWeight(changeWeight);
                    con.setWeight(newWeight + momentum * con.getPrevChangeInWeight());
                }
            }
        }
    }
}
