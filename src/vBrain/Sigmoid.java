package vBrain;


import java.util.*;
import javax.xml.validation.*;


    public class Sigmoid implements INetFunction
    {
        double alpha = 2.0;

		public double getAlpha()
		{
			return this.alpha;
		}
		
		private void setAlpha (double value)
		{
			this.alpha = value;
		}
		
        public Sigmoid() { }
        public Sigmoid(double alpha) { setAlpha(alpha); }
        public double output(double x)
        {
			x = Math.min(x,45);
			x = Math.max(x,-45);
            return 1.0 / (1.0 + Math.exp(-this.alpha * x));
        }
		
        public double derivative(double value)
        {
            return (1 - value) * value;
        }
    }

