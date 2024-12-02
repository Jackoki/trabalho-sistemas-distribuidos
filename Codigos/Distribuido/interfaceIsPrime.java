import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interfaceIsPrime extends Remote {
    public boolean isPrime(long num) throws RemoteException;    
}
