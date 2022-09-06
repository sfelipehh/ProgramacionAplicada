import java.util.Scanner;

class Fibo {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Numeros de Fibonacci");
        System.out.println("Ingresa la cantidad de terminos que deseas:");
        int objective = keyboard.nextInt();
        System.out.printf("Los %d primeros numeros de Fibonacci son:\n",objective);
        for (int i = 1; i <= objective; i++) {
            System.out.print(fibo(i) + "\t");
        }
    }

    public static int fibo(int n_termino){
        return switch (n_termino){
            case 1 -> 1;
            case 2 -> 1;
            default -> fibo(n_termino-1) + fibo(n_termino-2);
        };
    }
}
