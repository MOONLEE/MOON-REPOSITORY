import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Knnclassifier {
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
		
		try{
			fr = new FileReader(path+"\\train_set.txt");
			br = new BufferedReader(fr);
			iForTest = 0;
			while((temp=br.readLine())!=null){			
				Scanner sc = new Scanner(temp).useDelimiter(",");//,로 토큰 분류
				jForTest = 0;
				while(sc.hasNext()){
					if(jForTest==4){
						String tempSpices = sc.next();
						if(tempSpices.equals("Iris-setosa")){
							trainSet[iForTest][jForTest] = 0;	
						//	System.out.println(trainSet[iForTest][0]+" "+trainSet[iForTest][1]+" "+trainSet[iForTest][2]+" "+trainSet[iForTest][3]+" "+trainSet[iForTest][4]);
						}else if(tempSpices.equals("Iris-versicolor")){
							trainSet[iForTest][jForTest] = 1;
							
						}else{							
							trainSet[iForTest][jForTest] = 2;
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
		
		try{			
			fr = new FileReader(path+"\\test_set.txt");
			br = new BufferedReader(fr);
			
			while((temp=br.readLine())!=null){
				Scanner sc = new Scanner(temp).useDelimiter(",");	
				jForTrain = 0;
				while(sc.hasNext()){
					if(jForTrain==4){
						String tempSpices = sc.next();
						if(tempSpices.equals("Iris-setosa")){
							testSet[iForTrain][jForTrain] = 0;		
						}else if(tempSpices.equals("Iris-versicolor")){
							testSet[iForTrain][jForTrain] = 1;
						}else{
							testSet[iForTrain][jForTrain] = 2;
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
		
		
		int k = 3;//몇개의 근접한 값을 찾을지 저장할 변수
		double[][] resultOfDistance = new double[iForTrain][2];//거리 계산 결과 저장을 위한 배열

		double distance = 0;//유클리드거리 계산값 저장 변수
		
		
		for(int m=0;m<iForTest;m++){
			
			//System.out.println("------------------------------------------");
			//트레이닝 집합에 대한 유클리드 거리계산
			for(int i=0;i<iForTrain;i++){
				distance = 0;
				for(int j=0;j<jForTrain-1;j++){
					distance += ((testSet[m][j]-trainSet[i][j])*(testSet[m][j]-trainSet[i][j]));
				}
				if(i==0){//i가 0잉경우 
					resultOfDistance[i][0] = Math.sqrt(distance);
					resultOfDistance[i][1] = i;
				}
				
				double[] temp1 = new double[2];
				temp1[0] = Math.sqrt(distance);
				temp1[1] = i;
				double[] temp2 = new double[2];
				
				for(int n=0;n<i;n++){//오름차순으로 거리값 정렬
					if(resultOfDistance[n][0] >= temp1[0]){
						temp2[0] = resultOfDistance[n][0];
						temp2[1] = resultOfDistance[n][1];
						resultOfDistance[n][0] = temp1[0];
						resultOfDistance[n][1] = temp1[1];
						temp1[0] = temp2[0];
						temp1[1] = temp2[1];
					}
					
					if(n==(i-1)){//현재의 거리가 가장 작은 경우
						resultOfDistance[n][0] = temp1[0];
						resultOfDistance[n][1] = temp1[1];
					}
				}
				
				
			}
			
			
			

			//가장 거리가 가까운 노드 찾고 종 분류하기
			int virginica = 0;
			int setosa = 0;
			int versicolor = 0;
			int[] count = {0,0,0};//버즈니카, 세토사, 베시컬러의 개수를 배열에 저장한다.
			
			for(int i=0;i<k;i++){
				if(testSet[(int)(resultOfDistance[i][1])][4]==0){//setosa
					setosa++;
					count[0]++;
				}else if(testSet[(int)(resultOfDistance[i][1])][4]==1){//versicolor
					versicolor++;
					count[1]++;
				}else{//virginica
					virginica++;
					count[2]++;
				}
			}
			Arrays.sort(count);//오름차순으로 정렬
			
			//추정값과 실제 종의 종류를 비교
			if(setosa==count[2]){//추정결과가 세토사로 나온경우 	
				if(testSet[m][4]==0){//테스트 셋이 세토사인경우
					totalTrue++;
				}else{
					totalFalse++;//추정이 틀린경우
				}
				
			}else if(versicolor==count[2]){//추정결과가 베시컬러로 나온경우 
				if(testSet[m][4]==0){//테스트 셋이 베시컬러인경우
					totalTrue++;
				}else{
				totalFalse++;//추정이 틀린 경우
				}
			}else if(virginica==count[2]){//추정결과가 버즈니카인경우
				if(testSet[m][4]==0){//테스트 셋이 버즈니카인경우
					totalTrue++;
				}else{
				totalFalse++;//추정이 틀린 경우
				}
			}		
		//	System.out.println("setosa : " + setosa);
		//	System.out.println("versicolor : " + versicolor);
		//	System.out.println("virginica : " + virginica);
		//	System.out.println("추정이 올은 경우 : " + totalTrue);
		//	System.out.println("추정이 틀린 경우 : " + totalFalse);
			
		}
		
	//	for(int m=0;m<iForTrain;m++ ){
	//		System.out.println(resultOfDistance[m][0]+ " " + resultOfDistance[m][1]);
	//	}
		
		//계산 끝
		//측정결과 출력
		System.out.println("학습 집합 개수 : " + iForTrain);
		System.out.println("학습 집합 개수 : " + iForTest);
		System.out.println("추정이 올은 경우 : " + totalTrue);
		System.out.println("추정이 틀린 경우 : " + totalFalse);
		
		
		
		
		
	}
}
