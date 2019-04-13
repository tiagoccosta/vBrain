package vBrain;
import java.util.*;


    public class SimpleSense implements ISense
    {
        private boolean analisate = false;
        //private double[] data;
		private List<double[]> dataList = new ArrayList<double[]>();

        public void setData(double[] data)
        {
			synchronized(dataList){
				dataList.add(data);
			}
            //data = _data;
            analisate = true;
        }

        public double[] getData(Network network)
        {
			synchronized(dataList){
				double[] data= dataList.get(0);
				dataList.remove(0);
				if(dataList.size()==0){
            		analisate = false;
				}
            	return data;
			}
        }

        public boolean toAnalisate()
        {
            return analisate;
        }
    }

