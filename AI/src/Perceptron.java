import java.util.ArrayList;


public class Perceptron {
	Node[] nodes;
	
	public Perceptron(int input, int output){
		nodes= new Node[output];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i]=new Node(input);
		}
	}
	
	public void train(ArrayList<Double> input, ArrayList<Integer> output){
		
	}
	
	private class Node{
		double bias=1;
		double initWeight=0.0;
		double[] weights;
		
		private Node(int inputs){
			weights=new double[inputs+1];
			//add weight for bias at index 0;
			weights[0]=0.3;
			for (int i = 1; i < weights.length; i++) {
				weights[i]=initWeight;
			}
		}
		
		public void train(ArrayList<Double> in, int result){
			
		}
		
		public int predict(ArrayList<Double> in){
			double sum=weights[0]*bias;
			for(int i=0;i<in.size();i++){
				sum+=weights[i+1]*in.get(i);
			}
			if(sum>0)return 1;
			return 0;
		}
	}
	
	

}
