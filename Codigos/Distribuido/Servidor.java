import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends UnicastRemoteObject implements interfaceIsPrime {
	//Importação da classe com a função que verifica se é primo ou não
    ImplIsPrime impIsPrime;
	
	//Declaração do uso do 'executor', que é uma interface do Java
	//Ele permite o uso de threads que realizam tarefas assíncronas mas permitindo
	//a realização de tarefas em segundo plano 
    private static final int THREADS_QUANTITY = 10;
    private ExecutorService executor;

    public Servidor() throws RemoteException {
        super();
		//Criação da classe do Executor
        executor = Executors.newFixedThreadPool(THREADS_QUANTITY);
    }

    public static void main(String[] args) {
        try {
            new Servidor().iniciar();
        } 
		
		catch (RemoteException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void iniciar() {
        try {
            impIsPrime = new ImplIsPrime();
			//Criação do Servidor na porta 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            System.out.println("Servidor Geral RMI iniciado...");
			//Chamada da função impIsPrime
            registry.rebind("IsPrimeFunction", impIsPrime);
        } 
		
		catch (RemoteException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	@Override
	public boolean isPrime(long num) throws RemoteException {
		//Como estamos utilizando o executor, pode acontecer algumas exceções
		//Por exemplo, o InterruptedException, que é chamada quando alguma Thread é interrompida por outra tarefa
		//O java.util.concurrent.ExecutionException ocorre quando ocorre uma falha durante a execução da tarefa
		try {
			//A função submit() realiza a tarefa de forma assincrona
			//mas o get() realiza o bloqueio para o retorno ser sincrono.
			//O valor a retornar o resultado do valor impIsPrime()
			return executor.submit(() -> impIsPrime.isPrime(num)).get();
		} 
		
		catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		
		catch (java.util.concurrent.ExecutionException e) {
			e.printStackTrace();
			return false;
		}
	}
}
