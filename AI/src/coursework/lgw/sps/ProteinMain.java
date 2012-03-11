package coursework.lgw.sps;

import java.util.ArrayList;

public class ProteinMain {

	private static int train = 500, runs = 5,window = 21;

	public static void main(String[] args) {
		
		
		
		for (int i = 0; i < runs; i++) {
			System.out.println("NEW ROUND------------");
			DataSampler io = new DataSampler(window);
			io.run();
			Perceptron pHelix = new Perceptron(20 * window, 1);
			Perceptron pCoil = new Perceptron(20 * window, 1);
			Perceptron pSheet = new Perceptron(20 * window, 1);
			System.out.println("H");
			double hcp = trainAndTest(pHelix, io.getTrainingSet(),
					io.getHelixClassTrain(), io.getTestSet(),
					io.getHelixClassTest());
			System.out.println("C");
			double ccp = trainAndTest(pCoil, io.getTrainingSet(),
					io.getCoilClassTrain(), io.getTestSet(),
					io.getCoilClassTest());
			System.out.println("S");
			double scp = trainAndTest(pSheet, io.getTrainingSet(),
					io.getSheetClassTrain(), io.getTestSet(),
					io.getSheetClassTest());
			double q3= (hcp+ccp+scp)/io.getTestSet().size();
			System.out.println("Q3: "+q3);
		}
	}

	private static double trainAndTest(Perceptron p,
			ArrayList<ArrayList<Double>> trainVecs,
			ArrayList<ArrayList<Integer>> trainClasses,
			ArrayList<ArrayList<Double>> testVecs,
			ArrayList<ArrayList<Integer>> testClasses) {
		//train
		for (int j = 0; j < train; j++) {
			for (int i = 0; i < trainVecs.size(); i++) {
				p.train(trainVecs.get(i), trainClasses.get(i));
			}
		}
		// test
		double positives = 0;
		double negatives = 0;
		double correctPositives = 0;
		double incorrectPositives = 0;
		double correctNegatives = 0;
		double incorrectNegatives = 0;
		for (int k = 0; k < testVecs.size(); k++) {
			int predict = p.predict(testVecs.get(k))[0];
			int expected = testClasses.get(k).get(0);
			if (predict == 1)
				positives++;
			else
				negatives++;
			if (predict == expected && predict == 1)
				correctPositives++;
			if (predict == expected && predict == 0)
				correctNegatives++;
			if (predict != expected && predict == 1)
				incorrectPositives++;
			if (predict != expected && predict == 0)
				incorrectNegatives++;

		}
		double correct = (correctPositives + 0.0)
				/ (correctPositives + incorrectNegatives);
		double d =Math.sqrt((correctNegatives+incorrectNegatives)
				*(correctNegatives+incorrectPositives)
				*(correctPositives+incorrectNegatives)
				*(correctPositives+incorrectPositives));
		double cc = ((correctPositives*correctNegatives)-(incorrectPositives*incorrectNegatives))/d;
		
		 System.out.println("Correct percentage = "+correct);
		 System.out.println("Correlation Co-effecient: "+cc);
		 //System.out.println("p=" + positives);
		 //System.out.println("n="+negatives); 
		 System.out.println("cp=" + correctPositives);
		 System.out.println("ip=" + incorrectPositives);
		 System.out.println("cn=" + correctNegatives);
		 System.out.println("in=" + incorrectNegatives);
		
		return correctPositives;
	}
}
