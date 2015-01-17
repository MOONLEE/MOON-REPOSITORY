import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;


public class Peceptron {
	public static void main(String[] args){
		int colums = 5;
		int rows = 256;
		double[][] trainSet = new double[rows][colums];
		double[][] testSet = new double[rows][colums];
		int iForTest = 0;
		int jForTest = 0;
		int iForTrain = 0;
		int jForTrain = 0;
		int totalTrue = 0;//������ ���� ����
		int totalFalse = 0;//������ Ʋ�� ����
		
		
		// ���Ϸκ��� ���� �о����
		String path = Knnclassifier.class.getClassLoader().getResource(".").getPath();//Ŭ������ �ִ� ��� ���
	//	System.out.println(path);
		String temp = null;//txt������ txt������ ������ �ӽ� ��Ʈ��
		FileReader fr = null;		
		BufferedReader br = null;
		// train_set1.txt�� �д´�.
		try{
			fr = new FileReader(path+"\\train_set1.txt");
			br = new BufferedReader(fr);
			iForTest = 0;
			while((temp=br.readLine())!=null){			
				Scanner sc = new Scanner(temp).useDelimiter(",");//,�� ��ū �з�
				jForTest = 0;
				while(sc.hasNext()){
					if(jForTest==4){
						String tempSpices = sc.next();
						if(tempSpices.equals("Iris-setosa")){
							trainSet[iForTest][jForTest] = -1;	
						//	System.out.println(trainSet[iForTest][0]+" "+trainSet[iForTest][1]+" "+trainSet[iForTest][2]+" "+trainSet[iForTest][3]+" "+trainSet[iForTest][4]);
						}else if(tempSpices.equals("Iris-versicolor")){
							trainSet[iForTest][jForTest] = 1;
						}
					}else{
						
						trainSet[iForTest][jForTest] = Double.parseDouble(sc.next());
					}
					jForTest++;							
				}
				iForTest++;
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
		//test_set1.txt�� �д´�.
		try{			
			fr = new FileReader(path+"\\test_set1.txt");
			br = new BufferedReader(fr);
			
			while((temp=br.readLine())!=null){
				Scanner sc = new Scanner(temp).useDelimiter(",");	
				jForTrain = 0;
				while(sc.hasNext()){
					if(jForTrain==4){
						String tempSpices = sc.next();
						if(tempSpices.equals("Iris-setosa")){
							testSet[iForTrain][jForTrain] = -1;		
						}else if(tempSpices.equals("Iris-versicolor")){
							testSet[iForTrain][jForTrain] = 1;
						}
					}else{
						testSet[iForTrain][jForTrain] = Double.parseDouble(sc.next());
					}
					jForTrain++;							
				}
				iForTrain++;
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
	/*	
		for(int i=0;i<iForTest;i++){
			System.out.println(testSet[i][0] + " " + testSet[i][1]+ " " + testSet[i][2]+ " " + testSet[i][3]+ " " + testSet[i][4]);
		}
		
		for(int i=0;i<iForTrain;i++){
			System.out.println(trainSet[i][0] + " " + trainSet[i][1]+ " " + trainSet[i][2]+ " " + trainSet[i][3]+ " " + trainSet[i][4]);
		}		
	*/	
		//�ۼ�Ʈ�� �н�
		
		double w[] = {11.2,6.2,-3.6,-9.1};
		double b = 0.753;
		int h = 0;
		int q = 0;
		double sum = 0;
		int bestQ = 0;
		double bestB = 0.753;
		double[] bestW =  {11.2,6.2,-3.6,-9.1};
		double p = 0.367;

		while(h<200){
			//�н� ���տ��� ����� ����� Ʋ�� ������ �����Ѵ�.
			for(int i=0;i<iForTrain;i++){
				 sum = w[0]*trainSet[i][0] + w[1]*trainSet[i][1] + w[2]*trainSet[i][2] + w[3]*trainSet[i][3] + b;
				 if(sum<=0){//������ ������ ������ ��
					 if(trainSet[i][4] == -1){//������ ������
						 q++;
					 }else{//������ Ʋ����
					//	 System.out.println("Ʋ�� ��ȣ : " + i);
						 //w�� b���� �����Ѵ�.
						 w[0] = w[0] + p * trainSet[i][4] * trainSet[i][0];
						 w[1] = w[1] + p * trainSet[i][4] * trainSet[i][1];
						 w[2] = w[2] + p * trainSet[i][4] * trainSet[i][2];
						 w[3] = w[3] + p * trainSet[i][4] * trainSet[i][3];
						 
						 b = b + p * trainSet[i][4];
				//		 System.out.println("b : " + b);
				//		 System.out.println("w : " + w[0] + " " + w[1] + " " + w[2] + " " + w[3]);
					 }
				 }else{//������ �����÷η� ������ ��
					 if(trainSet[i][4] == 1){
						 q++;
					 }else{//������ Ʋ����
				//		 System.out.println("Ʋ�� ��ȣ : " + i);
						 //w�� b���� �����Ѵ�.
						 w[0] = w[0] + p * trainSet[i][4] * trainSet[i][0];
						 w[1] = w[1] + p * trainSet[i][4] * trainSet[i][1];
						 w[2] = w[2] + p * trainSet[i][4] * trainSet[i][2];
						 w[3] = w[3] + p * trainSet[i][4] * trainSet[i][3];
						 
						 b = b + p * trainSet[i][4];
				//		 System.out.println("b : " + b);
				//		 System.out.println("w : " + w[0] + " " + w[1] + " " + w[2] + " " + w[3]);
					 }
				 }
				 
				
			}
		//	 System.out.println("��� ��");
			//���νǷ� Q�� ���Ѵ�.
			if(q>=bestQ){
				for(int j=0;j<4;j++){
					bestW[j] = w[j];
				}
				bestB = b;
				bestQ = q;				
			}
			
			h = h + 1;
			q = 0;
		}
		
		System.out.println("bestB : " + bestB);
		System.out.println("bestW : " + bestW[0] + " " + bestW[1] + " " + bestW[2] + " " + bestW[3]);
		
		//�׽�Ʈ ������ �̿��� �ۼ�Ʈ�� ����
		
		for(int i=0;i<iForTrain;i++){
			 sum = w[0]*testSet[i][0] + w[1]*testSet[i][1] + w[2]*testSet[i][2] + w[3]*testSet[i][3] + b;
			 if(sum<=0){//������ ������ ������ ��
				 if(testSet[i][4] == -1){//������ ������
					totalTrue++;
				 }else{//������ Ʋ����
					totalFalse++;
				 }
			 }else{//������ �����÷η� ������ ��
				 if(testSet[i][4] == 1){
					totalTrue++;
				 }else{//������ Ʋ����
					totalFalse++;		
				 }
			 }
		}
		
		
		System.out.println("totalTrue : " + totalTrue);
		System.out.println("totalFalse : " + totalFalse); 
		
		
	}
}
