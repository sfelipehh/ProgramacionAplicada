def fibo(n_termino):
    if(n_termino==1):
        return 1
    elif(n_termino==2):
        return 1
    else:
        return fibo(n_termino-1) + fibo(n_termino-2)


print("Numeros de Fibonacci")

objective = int(input("Ingresa cuantos terminos deseas: "))

print(f'Los {objective} primeros terminos de Fibonacci son:')
for i in range(1,objective+1):
    print(fibo(i), end=" ")
