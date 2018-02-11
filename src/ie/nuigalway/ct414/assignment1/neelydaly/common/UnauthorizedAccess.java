package ie.nuigalway.ct414.assignment1.neelydaly.common;

public class UnauthorizedAccess extends Exception {

	private static final long serialVersionUID = -6923306248421505628L;

	public UnauthorizedAccess(String reason) {
		super(reason);
	}

}