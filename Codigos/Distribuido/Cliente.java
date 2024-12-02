import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Cliente {
    public static void main(String[] args) {
        try {
			//Entrada no servidor pela porta 1099 e endereço localhost
            Registry serverRegister = LocateRegistry.getRegistry("localhost", 1099);
			
			//Função a ser chamada sendo o IsPrimeFunction
            interfaceIsPrime stub = (interfaceIsPrime) serverRegister.lookup("IsPrimeFunction");
            Scanner sc = new Scanner(System.in);
			
            long initialValue;
			long finalValue;
			long i;
			
            System.out.print("Digite o primeiro valor do intervalo: ");
            initialValue = sc.nextLong();
            System.out.print("Digite o segundo valor do intervalo: ");
            finalValue = sc.nextLong();

			//Chamada do executor com 10 threads/tarefas
            ExecutorService executor = Executors.newFixedThreadPool(10);

            System.out.println("Números primos do intervalo inserido: ");
            for (i = initialValue; i < finalValue; i++) {
                long number = i;
				
				//As tarefas das threads serão feitas de forma assincronas
				//Explicação está no código do Servidor.
                executor.submit(() -> {
                    try {
                        if (stub.isPrime(number)) {
                            System.out.println(number);
                        }
                    } 
					
					catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });
            }

			//Fechamento do executor
            executor.shutdown();

        } 
		
		catch (RemoteException e) {
			e.printStackTrace();
		}
		
		catch (NotBoundException e) {
			e.printStackTrace();
		}
    }
}
