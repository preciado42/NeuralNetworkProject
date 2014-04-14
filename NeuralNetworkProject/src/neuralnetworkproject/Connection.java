/**
 * Created by Colby on 4/13/14.
 */
package neuralnetworkproject;
public class Connection {

    private double weight = 0.0;
    private double prevChangeInWeight = 0.0;
    private double changeInWeight = 0.0;

    private Neuron leftNeuron;
    private Neuron rightNeuron;
    private static int counter = 0;
    public final int ID;

    public Connection(Neuron lNeuron, Neuron rNeuron)
    {
        leftNeuron = lNeuron;
        rightNeuron = rNeuron;
        ID = counter;
        counter++;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double newWeight)
    {
        weight = newWeight;
    }

    public void setCHangeInWeight(double change)
    {
        prevChangeInWeight = changeInWeight;
        changeInWeight = change;
    }

    public double getPrevChangeInWeight()
    {
        return prevChangeInWeight;
    }

    public Neuron getLeftNeuron()
    {
        return leftNeuron;
    }

    public Neuron getRightNeuron()
    {
        return rightNeuron;
    }
}
