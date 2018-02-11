package ie.nuigalway.ct414.assignment1.neelydaly.common;

public class NoMatchingAssessment extends Exception {

	private static final long serialVersionUID = 5458202822922220638L;

	public NoMatchingAssessment(String reason) {
		super(reason);
	}

}