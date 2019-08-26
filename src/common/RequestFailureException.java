package common;

public class RequestFailureException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private Exception ex = null;

	public RequestFailureException(String msg){
		super(msg);
	}
	public RequestFailureException(String msg,Exception ex){
		super(msg);
		this.ex = ex;
	}
	public Exception getException(){
		return ex;
	}
}
