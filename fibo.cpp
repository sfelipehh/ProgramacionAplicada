#include <iostream>

using namespace std;

int fibo(int n_term){
    if(n_term==1){
        return 1;
    }else
    if(n_term==2){
        return 1;
    }else {
        return fibo(n_term-1) + fibo(n_term-2);
    }
}

int main() {
  cout << "Numeros de Fibonacci\n";
    int objective = 0;
    int iter = 1;
    int result = 0;
    cout << "Ingresa cuantos terminos quieres: ";  
    cin >> objective;
    cout << "Los " << objective << " primeros terminos de Fibonacci son: \n";
    while(iter<=objective){
        result = fibo(iter);
        cout << result << " ";
        iter = iter + 1;
    }
    
}
