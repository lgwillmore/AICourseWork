package coursework.lgw.sps;

import java.util.ArrayList;

public class ProteinMain {
	
	private static int train=300;
	
	public static void main(String[] args){
		int window =5;
		DataSampler io=new DataSampler(window);
		io.run();
		Perceptron pHelix = new Perceptron(20*window,1);
		Perceptron pCoil = new Perceptron(20*window,1);
		Perceptron pSheet = new Perceptron(20*window,1);
		trainAndTest(pHelix,io.getTrainingSet(),io.getHelixClassTrain(),io.getTestSet(),io.getHelixClassTest());
		trainAndTest(pCoil,io.getTrainingSet(),io.getCoilClassTrain(),io.getTestSet(),io.getCoilClassTest());
		trainAndTest(pSheet,io.getTrainingSet(),io.getSheetClassTrain(),io.getTestSet(),io.getSheetClassTest());
	}
	
	private static void trainAndTest(
			Perceptron p,
			ArrayList<ArrayList<Double>> trainVecs,
			ArrayList<ArrayList<Integer>> trainClasses,
			ArrayList<ArrayList<Double>> testVecs,
			ArrayList<ArrayList<Integer>> testClasses
			){
		for (int j = 0; j < train; j++) {
			for (int i = 0; i < trainVecs.size(); i++) {
				p.train(trainVecs.get(i), trainClasses.get(i)); 
			}
		}
		// test
		int positives=0;
		int negatives=0;
		int correctPositives=0;
		int incorrectPositives=0;
		int correctNegatives=0;
		int incorrectNegatives=0;
		for(int k =0;k<testVecs.size();k++){			
			int predict =p.predict(testVecs.get(k))[0];
			int expected=testClasses.get(k).get(0);
			if(predict==1)positives++;
			else negatives++;
			if(predict==expected&&predict==1)correctPositives++;
			if(predict==expected&&predict==0)correctNegatives++;
			if(predict!=expected&&predict==1)incorrectPositives++;
			if(predict!=expected&&predict==0)incorrectNegatives++;
			
		}
		System.out.println("p="+positives);
		System.out.println("n="+negatives);
		System.out.println("cp="+correctPositives);
		System.out.println("ip="+incorrectPositives);
		System.out.println("cn="+correctNegatives);
		System.out.println("in="+incorrectNegatives);
	}
}
