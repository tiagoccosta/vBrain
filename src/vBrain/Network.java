package vBrain;


import java.util.*;

    public class Network
    {
        private String ID;
		public String getID(){return ID;}
        public double learnRate; //{ get; set; }
        public double momentum; //{ get; set; }
		public List<Layer> layers = new ArrayList<>();

	public Network(int inputSize, int[] hiddenLayersSizes, int outputSize){
		Initialize(inputSize, hiddenLayersSizes, outputSize, null, 0, 0);
		}
	public Network(int inputSize, int[] hiddenLayersSizes, int outputSize, INetFunction netFunction){
		Initialize(inputSize, hiddenLayersSizes, outputSize, netFunction, 0, 0);
	}
	public Network(int inputSize, int[] hiddenLayersSizes, int outputSize, INetFunction netFunction, double learnRate){
		Initialize(inputSize, hiddenLayersSizes, outputSize, netFunction, learnRate, 0);
	}
	public Network(int inputSize, int[] hiddenLayersSizes, int outputSize, INetFunction netFunction, double learnRate, double momentum){
		Initialize(inputSize, hiddenLayersSizes, outputSize, netFunction, learnRate, momentum);
	}
        void Initialize(int inputSize, int[] hiddenLayersSizes, int outputSize, INetFunction netFunction, double learnRate, double momentum)
        {
            this.ID = UUID.randomUUID().toString();
            netFunction = netFunction == null ? new Sigmoid() : netFunction;
			this.learnRate = learnRate==0 ? .4 : learnRate;
            this.momentum = momentum == 0 ? .9 : momentum;
            
			this.layers.add(new Layer(inputSize,netFunction));
			for (int i = 0; i < hiddenLayersSizes.length; i++){
				this.layers.add( new Layer(hiddenLayersSizes[i],layers.get(i),netFunction));
            }
			this.layers.add(new Layer(outputSize,layers.get(layers.size() - 1),netFunction));
			
        }

		
        public void Train(DataTemplate[] templates, int epochs)
        {
            for (int i = 0; i < epochs; i++)
            {
                for (DataTemplate template : templates)
                {
                    frdProp(template.inputs);
                    backProp(template.outputs);
                }
            }
        }
		

		
        public void Train(DataTemplate[] templates, double minError)
        {
            double error = 1.0;
            int epochs = 0;

            while (error > minError && epochs < Integer.MAX_VALUE)
            {
                List<Double> errors = new ArrayList<Double>();
                for (DataTemplate template : templates)
                {
                    frdProp(template.inputs);
                    backProp(template.outputs);
                    errors.add(calculeError(template.outputs));
                }
				error = 0;
				for(double er : errors){
					error+=er;
				}
                error /= errors.size();
                epochs++;
            }
        }
		

        private void frdProp(double[] inputs)
        {
            int i = 0;
			for (Neuron neuron: layers.get(0).neurons){
				neuron.value = inputs[i++];
			}
			for(int l = 1; l < layers.size(); l++){
				Layer layer = layers.get(l);
				for(int neuronIndex = 0; neuronIndex< layer.neurons.length; neuronIndex++){
					Neuron neuron = layer.neurons[neuronIndex];
					double sum = 0;
					for(int synapseIndex = 0 ; synapseIndex < neuron.weights.length; synapseIndex++){
						sum +=(neuron.weights[synapseIndex][0] * layers.get(l-1).neurons[synapseIndex].value) + neuron.bias[0];
					}
					neuron. value = layer.function.output( sum);
				}
			}
        }

        private void backProp(double[] outputs)
        {
            int i = 0;
			Layer outputLayer = layers.get(layers.size()-1);
			for(Neuron neuron : outputLayer.neurons){
				neuron.gradient = (outputs[i++]-neuron.value) * outputLayer.function.derivative(neuron.value);
			}
			for(int layerIndex =layers.size()-2; layerIndex>0; layerIndex--){
				Layer layer= layers.get(layerIndex);
				Layer inputNeuronLayer = null;
				if(layerIndex>0){inputNeuronLayer = layers.get(layerIndex-1);}
				Layer outputNeuronLayer = null;
				if(layerIndex<=layers.size()-2){outputNeuronLayer = layers.get(layerIndex+1);}
				for(int n = 0; n<layer.neurons.length; n++){
					Neuron neuron= layer.neurons[n];
					double sum = 0;

					for(int outputNeuronIndex =0; outputNeuronIndex < outputNeuronLayer.neurons.length; outputNeuronIndex++){
						Neuron outputNeuron = outputNeuronLayer.neurons[outputNeuronIndex];
						sum +=(outputNeuron.gradient * outputNeuron.weights[n][0])* layer.function.derivative(neuron.value);
					}
					neuron.gradient = sum;
				}
				for(Neuron neuron : layer.neurons){
					
					refreshNeuronWeights(neuron, inputNeuronLayer);
				}
			}

			Layer inputNeuronLayer_outputLayer= layers.get(layers.size()-2);
			for(Neuron neuron : outputLayer.neurons){ // layers.get(layers.size()-1).neurons){
				refreshNeuronWeights(neuron, inputNeuronLayer_outputLayer);
			}
        }
		
		private void refreshNeuronWeights(Neuron neuron, Layer inputNeuronLayer){
			double prevDelta = neuron.bias[1];
            neuron.bias[1] = this.learnRate * neuron.gradient;
            neuron.bias[0] += neuron.bias[1] + this.momentum * prevDelta;

			for(int i = 0 ; i < inputNeuronLayer.neurons.length; i++)
            {
				prevDelta = neuron.weights[i][1];
				neuron.weights[i][1] = this.learnRate * neuron.gradient * inputNeuronLayer.neurons[i].value;

                neuron.weights[i][0] += neuron.weights[i][1] + this.momentum * prevDelta;
            }
		}

        public double[] compute(double[] inputs)
        {
            frdProp(inputs);
			double[] results = new double[layers.get(layers.size()-1).neurons.length];
			int count = 0;
			for(Neuron neuron : layers.get(layers.size()-1).neurons){
				results[count]= neuron.value;
				count++;
			}
			double[] arr = new double[count];
			for(int i=0; i<count;i++){
				arr[i] = results[i];
			}
			return arr;
        }

		
        private double calculeError(double[] outputs)
        {
            int i = 0;
			double sum = 0;
			for(Neuron neuron: layers.get(layers.size()-1).neurons){
				sum += Math.abs((float)(outputs[i++] - neuron.value));
			}
			return sum;
        }
		
		
		

        public static double getRandom()
        {
            return 2 * Brain.random.nextDouble() - 1;
        }
    }

