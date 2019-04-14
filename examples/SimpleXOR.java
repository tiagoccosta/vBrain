
import vBrain.*;public class SimpleXOR
{
	public static void main(String[] args)
	{
		System.out.println("Test Brain Library");

		Brain brain = new Brain();

		Lobe lobe1 = new Lobe(
			"Lobe1",
			new Network(2,new int[]{6,4},1,new Sigmoid()),
			new SimpleSense(),
			new Action(){
				public void Invoke(ActionEvent e){
					double[] result = (double[])e.data;
					System.out.println("Output: "+result[0]);
				}
			}
		);

		brain.insertLobes(new Lobe[]{lobe1});
		System.out.println("Training Brain");
		brain.learnLobe("Lobe1",getTemplates(),0.02);
		System.out.println("Brain treined");
		brain.startWork();
		System.out.println("Calculing 1 | 0");
		brain.setDataOnLobeWithName("Lobe1",new double[]{1,0});
		System.out.println("Calculing 1 | 1");
		brain.setDataOnLobeWithName("Lobe1",new double[]{0,0});

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
