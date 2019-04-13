package vBrain;


import java.util.*;


    public class AcumulativeSense implements ISense
    {
        private boolean analisate = false;
        private double[] data;
        private int dataCount;

        public AcumulativeSense(int dataCount)
        {
            this.dataCount = dataCount;
            this.data = new double[0];
        }

        public void setData(double[] data)
        {
			
            double[] tempList = new double[this.data.length+data.length];// this.Data.ToList();
			int i=0;
			for(double val : this.data){
				tempList[i]=val;
				i++;
			}
			for(double val : data){
				tempList[i]=val;
				i++;
			}
            //tempList.AddRange(data);
			
            if (!(tempList.length > dataCount))
            {
				this.data = new double[tempList.length];
				this.data = Arrays.copyOf( tempList,tempList.length);
				if(this.data.length==dataCount){
                	analisate = true;
				}
            }else{
				this.data = Arrays.copyOfRange(tempList,0,dataCount-1);
			}
        }

        public double[] getData(Network network)
        {
            analisate = false;
            double[] returnData = this.data;
            this.data = new double[0];
            return returnData;
        }

        public boolean toAnalisate()
        {
            return analisate;
        }
    }

