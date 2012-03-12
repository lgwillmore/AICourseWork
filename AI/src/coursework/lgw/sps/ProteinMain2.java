package coursework.lgw.sps;

import java.util.ArrayList;

public class ProteinMain2 {

	private static int maxtrain = 100, window = 5, fraction = 1;

	public static void main(String[] args) {
		DataSampler io = new DataSampler(window);
		io.run();
		int mtr = io.getTrainSizeAvailable();
		int atr = mtr / fraction;
		System.out.println("Training Size: " + atr);
		io.buildSampleSets(atr, io.getTestSizeAvailable());
		// Train
		PerceptronTrainer htrainer = new PerceptronTrainer(io.getTrainingSet(),
				io.getHelixClassTrain());
		PerceptronTrainer strainer = new PerceptronTrainer(io.getTrainingSet(),
				io.getSheetClassTrain());
		PerceptronTrainer ctrainer = new PerceptronTrainer(io.getTrainingSet(),
				io.getCoilClassTrain());
		System.out.println("Helix-Train error rate: "
				+ htrainer.train(maxtrain));
		System.out.println("Sheet-Train error rate: "
				+ strainer.train(maxtrain));
		System.out
				.println("Coil-Train error rate: " + ctrainer.train(maxtrain));
		// Test
		Perceptron hper = htrainer.getPerceptron();
		Perceptron sper = strainer.getPerceptron();
		Perceptron cper = ctrainer.getPerceptron();
		System.out.println("Helix Test:");
		double hp = test(io.getTestSet(), io.getHelixClassTest(), hper);
		System.out.println("Sheet Test:");
		double sp = test(io.getTestSet(), io.getSheetClassTest(), sper);
		System.out.println("Coil Test:");
		double cp = test(io.getTestSet(), io.getCoilClassTest(), cper);

		// Q3
		double q3 = (hp + sp + cp) / (double) io.getTestSet().size();
		System.out.println("Q3: " + q3);
	}

	private static double test(ArrayList<ArrayList<Double>> testVecs,
			ArrayList<ArrayList<Integer>> testClasses, Perceptron p) {
		double fp = 0, tp = 0, fn = 0, tn = 0, count = 0;

		for (int k = 0; k < testVecs.size(); k++) {
			int predict = p.predict(testVecs.get(k))[0];
			int expected = testClasses.get(k).get(0);
			if (predict == expected && predict == 1)
				tp++;
			if (predict == expected && predict == 0)
				tn++;
			if (predict != expected && predict == 1)
				fp++;
			if (predict != expected && predict == 0)
				fn++;
			count++;
		}
		System.out.println("error: " + (fp + fn) / count);
		System.out.println("tp=" + tp);
		System.out.println("fp=" + fp);
		System.out.println("tn=" + tn);
		System.out.println("fn=" + fn);
		double cc = ((tp * tn) - (fp * fn))
				/ Math.sqrt((tn + fn) * (tn + fp) * (tp + fp) * (tp + fn));
		System.out.println("correlation co-effecient: " + cc);
		return tp;
	}

}
