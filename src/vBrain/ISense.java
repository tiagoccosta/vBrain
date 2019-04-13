package vBrain;



    /// <summary>
    /// Interface dos sentidos que fornecem valores utilizado pela rede neural do lóbulo
    /// </summary>
    public interface ISense
    {
        public boolean toAnalisate();
        public void setData(double[] input);
        public double[] getData(Network network);
    }

