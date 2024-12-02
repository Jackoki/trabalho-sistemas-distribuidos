import java.io.Serializable;
import java.rmi.RemoteException;

public class ImplIsPrime implements interfaceIsPrime, Serializable {

    @Override
    public boolean isPrime(long num) {
		//Análise 1
        if (num <= 1){
			return false;
		} 
		
		//Análise 2
        if (num <= 3){
			return true;
		}
		
		//Análise 3
        if (num % 2 == 0 || num % 3 == 0){
			return false;
		} 

		//Análise 4
        for (long i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0){
				return false;
			} 
        }

        return true;
    }
    
}
