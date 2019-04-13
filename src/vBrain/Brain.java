package vBrain;


import java.util.*;

    /// <summary>
    /// Contém toda a estrutura do sistema nervoso do organismo
    /// </summary>
    public class Brain
    {
        String ID;
        public List<Lobe> lobes;
		private boolean working = false;
		Thread brainThread;
        ///Statistics
		/*
        public List<List<String>> processors;
        public Dictionary<String, Integer> lobesProcessorCount;
        public Dictionary<String, List<Double[]>> lobesProcessorOutputs;
        public Dictionary<String, List<Double>> lobesProcessorErrors;
		*/

        public static Random random = new Random();

        /// <summary>
        /// Create a brain without lobes
        /// </summary>
        /// <param name="_lobes"></param>
        public Brain()
        {
            ID = UUID.randomUUID().toString();
            //processors = new ArrayList<List<String>>();
            //lobesProcessorCount = new Hashtable <String, Integer>();
            //lobesProcessorOutputs = new Hashtable<String, List<double[]>>();
            //lobesProcessorErrors = new Hashtable<String, List<double>>();
            lobes = new ArrayList<Lobe>();
        }

        /// <summary>
        /// Create a brain with lobes
        /// </summary>
        /// <param name="_lobes"></param>
        public Brain(Lobe[] lobes)
        {
            ID = UUID.randomUUID().toString();
            this.lobes = new ArrayList<Lobe>();
			for(Lobe lobe: lobes){
				this.lobes.add(lobe);
			}
        }

        public void insertLobes( Lobe[] lobes)
        {
			for(Lobe lobe: lobes){
				this.lobes.add(lobe);
			}
        }


        public void work()
        {
			working = true;
			brainThread = new Thread(new Runnable(){
				public void run(){
					while(working){
						for (int i = 0; i < lobes.size(); i++)
						{
							boolean worked = lobes.get(i).Work();
						}
					}
				}
			});
            brainThread.start();
        }
		
		public void stopWork(){
			working = false;
		}

        public void learnLobe(String lobeName, DataTemplate[] templates, int numEpochs)
        {
            getLobeFromName(lobeName).network.Train(templates, numEpochs);
        }

        public void learnLobe(String lobeName, DataTemplate[] templates, double minimunError)
        {
            getLobeFromName(lobeName).network.Train(templates, minimunError);
        }

        public Lobe getLobeFromName(String name)
        {
            Lobe returnLobe = null;
            for (Lobe lobe : lobes)
            {
                if (lobe.name == name)
                {
                    returnLobe = lobe;
                }
            }
            return returnLobe;
        }

        public Lobe getLobeFromID(String id)
        {
            Lobe returnLobe = null;
            for (Lobe lobe : lobes)
            {
                if (lobe.getID() == id)
                {
                    returnLobe = lobe;
                }
            }
            return returnLobe;
        }
    }

