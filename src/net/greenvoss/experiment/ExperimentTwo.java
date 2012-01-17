package net.greenvoss.experiment;

import java.util.List;
import net.greenvoss.PerceptronTrainer;

public class ExperimentTwo extends ExperimentBase {

	List<PerceptronTrainer> trainerList;
	
	public void execute(String trainingDataPath, String testingDataPath, int digit) {
		//create trainers 
		trainerList = this.getPerceptronTrainers(10, 64, 0.2f);
		
		//Load training data
		List<String> fileData = this.getFileContents(trainingDataPath);
		
		//train on the data
		//	NOTE: We are forcing the min epoch to 10 to overtrain the perceptrons
		int epochs = this.train(trainerList,fileData,digit,10,1000);
		//show results of the testing data
		List<ExperimentMetrics> metrics = this.calculateMetrics(trainerList, fileData, digit);
		this.ReportResults(trainerList, metrics, epochs, "Experiment Two metrics on training data:");
		
		//done training - get testing metrics//////////////////////////
		//Load testing data
		List<String> testFileData = this.getFileContents(testingDataPath);
		metrics = this.calculateMetrics(trainerList, testFileData, digit);
		
		//Done - we can now print out the metrics
		this.ReportResults(trainerList, metrics, epochs, "Experiment Two metrics on testing data:");
	}

}