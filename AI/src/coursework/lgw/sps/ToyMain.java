package coursework.lgw.sps;
public class ToyMain {

	public static void main(String[] args) {
		double[][] trainInput = new double[][] { new double[] { 196, 123 },
				new double[] { 182, 82 }, new double[] { 176, 82 },
				new double[] { 169, 105 }, new double[] { 155, 95 },
				new double[] { 147, 70 }, new double[] { 150, 50 },
				new double[] { 170, 125 }, new double[] { 185, 95 },
				new double[] { 190, 63 }, };

		int[][] trainexpected = new int[][] { new int[] { 1 }, new int[] { 0 },
				new int[] { 0 }, new int[] { 1 }, new int[] { 1 },
				new int[] { 1 }, new int[] { 0 }, new int[] { 1 },
				new int[] { 0 }, new int[] { 0 } };

		double[][] testInput = new double[][] { new double[] { 199, 130 },
				new double[] { 186, 88 }, new double[] { 172, 117 },
				new double[] { 149, 70 }, new double[] { 151, 57 },
				new double[] { 163, 76 }, new double[] { 177, 50 },
				new double[] { 191, 99 }, new double[] { 158, 95 },
				new double[] { 165, 128 }, };

		int[][] testexpected = new int[][] { new int[] { 1 }, new int[] { 0 },
				new int[] { 1 }, new int[] { 1 }, new int[] { 0 },
				new int[] { 0 }, new int[] { 0 }, new int[] { 0 },
				new int[] { 1 }, new int[] { 1 } };

		Perceptron ai = new Perceptron(2, 1);
		// train
		for (int j = 0; j < 1000; j++) {
			for (int i = 0; i < trainInput.length; i++) {
				ai.train(trainInput[i], trainexpected[i]);
			}
		}
		// test
		for(int k =0;k<testInput.length;k++){
			System.out.println("Test: "+(k+1)+" Predict: "+ai.predict(testInput[k])[0]+" Expected: "+testexpected[k][0]);
		}
		System.out.println("done");

	}
}
