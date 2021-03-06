package coursework.lgw.sps;

import java.util.ArrayList;

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
	 * Train nodes on input instance and expected results. If training was not
	 * required returns true, else false
	 */
	public boolean train(ArrayList<Double> input, ArrayList<Integer> expected) {
		boolean trainingNotRequired = true;
		for (int i = 0; i < nodes.length; i++) {
			if(!nodes[i].train(input, expected.get(i)))trainingNotRequired=false;
		}
		return trainingNotRequired;
	}

	/*
	 * Get a prediction on the input
	 */
	public int[] predict(ArrayList<Double> input) {
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
			for (int i = 0; i < weights.length; i++) {
				weights[i] = initWeight;
			}
		}

		/*
		 * Trains the node on given input and class. If training was not
		 * required, returns true, else false.
		 */
		public boolean train(ArrayList<Double> in, int expected) {
			if (predict(in) != expected) {
				if (expected == 1) {
					weights[0] += 1;
					for (int i = 0; i < in.size(); i++) {
						weights[i + 1] += in.get(i);
					}
				} else {
					weights[0] -= 1;
					for (int i = 0; i < in.size(); i++) {
						weights[i + 1] -= in.get(i);
					}
				}
				return false;
			}
			return true;
		}

		public int predict(ArrayList<Double> in) {
			double sum = weights[0] * bias;
			for (int i = 0; i < in.size(); i++) {
				sum += weights[i + 1] * in.get(i);
			}
			if (sum > 0)
				return 1;
			return 0;
		}
	}

}
