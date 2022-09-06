using System; 

public class Program {

  public static void Main(string[] args) {
    Console.WriteLine("Numeros de Fibonacci");
    Console.WriteLine("Ingresa el numero de terminos que deseas:");
    int objective = int.Parse(Console.ReadLine());
    Console.WriteLine("Los " + objective + " primeros terminos de Fibonacci son:");
    for(int i=1; i<=objective;i++){
      Console.Write(fibo(i) + " ");
    }
  }

  public static int fibo(int n_termino){
      switch(n_termino){
          case 1 : return 1;
          case 2 : return 1;
          default: return fibo(n_termino-1) + fibo(n_termino-2);
      }
  }
}
