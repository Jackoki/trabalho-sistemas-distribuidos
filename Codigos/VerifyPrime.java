import java.util.Scanner;

//Código Java bem simples para passar um número teste
//para verificar se ele é primo ou não.

//Utilizamos esse código para autenticar o funcionamento 
//dos números gerados nos outros códigos.

public class VerifyPrime {

    // Método para verificar se um número é primo
    public static boolean ehPrimo(long num) {
        if (num < 2) {
            return false;  // Números menores que 2 não são primos
        }
        
        // Verificar divisibilidade até a raiz quadrada de num
        for (long i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;  // Se o número for divisível por i, não é primo
            }
        }
        
        return true;  // Se não for divisível por nenhum número até √num, é primo
    }

    public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
        System.out.print("Digite o número: ");
        long numero = scanner.nextLong();

        if (ehPrimo(numero)) {
            System.out.println(numero + " é um número primo.");
        } 
        
        else {
            System.out.println(numero + " não é um número primo.");
        }
    }
}
