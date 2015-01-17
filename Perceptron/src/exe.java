
public class exe {
	public static void main(String[] args){
		int hiddenlayerNumber = 3;
		double alpha = 0.3;
		
		MLP mlp = new MLP(hiddenlayerNumber, alpha);
			
		mlp.excution();
	
//		MLP mlp = new MLP(hiddenlayerNumber, alpha);
//		mlp.excution();
	}
}
