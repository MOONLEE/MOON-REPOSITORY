public class MLP {
	private int hiddenlyaerNumber;
	private int inputNumber;
	private int pairNumber;
	private double[][] v;//weight for hidden layer
	private double[] w;// weight for output
	private double alpha;
	private double maxError;
	private int k;//present count
	private int p;//max count 
	private double error;//present error
	private double[][] x;
	private double[] d;
	private double[] z;
	private double y;
	private double signalY;
	private double[] signalZ;
	
	MLP(){
		hiddenlyaerNumber = 2;
		inputNumber = 2;
		pairNumber = 4;
		k = 0;
		p = pairNumber;
		alpha = 0.01;
		maxError = 0.02;
		error = 0;
		signalY = 0;
		signalZ = new double[hiddenlyaerNumber];
		//initialize weight
		v = new double[hiddenlyaerNumber][inputNumber+1];
		w = new double[hiddenlyaerNumber+1];
		
		for(int j=0;j<hiddenlyaerNumber;j++){		
			for(int i=0;i<=inputNumber;i++){
				if(i==inputNumber){
					v[j][i] = -Math.random();
				}else{
					v[j][i] = Math.random();
				}
			}
			
		}
		for(int i=0;i<=hiddenlyaerNumber;i++){
			if(i==hiddenlyaerNumber){
				w[i] = -Math.random();
			}else{
				w[i] = Math.random();
			}
			
		}
		/*
		for(int i=0;i<=hiddenlyaerNumber;i++){
			if(i==hiddenlyaerNumber){
				w[i] = -0.2;
			}else if(i%2==0){
				
				w[i] = 0.8;
			}else{
				w[i] = -0.8;
			}
		}
		
		
		for(int i=0;i<hiddenlyaerNumber;i++){
			if(i%2==0){
				double[] temp4 = {0.8, -0.8 ,-0.2};
				v[i] = temp4;
			}else{
				double[] temp4 = {-0.8, 0.8 ,-0.2};
				v[i] = temp4;
			}
		}*/
		z = new double[hiddenlyaerNumber+1];
		y = 0;
		//initialize x and d
		double[][] temp1 = {{0,0,1},{0,1,1},{1,0,1},{1,1,1}};//pairNumber and output
		x = temp1;
		double[] temp2 = {0,1,1,0};
		d = temp2;
	}
	
	public MLP(int hiddenlyaerNumber, double aplha){
		this.hiddenlyaerNumber = hiddenlyaerNumber;
		inputNumber = 2;
		pairNumber = 4;
		k = 0;
		p = pairNumber;
		this.alpha = aplha;
		maxError = 0.01;
		error = 0;
		signalY = 0;
		signalZ = new double[hiddenlyaerNumber];
		//initialize weight
		v = new double[hiddenlyaerNumber][inputNumber+1];
		w = new double[hiddenlyaerNumber+1];
		
		for(int j=0;j<hiddenlyaerNumber;j++){		
			for(int i=0;i<=inputNumber;i++){
			//	if(i%2==0){
			//		v[j][i] = -Math.random();
			//	}else{
					v[j][i] = Math.random();
			//	}
			}
			
		}
		for(int i=0;i<=hiddenlyaerNumber;i++){
			//if(i%2==1){
			//	w[i] = -Math.random();
			//}else{
				w[i] = Math.random();
			//}
			
		}
		/*
		for(int i=0;i<=hiddenlyaerNumber;i++){
			if(i==hiddenlyaerNumber){
				w[i] = -0.2;
			}else if(i%2==0){
				
				w[i] = 0.8;
			}else{
				w[i] = -0.8;
			}
		}
		
		
		for(int i=0;i<hiddenlyaerNumber;i++){
			if(i%2==0){
				double[] temp4 = {0.8, -0.8 ,-0.2};
				v[i] = temp4;
			}else{
				double[] temp4 = {-0.8, 0.8 ,-0.2};
				v[i] = temp4;
			}
		}*/
		z = new double[hiddenlyaerNumber+1];
		y = 0;
		//initialize x and d
		double[][] temp1 = {{0,0,1},{0,1,1},{1,0,1},{1,1,1}};//pairNumber and output
		x = temp1;
		double[] temp2 = {0,1,1,0};
		d = temp2;
	}
	
	public void excution(){
		learning();
		k = 0;
		while(k<pairNumber){
			computeOutputForHiddenlayer(k);
			computeOutputForOutput();
			System.out.println("output : " + y);
			k++;
		}
	}
	private void learning(){
			double temp = alpha;
			int count = 0;
			
			System.out.println("alpha : " + alpha + " hiddenlayerNumber : " + hiddenlyaerNumber);					
			System.out.println("count : " + count + " error : " + error);					
			System.out.println("weight w");			
			for(int i=0;i<hiddenlyaerNumber+1;i++){
				System.out.print(w[i] + " ");
			}
			System.out.println();			
			
			System.out.println("weight v");

			for(int i=0;i<hiddenlyaerNumber;i++){
				for(int j=0;j<inputNumber+1;j++){
					System.out.print(v[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			//learning start!!
			while(true){					
				while(k!=p){				
					computeOutputForHiddenlayer(k);
					computeOutputForOutput();
					computeOutputError(k);
					computeErrorSignal(k);
					updateWeightForV(k);
					updateWeightForW();
					k++;
				}
			//	System.out.println(k + " " + error);
				if(error<maxError & count > 1)
					break;
				//학습실패 - 초기 웨이트 재설정
				if(count > 10000000){
					System.out.println(error);
					for(int j=0;j<hiddenlyaerNumber;j++){		
						for(int i=0;i<=inputNumber;i++){
						//	if(i%2==0){
						//		v[j][i] = -Math.random();
						//	}else{
								v[j][i] = Math.random();
						//	}
						}
						
					}
					for(int i=0;i<=hiddenlyaerNumber;i++){
						//if(i%2==1){
						//	w[i] = -Math.random();
						//}else{
							w[i] = Math.random();
						//}
						
					}
					System.out.println("XOR 학습 실패 ----  가중치 재설정");
					System.out.println("alpha : " + alpha + " hiddenlayerNumber : " + hiddenlyaerNumber);					
					System.out.println("count : " + count + " error : " + error);					
					System.out.println("weight w");			
					for(int i=0;i<hiddenlyaerNumber+1;i++){
						System.out.print(w[i] + " ");
					}
					System.out.println();			
					
					System.out.println("weight v");

					for(int i=0;i<hiddenlyaerNumber;i++){
						for(int j=0;j<inputNumber+1;j++){
							System.out.print(v[i][j] + " ");
						}
						System.out.println();
					}
					System.out.println();
					count = 0;
				}
				error = 0;
				k = 0;			
				count ++;
			}
			System.out.println("finish learning");			
			System.out.println("count : " + count + " error : " + error);			
			System.out.println("weight w");
			for(int i=0;i<=hiddenlyaerNumber;i++){
				System.out.print(w[i] + " ");	
			}
			System.out.println();
			
			System.out.println("weight v");
			for(int i=0;i<hiddenlyaerNumber;i++){
				for(int j=0;j<inputNumber+1;j++){
					System.out.print(v[i][j] + " ");	
				}
				System.out.println();
			}
			System.out.println();
	}
	private void computeOutputForHiddenlayer(int k){
		double netZ = 0;
		
		//compute netZ
		for(int i=0;i<hiddenlyaerNumber;i++){
			for(int j=0;j<inputNumber+1;j++){
				netZ += x[k][j] * v[i][j];
			}
			//compute unipolar sigmoid function for Z
			z[i] = 1 / ( 1 + Math.exp(-netZ));
			netZ = 0;
		}
		z[hiddenlyaerNumber] = 1;
			 
	}

	private void computeOutputForOutput(){
		double netY = 0;
		//compute netY
		for(int i=0;i<hiddenlyaerNumber+1;i++){
			netY += z[i] * w[i]; 
		}
		
		//compute unipolar sigmoid function for Y
		y = 1 / ( 1 + Math.exp(-netY)); 
	//	System.out.println("output : " + y);
	}
	
	private void computeOutputError(int k){
		error = 0.5 * (d[k] - y) * (d[k] - y) + error;
//		System.out.println("k : " + k + " error : " + error);
	}
	
	private void computeErrorSignal(int k){

		double tempSum = 0;
		
		signalY = (d[k] - y)* y *(1 - y);
		
		for(int i=0;i<hiddenlyaerNumber;i++){
			for(int j=0;j<inputNumber+1;j++){
				tempSum += signalY * w[j];
			}
			signalZ[i] = z[i]*(1-z[i])*tempSum;

			tempSum = 0;
		}
/*		
		System.out.println("signal Y : " + signalY);
		
		for(int i=0;i<hiddenlyaerNumber;i++){
			System.out.println("signal Z : " + signalZ[i]);	
		}
*/
	}
	
	private void updateWeightForW(){
		for(int i=0;i<hiddenlyaerNumber+1;i++){
			w[i] = w[i] + alpha * signalY * z[i];
		}
	}
	
	private void updateWeightForV(int k){		
		for(int i=0;i<hiddenlyaerNumber;i++){
			for(int j=0;j<inputNumber+1;j++){
				v[i][j] = v[i][j] + alpha * signalZ[i] * x[k][j];
			}
		}
	}
	
	
}
