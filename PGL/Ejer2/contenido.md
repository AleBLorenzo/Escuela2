## Que es un hilo?

Los Threads, hilos de ejecución o procesos ligeros, son las unidades más pequeñas de procesamiento que pueden ser programadas por los sistemas operativos, y que
permiten a un mismo proceso ejecutar diferentes tareas de forma simultánea.

## Como crearlos?

Para crear threads en Java podemos optar por dos opciones:
- Crear una clase que herede de la clase Thread (java.lang.Thread), o bien
- crear una clase que implemente la interfaz Runnable (java.lang.Runnable).

> aunque ambos mecanismos son prácticamente equivalentes, la opción recomendada en
la documentación de Java es utilizar la interfaz Runnable.

## Creación de hilos mediante la clase Thread

El código correspondiente para ello sería:

public class MiHilo extends Thread{

 public void run(){
 // lógica interna del hilo
  }
 }

● Y ahora, en otra clase, inicializamos y ejecutamos el hilo:

  public class Principal {
    public static void main(String[] args) {
// Creamos el nuevo thread
  MiHilo t=new MiHilo();
// Lo ponemos en ejecución
  t.start();
  }
}

> IMPORTANTE
Observad que la lógica del hilo se define en el método run(), pero la invocación la
realizamos mediante el método start(), que es el que hace posible la ejecución asíncrona
de la lógica indicada en el método run(). Si se invocase el método run() directamente, su
lógica se ejecutaría de forma síncrona.

## Creación de hilos mediante la interfaz Runnable

Definir una clase que implemente la interfaz Runnable y sobreescribir el
método public void run(), con la lógica interna del hilo:


public class ClaseRunnable implements Runnable {

// Declaración de atributos y métodos

@Override
public void run() {

// lógica interna del hilo
 }
}

- Crear el Thread. Para ello, creamos una instancia de la clase ClaseRunnable y se
la proporcionamos al constructor de la clase Thread para crear el nuevo hilo:

ClaseRunnable objetoRunnable=new ClaseRunnable();

Thread hilo=new Thread(objetoRunnable);

- Lanzar el hilo, invocando al método start():

hilo.start();

>IMPORTANTE
La clase Thread admite múltiples constructores. En nuestro caso, estamos utilizando un
constructor al que le proporcionamos una clase que implementa la interfaz Runnable,
pero también podemos proporcionarle un nombre al Thread, e incluso un nombre de
grupo.

## CREACIÓN DE MÚLTIPLES HILOS

En algunas ocasiones necesitaremos controlar varios hilos de ejecución en nuestra
aplicación. En este caso deberemos utilizar estructuras de datos como vectores o listas
para almacenarlos.

- Creación del vector o lista:

Thread vector_hilos[]=new Thread[longitud];

ArrayList<Thread> lista_hilos=new ArrayList<Thread>();

- Creación de los hilos:

Thread hilo=new Thread(objetoRunnable);

vector_hilos[posicion_i]=hilo;
lista_hilos.add(hilo);

// O bien todo en la misma orden:

vector_hilos[posicion_i]=new Thread(objetoRunnable);
lista_hilos.add(new Thread(objetoRunnable));

- Lanzamiento de los hilos:

vector_hilos[posicion].start();
lista_hilos.get(0).start();

##