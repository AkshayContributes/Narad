package exception;

public class NonExistentSubscriberException extends Exception{
  public NonExistentSubscriberException (String errorMessage){
    super(errorMessage);
  }

}
