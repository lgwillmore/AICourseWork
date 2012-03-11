package coursework.lgw.sps;

import java.util.ArrayList;

public class PerceptronTrainer {
	private ArrayList<ArrayList<Double>> trainVectors;
	private ArrayList<ArrayList<Integer>> trainClasses;
	private int MaxIterations;
	private Perceptron p;
	private int itCount = 0;
	private int wrongCount=0;
	private ArrayList<Double> errorRates;

	public PerceptronTrainer(ArrayList<ArrayList<Double>> trainVectors,
			ArrayList<ArrayList<Integer>> trainClasses) {
		p = new Perceptron((int) trainVectors.get(0).size(), trainClasses
				.get(0).size());
		this.trainVectors = trainVectors;
		this.trainClasses = trainClasses;
	}

	/*
	 * Train on the given data set. Returns error rate of final iteration;
	 */
	public double train(int maxIterations) {
		this.MaxIterations = maxIterations;
		wrongCount=0;
		itCount = 0;
		errorRates=new ArrayList<Double>();
		if(trainHelper()){
			return 0.0;
		}
		else return (double)wrongCount/(double)trainVectors.size();
	}

	private boolean trainHelper() {
		boolean perfect = true;
		wrongCount=0;
		for (int i = 0; i < trainVectors.size(); i++) {
			if (!p.train(trainVectors.get(i), trainClasses.get(i))){
				perfect = false;
				wrongCount++;
			}
		}
		errorRates.add((double)wrongCount/(double)trainVectors.size());
		itCount++;
		if (!perfect) {
			if (itCount < MaxIterations)
				return trainHelper();
			else
				return false;
		}
		return true;
	}

	public Perceptron getPerceptron() {
		return p;
	}

}
