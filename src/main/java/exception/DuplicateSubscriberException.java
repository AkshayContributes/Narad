package exception;

public class DuplicateSubscriberException extends Exception{
  public DuplicateSubscriberException(String errorMessage){
    super(errorMessage);
  }

}
