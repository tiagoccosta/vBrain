
import vBrain.*;public class CompleteUse
{
	public static void main(String[] args)
	{
		System.out.println("Test Brain Library");

		Brain brain = new Brain();

		Lobe lobe1 = new Lobe(
			"Lobe1",
			new Network(2,new int[]{6,4},1,new Sigmoid()),
			new SimpleSense()
		);
		Lobe lobe2 = new Lobe(
			"Lobe2",
			new Network(2,new int[]{6,4},1,new Sigmoid()),
			new SimpleSense()
		);
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

		//Training lobes
		brain.learnLobe("Lobe1",getTemplates(),0.02);
		brain.learnLobe("Lobe2",getTemplates(),0.02);
		brain.learnLobe("Lobe3",getTemplates(),0.02);

		brain.startWork();

		System.out.println("Calculing 1 | 0");
		brain.setDataOnLobeWithName("Lobe1",new double[]{1,0});
		System.out.println("Calculing 1 | 1");
		brain.setDataOnLobeWithName("Lobe2",new double[]{0,0});

	}

	public static  DataTemplate[] getTemplates(){
		return new DataTemplate[]{
			new DataTemplate(
				new double[]{1,0},
				new double[]{0}
			),
			new DataTemplate(
				new double[]{0,1},
				new double[]{0}
			),
			new DataTemplate(
				new double[]{1,1},
				new double[]{1}
			),
			new DataTemplate(
				new double[]{0,0},
				new double[]{1}
			)
		};
	}
}
