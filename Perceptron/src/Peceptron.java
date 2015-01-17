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
		int totalTrue = 0;//추정이 맞은 개수
		int totalFalse = 0;//추정이 틀린 개수
		
		
		// 파일로부터 값을 읽어오기
		String path = Knnclassifier.class.getClassLoader().getResource(".").getPath();//클래스가 있는 경로 얻기
	//	System.out.println(path);
		String temp = null;//txt파일의 txt라인을 저장할 임시 스트링
		FileReader fr = null;		
		BufferedReader br = null;
		// train_set1.txt를 읽는다.
		try{
			fr = new FileReader(path+"\\train_set1.txt");
			br = new BufferedReader(fr);
			iForTest = 0;
			while((temp=br.readLine())!=null){			
				Scanner sc = new Scanner(temp).useDelimiter(",");//,로 토큰 분류
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
		//test_set1.txt를 읽는다.
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
		//퍼셉트론 학습
		
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
			//학습 집합에서 계산후 결과가 틀린 집합을 수집한다.
			for(int i=0;i<iForTrain;i++){
				 sum = w[0]*trainSet[i][0] + w[1]*trainSet[i][1] + w[2]*trainSet[i][2] + w[3]*trainSet[i][3] + b;
				 if(sum<=0){//추정이 세토사로 나왔을 때
					 if(trainSet[i][4] == -1){//추정이 맞으면
						 q++;
					 }else{//추정의 틀리면
					//	 System.out.println("틀린 번호 : " + i);
						 //w와 b값을 수정한다.
						 w[0] = w[0] + p * trainSet[i][4] * trainSet[i][0];
						 w[1] = w[1] + p * trainSet[i][4] * trainSet[i][1];
						 w[2] = w[2] + p * trainSet[i][4] * trainSet[i][2];
						 w[3] = w[3] + p * trainSet[i][4] * trainSet[i][3];
						 
						 b = b + p * trainSet[i][4];
				//		 System.out.println("b : " + b);
				//		 System.out.println("w : " + w[0] + " " + w[1] + " " + w[2] + " " + w[3]);
					 }
				 }else{//추정이 베시컬로로 나왔을 때
					 if(trainSet[i][4] == 1){
						 q++;
					 }else{//추정의 틀리면
				//		 System.out.println("틀린 번호 : " + i);
						 //w와 b값을 수정한다.
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
		//	 System.out.println("계산 끝");
			//정인실률 Q를 구한다.
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
		
		//테스트 집합을 이용한 퍼셉트론 실행
		
		for(int i=0;i<iForTrain;i++){
			 sum = w[0]*testSet[i][0] + w[1]*testSet[i][1] + w[2]*testSet[i][2] + w[3]*testSet[i][3] + b;
			 if(sum<=0){//추정이 세토사로 나왔을 때
				 if(testSet[i][4] == -1){//추정이 맞으면
					totalTrue++;
				 }else{//추정의 틀리면
					totalFalse++;
				 }
			 }else{//추정이 베시컬로로 나왔을 때
				 if(testSet[i][4] == 1){
					totalTrue++;
				 }else{//추정의 틀리면
					totalFalse++;		
				 }
			 }
		}
		
		
		System.out.println("totalTrue : " + totalTrue);
		System.out.println("totalFalse : " + totalFalse); 
		
		
	}
}
