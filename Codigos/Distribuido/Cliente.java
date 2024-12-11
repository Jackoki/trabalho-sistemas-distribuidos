import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

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

            System.out.print("Digite o primeiro valor do intervalo: ");
            initialValue = sc.nextLong();
            System.out.print("Digite o segundo valor do intervalo: ");
            finalValue = sc.nextLong();

            // Criação de uma fila de prioridade para manter a ordem dos números
            PriorityBlockingQueue<Long> listPrimeNumbers = new PriorityBlockingQueue<>();

            // Chamada do executor com 10 threads/tarefas
            ExecutorService executor = Executors.newFixedThreadPool(10);

            for (long i = initialValue; i < finalValue; i++) {
                long number = i;

                // As tarefas das threads serão feitas de forma assíncrona
                executor.submit(() -> {
                    try {

                        //Para cada número primo que ela encontrar, ela vai inserir na ED
                        if (stub.isPrime(number)) {
                            listPrimeNumbers.put(number);
                        }
                    } 
                    
                    catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });
            }

            // Fechamento do executor
            executor.shutdown();

            //Função utilizada para finalizar as tarefas das threads
            //Passamos 2 parâmetros, a primeira é quantidade máxima de tempo para finalizar
            //A segunda é a unidade de tempo considerada no primeiro parâmetro
            //Ao invés de realizarmos um .sort em uma ED, usamos essa função, pois ela possibilita
            //A sincronização das threads após a finalização de todas as tarefas
            //Gastando menos processamento para números de grandes valores
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);



            // Impressão dos números primos da ED até esvaziar no loop
            while (!listPrimeNumbers.isEmpty()) {
                System.out.println(listPrimeNumbers.poll());
            }

        } 
        
        catch (RemoteException | NotBoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
