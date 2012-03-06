package coursework.lgw.sps;

/**
 * This is a perceptron that is designed to take any amount of double input
 * values, and return any amount of binary integer values you want.
 * 
 * @author Laurence
 * 
 */
public class Perceptron {
	Node[] nodes;

	/**
	 * Constructor
	 * 
	 * @param inputs
	 *            = number of inputs. inputs will be doubles
	 * @param outputs
	 *            = number of outputs. binary int.
	 */
	public Perceptron(int inputs, int outputs) {
		nodes = new Node[outputs];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new Node(inputs);
		}
	}

	/*
	 * Train nodes on input instance and expected results.
	 */
	public void train(double[] input, int[] expected) {
		for (int i = 0; i < nodes.length; i++) {
			nodes[i].train(input, expected[i]);
		}
	}

	/*
	 * Get a prediction on the input
	 */
	public int[] predict(double[] input) {
		int[] result = new int[nodes.length];
		for (int i = 0; i < nodes.length; i++) {
			result[i] = nodes[i].predict(input);
		}
		return result;
	}

	/**
	 * Node is needed for each binary output you want. Every input goes to every
	 * Node.
	 * 
	 * @author Laurence
	 * 
	 */
	private class Node {
		double bias = 1;
		double initWeight = 0.0;
		double[] weights;

		private Node(int inputs) {
			weights = new double[inputs + 1];
			// add weight for bias at index 0;
			weights[0] = 0.3;
			for (int i = 1; i < weights.length; i++) {
				weights[i] = initWeight;
			}
		}

		public void train(double[] in, int expected) {
			if (predict(in) != expected) {
				if (expected == 1) {
					weights[0] += 1;
					for (int i = 0; i < in.length; i++) {
						weights[i+1] += in[i];
					}
				} else {
					weights[0] += 1;
					for (int i = 0; i < in.length; i++) {
						weights[i+1] -= in[i];
					}
				}
			}
		}

		public int predict(double[] in) {
			double sum = weights[0] * bias;
			for (int i = 0; i < in.length; i++) {
				sum += weights[i + 1] * in[i];
			}
			if (sum > 0)
				return 1;
			return 0;
		}
	}

}
