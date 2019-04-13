package vBrain;


import java.util.*;
import java.io.*;

    public class Neuron implements Serializable
	{
		public double value;
		public double[][] weights;
		public double gradient;
		public double[] bias;

        public Neuron()
        {
			weights = new double[0][0];
			bias = new double[2];
            bias[0] = Network.getRandom();
        }

        public Neuron(Neuron[] inputNeurons)
        {
			weights = new double[inputNeurons.length][2];
			bias = new double[2];
            for (int i = 0; i< inputNeurons.length; i++)
            {
				weights[i][0] = Network.getRandom();
            }
            bias[0] = Network.getRandom();
        }

    }

