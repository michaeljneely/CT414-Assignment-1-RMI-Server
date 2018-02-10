package ie.nuigalway.ct414.assignment1.neelydaly.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AssessmentRegistry {

	private String dbName;
	private HashMap<Integer, MultipleChoiceAssessment> registeredAssessments;
	
	
	public AssessmentRegistry(String db) {
		this.dbName = db;
		this.registeredAssessments = new HashMap<Integer, MultipleChoiceAssessment>();
		this.loadRegistry();
	}
	
	private void loadRegistry() {
		ArrayList<String> lines = Utils.loadLines(dbName);
		lines.forEach(line -> {
			int questionCounter = 0;
			String[] assessment = line.split(";");
			int ID = Integer.parseInt(assessment[0]);
			String module = assessment[1];
			String info = assessment[2];
			LocalDateTime startDate = LocalDateTime.parse(assessment[3], Utils.formatter);
			LocalDateTime endDate = LocalDateTime.parse(assessment[4], Utils.formatter);
			String[] questionList = assessment[5].split("/");
			HashMap<Integer, MultipleChoiceQuestion> questions = new HashMap<Integer, MultipleChoiceQuestion>();
			for(int i = 0; i < questionList.length; i++) {
				String[] q = questionList[i].split(",");
				String q_detail = q[0];
				String[] options = {q[1], q[2], q[3], q[4]};
				int ans = Integer.parseInt(q[5]);
				MultipleChoiceQuestion question = new MultipleChoiceQuestion(questionCounter, q_detail, options, ans);
				questions.put(questionCounter, question);
			}
			questionCounter++;
			registeredAssessments.put(ID, new MultipleChoiceAssessment(ID, info, module, startDate, endDate, questions));
		});
	}
	
	public MultipleChoiceAssessment getAssessment(int id) {
		return this.registeredAssessments.get(id);
	}
}