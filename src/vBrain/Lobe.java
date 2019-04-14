package vBrain;


import java.util.*;

    /// <summary>
    /// Estrutura contendo uma rede neural, um sensor, e informações sobre relações com outras redes
    /// </summary>
    public class Lobe
    {
        private String ID;
		public String getID(){return ID;}
        public String name;
        public Network network;
        public ISense sense;
        public List<Lobe> lobesTargets;
        public Action action;
		private boolean active;
        public boolean isAtive(){return active;}
        public double[] lastOutput;
        public double lastError;

        public Lobe(String name, Network network, ISense sense)
        {
            ID = UUID.randomUUID().toString(); // new Guid().ToString();
            this.name = name;
            this.sense = sense;
            this.network = network;
            action = null;
            lobesTargets = new ArrayList<Lobe>();
        }

        public Lobe(String name, Network network, ISense sense, Action action)
        {
            ID = UUID.randomUUID().toString(); //  new Guid().ToString();
            this.name = name;
            this.sense = sense;
            this.network = network;
            this.action = action;
            lobesTargets = new ArrayList<Lobe>();
        }

        public Lobe(String name, Network network, ISense sense, Action action, Lobe[] targets)
        {
            ID = UUID.randomUUID().toString(); //  new Guid().ToString();
            this.name = name;
            this.sense = sense;
            this.network = network;
            this.action = action;
            lobesTargets = new ArrayList<Lobe>(Arrays.asList(targets));
        }

        public void insertTargets(Lobe[] targets)
        {
            lobesTargets.addAll(Arrays.asList(targets));
        }

        public boolean work()
        {
            boolean worked = false;
            if (sense.toAnalisate())
            {
                active = true;
                worked = true;
                lastOutput = network.compute(sense.getData(network));
                for (Lobe lobeTarget : lobesTargets) {
                    lobeTarget.sense.setData(lastOutput);
                    lobeTarget.work();
                }
                if (action != null)
                    action.Invoke(new ActionEvent(this,lastOutput));
                active = false;
            }

            return worked;
        }
    }
