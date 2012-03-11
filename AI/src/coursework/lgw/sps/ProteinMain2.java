package coursework.lgw.sps;

import java.util.ArrayList;

public class ProteinMain2 {
	
	private static int maxtrain = 400, window = 5;

	public static void main(String[] args) {
		DataSampler io = new DataSampler(window);
		io.run();
		PerceptronTrainer htrainer= new PerceptronTrainer(io.getTrainingSet(), io.getHelixClassTrain());
		System.out.println(htrainer.train(maxtrain));
		System.out.println("done");
	}	

}
