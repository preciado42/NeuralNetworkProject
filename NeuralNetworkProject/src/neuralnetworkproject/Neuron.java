package neuralnetworkproject;
import java.util.*;

/**
 * Created by Colby on 4/13/14.
 */
public class Neuron {

    private static int counter = 0;
    public final int ID;
    Connection biasConnection;
    private final double bias = -1;
    private double output;

    ArrayList<Connection> inConnections = new ArrayList<Connection>();
    HashMap<Integer, Connection> connections = new HashMap<Integer, Connection>();

    public Neuron()
    {
        ID = counter;
        counter++;
    }

    public void calculateOutput()
    {
        double sum = 0.0;
        for(Connection con : inConnections)
        {
            Neuron leftNeuron = con.getLeftNeuron();
            double weight = con.getWeight();
            double all = leftNeuron.getOutput(); //output from previous later

            sum = sum + (weight*all);
        }

        sum = sum + (biasConnection.getWeight()*bias);

        output = sigmoid(sum);
    }

    private double sigmoid(double in)
    {
        return 1.0/ (1.0 + (Math.exp(-in)));
    }

    public void addInConnections(ArrayList<Neuron> inNeurons)
    {
        for(Neuron n : inNeurons)
        {
            Connection con = new Connection(n, this);
            inConnections.add(con);
            connections.put(n.ID, con);
        }
    }

    public Connection getConnection(int neuronIndex)
    {
        return connections.get(neuronIndex);
    }

    public void addInConnection(Connection con)
    {
        inConnections.add(con);
    }

    public void addBiasConnection(Neuron n)
    {
        Connection con = new Connection(n, this);
        biasConnection = con;
        inConnections.add(con);
    }

    public ArrayList<Connection> getAllInConnections()
    {
        return inConnections;
    }

    public double getBias()
    {
        return bias;
    }

    public double getOutput()
    {
        return output;
    }

    public void setOutput(double o)
    {
        output = o;
    }
}
