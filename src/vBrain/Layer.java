package vBrain;
import java.util.*;

public class Layer
{
	public Neuron[] neurons;
	public INetFunction function;
	
	public Layer(int neuronsCount, INetFunction function){
		this.function = function;
		neurons = new Neuron[neuronsCount];
		for(int i = 0; i< neuronsCount; i++){
			neurons[i]=(new Neuron());
		}
	}
	public Layer(int neuronsCount, Layer inputLayer, INetFunction function){
		this.function = function;
		neurons = new Neuron[neuronsCount];
		for(int i = 0; i< neuronsCount; i++){
			neurons[i]=(new Neuron(inputLayer.neurons));
		}
	}
}
