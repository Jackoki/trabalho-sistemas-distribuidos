import java.util.Scanner;

public class PrimeGeneratorSequencial {

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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrada do intervalo
        System.out.print("Digite o limite inferior do intervalo: ");
        long lowerLimit = scanner.nextLong();

        System.out.print("Digite o limite superior do intervalo: ");
        long upperLimit = scanner.nextLong();

        System.out.println("Números primos no intervalo de " + lowerLimit + " a " + upperLimit + ":");

        // Verifica todos os números no intervalo
        for (long i = lowerLimit; i <= upperLimit; i++) {
            if (isPrime(i)) {
                System.out.print(i + "\n");
            }
        }

        System.out.println(); // Nova linha ao final
        scanner.close();
    }
}
