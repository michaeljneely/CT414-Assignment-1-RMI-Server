package ie.nuigalway.ct414.assignment1.neelydaly.server;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import ie.nuigalway.ct414.assignment1.neelydaly.common.NoMatchingAssessment;

public class AssessmentRegistry {

	private String dbName;
	private CourseRegistry courseRegistry;
	private HashMap<String, MultipleChoiceAssessment> registeredAssessments;
	
	
	public AssessmentRegistry(String db) {
		this.dbName = db;
		this.registeredAssessments = new HashMap<String, MultipleChoiceAssessment>();
		this.courseRegistry = new CourseRegistry(db);
		this.loadRegistry();
	}
	
	private void loadRegistry() {
		ArrayList<String> lines = Utils.loadLines(dbName);
		lines.forEach(line -> {
			int questionCounter = 0;
			String[] assessment = line.split(";");
			String ID = assessment[0];
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
	
	public MultipleChoiceAssessment getAssessmentByID(String id) throws NoMatchingAssessment {
		MultipleChoiceAssessment assessment = this.registeredAssessments.get(id);
		if(assessment == null) {
			throw new NoMatchingAssessment("");
		}
		return assessment;
	}
	
	public List<Pair<String,String>> getAssessmentsForModules(String[] modules) throws NoMatchingAssessment{
		ArrayList<Pair<String,String>> assessments = new ArrayList<Pair<String,String>>(10);
		for(String module : modules) {
			List<MultipleChoiceAssessment> mcqs = this.getAssessmentsForModule(module);
			for(MultipleChoiceAssessment mcq: mcqs) {
				assessments.add(Pair.of(mcq.getAssociatedID(), mcq.getInformation()));
			}
		}
		if (assessments.isEmpty()) {
			throw new NoMatchingAssessment("");
		}
		return assessments;
	}
	
	private List<MultipleChoiceAssessment> getAssessmentsForModule(String module) {
		ArrayList<MultipleChoiceAssessment> assessments = new ArrayList<MultipleChoiceAssessment>();
		this.registeredAssessments.values().forEach(assessment -> {
			if(assessment.getModule().equals(module)) {
				assessments.add(assessment);
			}
		});
		return assessments;
		
	}

	public void submitAssessment(MultipleChoiceAssessment a) {
	}
}
