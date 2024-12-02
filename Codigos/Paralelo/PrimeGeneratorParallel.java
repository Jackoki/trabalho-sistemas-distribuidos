import java.util.Scanner;
import java.util.concurrent.ConcurrentSkipListSet;

public class PrimeGeneratorParallel {
    public static boolean isPrime(long num) {
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
	
    static class ThreadTask implements Runnable {
        long startNumber = 0;
        long finalNumber = 0;
		//Estrutura de dados do Java utilizado para armazenar as os números primos pelas Threads
		//Ele organiza os elementos de forma ordenada ao inserir mesmo de forma concorrente
        ConcurrentSkipListSet<Long> set;

        public ThreadTask(long startNumber, long finalNumber, ConcurrentSkipListSet<Long> set) {
            this.startNumber = startNumber;
            this.finalNumber = finalNumber;
            this.set = set;
        }

        @Override
        public void run() {
            for (long i = startNumber; i <= finalNumber; i++) {
                if (isPrime(i)) {
					//Adiciona o número primo na Estrutura de Dados
                    set.add(i);
                }
            }
        }
    }
	
	public static void main (String[] args){
		Scanner sc = new Scanner(System.in);
		int i;
		
		System.out.print("Valor inicial do intervalo: ");
		long startNumber = sc.nextLong();
		
		System.out.print("Valor final do intervalo: ");
		long finalNumber = sc.nextLong();
		
		if(startNumber > finalNumber){
			System.out.print("O valor inicial não pode ser maior que o final");
			System.exit(0);
		}
		
		System.out.print("Digite a quantidade de Threads a ser utilizado: ");
		int threadsNumber = sc.nextInt();
		
		//Nesse caso somamos com +1 devido ao intervalo, por exemplo, se fosse
		//VI = 0 e VF = 10, ele ignoraria o número 0 e começaria com o 1.
		long range = finalNumber - startNumber + 1;
		
		
		//Cada thread gerará X quantidade de números primos
		//Baseado na proporação de threads e o intervalo inserido.
		long rangeThread = range/threadsNumber;
		
		
		Thread[] threads = new Thread[threadsNumber];
		//Criação da ED que será passada para as threads inserirem os valores de forma concorrente
        ConcurrentSkipListSet<Long> set = new ConcurrentSkipListSet<>();
		
		
		//Estrutura de repetição para criar as threads
		for(i = 0; i < threadsNumber; i++) {
			//Digamos que temos 4 threads para o intervalo de 0 a 20 (rangeThread = 20/4 --> 5)
			//Thread 1: 0 + (0 * 5) --> 0
			//Thread 2: 0 + (1 * 5)	--> 5
			//Thread 3: 0 + (2 * 5) --> 10
			//Thread 4: 0 + (3 * 5)	--> 15
			long startNumberThread = startNumber + (i * rangeThread);
			
			
			

			long finalNumberThread;
			//Para a última thread, ele pegará o valor final do intervalo.
			if(i == threadsNumber - 1){
				finalNumberThread = finalNumber;
			}
			
			//Digamos que temos 4 threads para o intervalo de 0 a 20 (rangeThread = 20/4 --> 5)
			//Thread 1:  0 + 5 - 1 --> 4
			//Thread 2:  5 + 5 - 1 --> 9
			//Thread 3: 10 + 5 - 1--> 14
			//Thread 4: Condição acima --> 20
			else {
				finalNumberThread = startNumberThread + rangeThread - 1;
			}
			
			threads[i] = new Thread(new ThreadTask(startNumberThread, finalNumberThread, set));
			threads[i].start();
		}
		
		
		
        try {
            for (i = 0; i < threadsNumber; i++) {
				//Execução das Threads
                threads[i].join();
            }
        }
		
		catch (InterruptedException e) {
            e.printStackTrace();
        }

        sc.close();

        System.out.println("Números primos encontrados:");
        //ForEach para cada valor na ED declarada
		for (long prime : set) {
            System.out.println(prime);
        }
	}
}