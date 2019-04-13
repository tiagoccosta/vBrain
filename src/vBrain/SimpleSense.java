package vBrain;


    public class SimpleSense implements ISense
    {
        private boolean analisate = false;
        private double[] data;

        public void setData(double[] _data)
        {
            data = _data;
            analisate = true;
        }

        public double[] getData(Network network)
        {
            analisate = false;
            return data;
        }

        public boolean toAnalisate()
        {
            return analisate;
        }
    }

