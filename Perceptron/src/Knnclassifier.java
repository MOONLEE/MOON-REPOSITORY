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
		int totalTrue = 0;//������ ���� ����
		int totalFalse = 0;//������ Ʋ�� ����
		
		// ���Ϸκ��� ���� �о����
		String path = Knnclassifier.class.getClassLoader().getResource(".").getPath();//Ŭ������ �ִ� ��� ���
	//	System.out.println(path);
		String temp = null;//txt������ txt������ ������ �ӽ� ��Ʈ��
		FileReader fr = null;		
		BufferedReader br = null;
		
		try{
			fr = new FileReader(path+"\\train_set.txt");
			br = new BufferedReader(fr);
			iForTest = 0;
			while((temp=br.readLine())!=null){			
				Scanner sc = new Scanner(temp).useDelimiter(",");//,�� ��ū �з�
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
		
		
		int k = 3;//��� ������ ���� ã���� ������ ����
		double[][] resultOfDistance = new double[iForTrain][2];//�Ÿ� ��� ��� ������ ���� �迭

		double distance = 0;//��Ŭ����Ÿ� ��갪 ���� ����
		
		
		for(int m=0;m<iForTest;m++){
			
			//System.out.println("------------------------------------------");
			//Ʈ���̴� ���տ� ���� ��Ŭ���� �Ÿ����
			for(int i=0;i<iForTrain;i++){
				distance = 0;
				for(int j=0;j<jForTrain-1;j++){
					distance += ((testSet[m][j]-trainSet[i][j])*(testSet[m][j]-trainSet[i][j]));
				}
				if(i==0){//i�� 0�װ�� 
					resultOfDistance[i][0] = Math.sqrt(distance);
					resultOfDistance[i][1] = i;
				}
				
				double[] temp1 = new double[2];
				temp1[0] = Math.sqrt(distance);
				temp1[1] = i;
				double[] temp2 = new double[2];
				
				for(int n=0;n<i;n++){//������������ �Ÿ��� ����
					if(resultOfDistance[n][0] >= temp1[0]){
						temp2[0] = resultOfDistance[n][0];
						temp2[1] = resultOfDistance[n][1];
						resultOfDistance[n][0] = temp1[0];
						resultOfDistance[n][1] = temp1[1];
						temp1[0] = temp2[0];
						temp1[1] = temp2[1];
					}
					
					if(n==(i-1)){//������ �Ÿ��� ���� ���� ���
						resultOfDistance[n][0] = temp1[0];
						resultOfDistance[n][1] = temp1[1];
					}
				}
				
				
			}
			
			
			

			//���� �Ÿ��� ����� ��� ã�� �� �з��ϱ�
			int virginica = 0;
			int setosa = 0;
			int versicolor = 0;
			int[] count = {0,0,0};//�����ī, �����, �����÷��� ������ �迭�� �����Ѵ�.
			
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
			Arrays.sort(count);//������������ ����
			
			//�������� ���� ���� ������ ��
			if(setosa==count[2]){//��������� ������ ���°�� 	
				if(testSet[m][4]==0){//�׽�Ʈ ���� ������ΰ��
					totalTrue++;
				}else{
					totalFalse++;//������ Ʋ�����
				}
				
			}else if(versicolor==count[2]){//��������� �����÷��� ���°�� 
				if(testSet[m][4]==0){//�׽�Ʈ ���� �����÷��ΰ��
					totalTrue++;
				}else{
				totalFalse++;//������ Ʋ�� ���
				}
			}else if(virginica==count[2]){//��������� �����ī�ΰ��
				if(testSet[m][4]==0){//�׽�Ʈ ���� �����ī�ΰ��
					totalTrue++;
				}else{
				totalFalse++;//������ Ʋ�� ���
				}
			}		
		//	System.out.println("setosa : " + setosa);
		//	System.out.println("versicolor : " + versicolor);
		//	System.out.println("virginica : " + virginica);
		//	System.out.println("������ ���� ��� : " + totalTrue);
		//	System.out.println("������ Ʋ�� ��� : " + totalFalse);
			
		}
		
	//	for(int m=0;m<iForTrain;m++ ){
	//		System.out.println(resultOfDistance[m][0]+ " " + resultOfDistance[m][1]);
	//	}
		
		//��� ��
		//������� ���
		System.out.println("�н� ���� ���� : " + iForTrain);
		System.out.println("�н� ���� ���� : " + iForTest);
		System.out.println("������ ���� ��� : " + totalTrue);
		System.out.println("������ Ʋ�� ��� : " + totalFalse);
		
		
		
		
		
	}
}
