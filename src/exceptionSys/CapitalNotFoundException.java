package exceptionSys;

public class CapitalNotFoundException extends Exception{
    @Override
    public String getMessage() {
        return "The requested key was not found in the database!";
    }
}
