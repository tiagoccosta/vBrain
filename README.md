# vBrain
Simple library to create virtual neural networks.


---------------
## Install

Just copy "vBrain" folder in your java project scripts folder.

---------------
## How To Use

Create an instance of a Brain, set Lobes with network and put the brain to work.


* __Brain Creation__
```java
//Simple way to create a brain
Brain brain = new Brain();
```


* __Lobe Creation__
```java
//Create lobe
Lobe lobe1 = new Lobe("Lobe1", network, sense, action);

//Insert lobe on brain
brain.insertLobes(new Lobe[]{lobe1});

//Train lobe 
brain.learnLobe("Lobe1",templates,errorMin);
```


* __Sense Types__
```java
ISense sense = new SimpleSense();

ISense sense = new AcumulativeSense(dataCount);
```


* __Brain work functions__
```java
//Put brain thread to work
brain.startWork();
		
//Stop brain work thread
brain.stopWork();

//Set data on lobe to calcule
brain.setDataOnLobeWithName("LobeName",data);
```


* __Network Create__
```java
new Network(inputLayerSize, hiddenLayersSizes, outputLayerSize, netFunction);
		
new Network(inputLayerSize, hiddenLayersSizes, outputLayerSize, netFunction, learnRate);

new Network(inputLayerSize, hiddenLayersSizes, outputLayerSize, netFunction, learnRate, momentum);
```


* __NetFunction Types__
```java
//Sigmoid is the only function created at the moment
INetFunction sense = new Sigmoid();
```


* __Use Lobe Target Configuration__
```java
Lobe lobe1 = new Lobe("Lobe1", new Network(2, new int[]{6,4},1,new Sigmoid()), new SimpleSense());
Lobe lobe2 = new Lobe("Lobe2", new Network(2, new int[]{6,4},1,new Sigmoid()), new SimpleSense());
Lobe lobe3 = new Lobe(
	"Lobe3",
	new Network(2,new int[]{6,4},1,new Sigmoid()),
	new AcumulativeSense(2),
	new Action(){
		public void Invoke(ActionEvent e){
			double[] result = (double[])e.data;
			System.out.println("Output: "+result[0]);
		}
	}
);
		
//Insert lobe target
lobe1.insertTargets(new Lobe[]{lobe3});
lobe2.insertTargets(new Lobe[]{lobe3});
		
//Insert lobes to brain
brain.insertLobes(new Lobe[]{lobe1,lobe2,lobe3});
```
