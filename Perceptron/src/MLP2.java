import java.io.BufferedWriter;
import java.io.FileWriter;


public class MLP2 {
	private int hiddenlyaerNumber;
	private int inputNumber;
	private int outputNumber;
	private int pairNumber;
	private double[][] v;//weight for hidden layer
	private double[][] w;// weight for output
	private double alpha;
	private double maxError;
	private int k;//present count
	private int p;//max count 
	private double error[];//present error
	private double[][] x;
	private double[][] d;
	private double[] z;
	private double[] y;
	private double[] signalY;
	private double[] signalZ;
	
	MLP2(){
		hiddenlyaerNumber = 2;
		inputNumber = 2;
		pairNumber = 4;
		outputNumber = 2;
		k = 0;
		p = pairNumber;
		alpha = 0.01;
		maxError = 0.02;
		error = new double[outputNumber];
		signalY = new double[outputNumber];
		signalZ = new double[hiddenlyaerNumber];
		//initialize weight
		v = new double[hiddenlyaerNumber][inputNumber+1];
		w = new double[outputNumber][hiddenlyaerNumber+1];
		
		for(int j=0;j<hiddenlyaerNumber;j++){		
			for(int i=0;i<=inputNumber;i++){
				if(i==inputNumber){
					v[j][i] = -Math.random();
				}else{
					v[j][i] = Math.random();
				}
			}
			
		}
		for(int j=0;j<outputNumber;j++){
			for(int i=0;i<=hiddenlyaerNumber;i++){
				if(i==hiddenlyaerNumber){
					w[j][i] = -Math.random();
				}else{
					w[j][i] = Math.random();
				}
				
			}
		}
		/*
		for(int i=0;i<outputNumber;i++){
			if(i%2==0){
				double[] temp4 = {0.8, -0.8 ,-0.2};
				w[i] = temp4;
			}else{
				double[] temp4 = {-0.8, 0.8 ,-0.2};
				w[i] = temp4;
			}
		}
		
		
		for(int j=0;j<hiddenlyaerNumber;j++){
			if(j%2==0){
				double[] temp4 = {0.8, -0.8 ,-0.2};
				v[j] = temp4;
			}else{
				double[] temp4 = {-0.8, 0.8 ,-0.2};
				v[j] = temp4;
			}
		}*/
		z = new double[hiddenlyaerNumber+1];
		y = new double[outputNumber+1];
		//initialize x and d
		double[][] temp1 = {{0,0,1},{0,1,1},{1,0,1},{1,1,1}};//pairNumber and output
		x = temp1;
		double[][] temp2 = {{1,0},{0,1},{0,1},{1,0}};
		d = temp2;
	}
	
	public MLP2(int hiddenlyaerNumber, double aplha){
		this.hiddenlyaerNumber = hiddenlyaerNumber;
		inputNumber = 2;
		pairNumber = 4;
		outputNumber = 2;
		k = 0;
		p = pairNumber;
		this.alpha = aplha;
		maxError = 0.01;
		error = new double[outputNumber];
		signalY = new double[outputNumber];
		signalZ = new double[hiddenlyaerNumber];
		//initialize weight
		v = new double[hiddenlyaerNumber][inputNumber+1];
		w = new double[outputNumber][hiddenlyaerNumber+1];

		for(int j=0;j<hiddenlyaerNumber;j++){		
			for(int i=0;i<=inputNumber;i++){
				//if(i==inputNumber){
				//	v[j][i] = -Math.random();
				//}else{
					v[j][i] = Math.random();
				//}
			}
			
		}
		for(int j=0;j<outputNumber;j++){
			for(int i=0;i<=hiddenlyaerNumber;i++){
				//if(i==hiddenlyaerNumber){
			//		w[j][i] = -Math.random();
			//	}else{
					w[j][i] = Math.random();
			//	}
				
			}
		}
		/*
		for(int i=0;i<outputNumber;i++){
			if(i%2==0){
				double[] temp4 = {0.8, -0.8 ,-0.2};
				w[i] = temp4;
			}else{
				double[] temp4 = {-0.8, 0.8 ,-0.2};
				w[i] = temp4;
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
		y = new double[outputNumber+1];
		//initialize x and d
		double[][] temp1 = {{0,0,1},{0,1,1},{1,0,1},{1,1,1}};//pairNumber and output
		x = temp1;
		double[][] temp2 = {{1,0},{0,1},{0,1},{1,0}};
		d = temp2;
	}
	
	public void excution(){
		learning();
		k = 0;
		while(k<pairNumber){
			computeOutputForHiddenlayer(k);
			computeOutputForOutput();
			System.out.println("output : " + y[0] + " " + y[1]);
			k++;
		}
	}
	private void learning(){
		//try{
			String tempString = null;	
			
		//	FileWriter fw = new FileWriter(".//alpha-" + String.valueOf(alpha)+"hiddenlayer-" +String.valueOf(hiddenlyaerNumber) + " result.txt");
		//	FileWriter fw = new FileWriter(".//result.txt");
		//	BufferedWriter bw = new BufferedWriter(fw);
		//	alpha = 0.5;
		//	hiddenlyaerNumber = 2;
		//	while(hiddenlyaerNumber<=5){
			int count = 0;
			
			System.out.println("alpha : " + alpha + " hiddenlayerNumber : " + hiddenlyaerNumber);
		//	tempString = "alpha : " + String.valueOf(alpha) + " hiddenlayerNumber : " + String.valueOf(hiddenlyaerNumber);
		//	bw.write(tempString);
			//bw.newLine();
					
			System.out.println("count : " + count + " error : " + error);
			/*	tempString = "count : " + String.valueOf(count) + "error : " + String.valueOf(error);
			
			bw.write(tempString);
			bw.newLine();
		*/
			System.out.println("weight w");
		//	tempString = "weight w";
		//	bw.write(tempString);
		//	bw.newLine();

			for(int j=0;j<outputNumber;j++){
				for(int i=0;i<hiddenlyaerNumber+1;i++){
					System.out.print(w[j][i] + " ");
				//	tempString = String.valueOf(w[j][i]) + " ";
				//	bw.write(tempString);			
				}
				System.out.println();
				//bw.newLine();
			}
			
			System.out.println("weight v");
			//tempString = "weight v";
			//bw.write(tempString);
			//bw.newLine();
			
			for(int i=0;i<hiddenlyaerNumber;i++){
				for(int j=0;j<inputNumber+1;j++){
					System.out.print(v[i][j] + " ");
				//	tempString = String.valueOf(v[i][j]) + " ";
				//	bw.write(tempString);		
				}
				System.out.println();
				//bw.newLine();
			}
			//System.out.println();
		//	bw.newLine();
			
			while(true){
				//System.out.println("start learning");			
				while(k!=p){
				//	System.out.println("k : " + k);
					computeOutputForHiddenlayer(k);
					computeOutputForOutput();
					computeOutputError(k);
					computeErrorSignal(k);
					updateWeightForV(k);
					updateWeightForW();
					k++;
				}
				System.out.println(error[0] + " " + error[1]);
				if(error[0]<maxError && error[1]<maxError && count > 1)
					break;
					
				error[0] = 0;
				error[1] = 0;
				k = 0;
			//	System.out.print("again ");
				count++;
			}
		
			// System.out.println("finish learning");
		//	System.out.println("count : " + count + " error : " + error);
			//tempString = "count : " + String.valueOf(count) + " error : " + String.valueOf(error);
			
		//	bw.write(tempString);
		//	bw.newLine();
			
			System.out.println("weight w");
			//tempString = "weight w";
			//bw.write(tempString);
			//bw.newLine();
			for(int j=0;j<outputNumber;j++){
				for(int i=0;i<hiddenlyaerNumber+1;i++){
					System.out.print(w[j][i] + " ");
				//	tempString = String.valueOf(w[i]) + " ";
				//	bw.write(tempString);			
				}
				System.out.println();
				//bw.newLine();
			}
			System.out.println("weight v");
		//tempString = "weight v";
		//	bw.write(tempString);
			//bw.newLine();
			for(int i=0;i<hiddenlyaerNumber;i++){
				for(int j=0;j<inputNumber+1;j++){
					System.out.print(v[i][j] + " ");
					tempString = String.valueOf(v[i][j]) + " ";
				//	bw.write(tempString);		
				}
				System.out.println();
				//bw.newLine();
			}
			System.out.println();
			//bw.newLine();

			/*hiddenlyaerNumber += 1;
			for(int i=0;i<=hiddenlyaerNumber;i++){
				if(i==hiddenlyaerNumber){
					w[i] = -0.2;
				}else if(i%2==0){
					
					w[i] = 0.8;
				}else{
					w[i] = -0.8;
				}
			}
			double[] temp4 = {0.8, -0.8 ,-0.2};
			v = temp4;
			
			}
			bw.close();
			fw.close();		
		}catch(Exception e){
			e.printStackTrace();
		}
			*/
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
		for(int j=0;j<outputNumber;j++){
			for(int i=0;i<hiddenlyaerNumber+1;i++){
				netY += z[i] * w[j][i]; 
			}
			
		//compute unipolar sigmoid function for Y
			y[j] = 1 / ( 1 + Math.exp(-netY)); 
	//	System.out.println("output : " + y);
			netY = 0;
		}
		
	}
	
	private void computeOutputError(int k){
		for(int z=0;z<outputNumber;z++)
			error[z] = 0.5 * (d[k][z] - y[z]) * (d[k][z] - y[z]) + error[z];
				
//		System.out.println("k : " + k + " error : " + error);
	}
	
	private void computeErrorSignal(int k){

		double tempSum = 0;
		for(int i=0;i<hiddenlyaerNumber;i++){
			signalZ[i] = 0;
		}
		for(int l=0;l<outputNumber;l++){
			signalY[l] = (d[k][l] - y[l])* y[l] *(1 - y[l]);
		
			for(int i=0;i<hiddenlyaerNumber;i++){
				for(int j=0;j<inputNumber+1;j++){
					tempSum += signalY[l] * w[l][j];
				}
				signalZ[i] += z[i]*(1-z[i])*tempSum;
	
				tempSum = 0;
			}
		}
/*		}
		System.out.println("signal Y : " + signalY);
		
		for(int i=0;i<hiddenlyaerNumber;i++){
			System.out.println("signal Z : " + signalZ[i]);	
		}
*/
	}
	
	private void updateWeightForW(){
		for(int j=0;j<outputNumber;j++){
			for(int i=0;i<hiddenlyaerNumber+1;i++){
				w[j][i] = w[j][i] + alpha * signalY[j] * z[i];
			}
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
