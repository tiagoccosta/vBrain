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


* __Sense Types__
```java
Sense sense = new SimpleSense();

Sense sense = new AcumulativeSense(dataCount);
```

