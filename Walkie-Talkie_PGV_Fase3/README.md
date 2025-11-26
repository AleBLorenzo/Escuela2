ntroducción a la Fase 3

¡Has llegado a la fase más desafiante y emocionante del proyecto! En la Fase 2, construiste un "Chat Privado" funcional donde un servidor podía conversar con un único cliente. Pero la verdadera utilidad de un chat es permitir que múltiples personas hablen entre sí.

En esta fase, transformarás tu servidor en una "Sala de Reuniones" centralizada. Tu objetivo es que el servidor pueda aceptar y gestionar conexiones de múltiples clientes de forma simultánea. Cuando un cliente envíe un mensaje, este no irá solo al servidor, sino que será retransmitido (broadcast) a todos los demás clientes conectados a la sala.

Para lograr esto, tendrás que enfrentarte al problema fundamental de los servidores de red: ¿cómo atender a un nuevo cliente si ya estás ocupado hablando con otro? La respuesta es la programación multihilo (multithreading).

Requisitos Previos: Verificación de la Fase 2

Esta fase implica una reestructuración profunda de tu servidor. Antes de empezar, es crucial que tu solución de la Fase 2 funcione a la perfección:

    Tu Servidor.java y Cliente.java pueden mantener una conversación continua.

    La comunicación es bidireccional: ambos pueden enviar y recibir mensajes.

    El protocolo de finalización con la palabra "adios" funciona correctamente, cerrando la conexión de forma limpia.

    Comprendes perfectamente cómo funcionan InputStream, OutputStream, BufferedReader y PrintWriter, ya que los seguirás utilizando.

Objetivos de Aprendizaje de esta Fase:

    Comprender por qué un servidor de un solo hilo no puede atender a múltiples clientes.

    Aprender a usar hilos (Thread y Runnable) para manejar cada conexión de cliente de forma independiente.

    Identificar un recurso compartido (la lista de clientes) y entender por qué su acceso debe ser controlado.

    Implementar la sincronización para proteger el recurso compartido y evitar condiciones de carrera.

    Rediseñar el cliente para que pueda enviar y recibir mensajes simultáneamente, también usando hilos.

Tarea 1: El Servidor Despachador y sus Asistentes

En esta tarea, reestructurarás completamente el lado del servidor.

Parte A: Rediseño del Servidor Principal (Servidor.java)

El servidor principal ahora es mucho más simple. Es el "recepcionista".

    Declara el Recurso Compartido: En la clase Servidor, crea una static List<PrintWriter> para almacenar los canales de salida de los clientes. Esta es la lista que pasarás a cada GestionCliente. Para empezar, puedes usar un ArrayList, pero pronto verás que necesitas protegerla.

    Protege el Recurso Compartido: Un ArrayList normal no es seguro para ser modificado por múltiples hilos a la vez. Investiga la clase Collections y encuentra un método que te devuelva una versión sincronizada de tu lista. Usa esa lista sincronizada como tu recurso compartido.

    Implementa el Bucle del Recepcionista:

        El método main ahora debe contener un bucle while(true).

        Dentro del bucle, simplemente llama a serverSocket.accept().

        Cuando se establezca una conexión, crea una nueva instancia de GestionCliente, pasándole el Socket, un nombre único para el nuevo cliente y la referencia a la lista compartida.

        Crea un new Thread con esta instancia de GestionCliente y llama a .start().

        ¡Eso es todo! El hilo principal volverá al inicio del bucle para esperar al siguiente cliente.

Parte B: Creación de la Clase Asistente (GestionCliente.java)

Esta será la clase que define el comportamiento de cada hilo trabajador.

    Crea la nueva clase GestionCliente que implemente la interfaz Runnable.

    Define sus Atributos: Piensa en todo lo que un asistente necesita para hacer su trabajo. Necesitará:

        Una referencia al Socket del cliente que tiene que atender.

        Un nombre o identificador para saber quién es su cliente.

        Y lo más importante: ¡Necesita una forma de hablar con los clientes de los otros asistentes! Por lo tanto, necesitará acceso a la lista compartida donde se guardan los "canales de salida" de todos los clientes.

    Implementa el Constructor: El constructor de GestionCliente deberá recibir y almacenar estos tres elementos: el Socket, un String para el nombre y la referencia a la lista compartida.

    Implementa el Método run():

        Este método es el corazón del asistente. Mueve aquí toda la lógica de conversación que tenías en el main de tu servidor de la Fase 2.

        Primero, dentro de un bloque try-catch, prepara los flujos de entrada (BufferedReader) y salida (PrintWriter) para tu cliente.

        Una vez que tengas el PrintWriter de tu cliente, debes añadirlo a la lista compartida para que los demás asistentes sepan que existes.

        Anuncia a todos que un nuevo usuario se ha unido. Para ello, crea una función broadcast(String mensaje) que recorra la lista compartida y envíe el mensaje a cada PrintWriter de la lista.

        Entra en el bucle while para leer los mensajes de tu cliente.

        Modifica la lógica del bucle (dasaparece): Cuando recibas un mensaje, en lugar de imprimirlo en la consola, formátéalo (ej. "Ana: Hola a todos") y pásalo a tu método broadcast() para retransmitirlo a todos.

        Gestiona la Desconexión: Cuando un cliente envíe "adios" o la conexión se cierre, es crucial que, antes de terminar, elimines su PrintWriter de la lista compartida. Si no lo haces, los otros asistentes intentarán enviar mensajes a una conexión cerrada, causando errores. Finalmente, anuncia a todos que el usuario se ha ido.

Tarea 2: El Cliente Multitarea (Cliente.java)

El cliente también debe evolucionar. Un solo hilo no puede estar esperando a que escribas un mensaje y, al mismo tiempo, estar pendiente de los mensajes que llegan del servidor.

    Crea una Clase para Escuchar (ReceptorMensajes.java):

        Crea una nueva clase que implemente Runnable. Su única responsabilidad será escuchar.

        El constructor recibirá el BufferedReader del Socket principal del cliente.

        El método run() contendrá un bucle while que simplemente llama a bufferedReader.readLine() y muestra cualquier mensaje que llegue en la consola. El bucle se romperá naturalmente cuando la conexión se cierre y readLine() devuelva null.

    Adapta el main del Cliente:

        En tu método main, después de crear el Socket y obtener los flujos BufferedReader y PrintWriter:

        Crea una instancia de tu nuevo ReceptorMensajes.

        Lanza esta tarea en un nuevo hilo (new Thread(...).start()).

        Ahora que el "hilo oyente" está trabajando en segundo plano, tu hilo principal puede dedicarse por completo a su propio bucle while: leer la entrada del teclado con Scanner y enviar los mensajes al servidor a través del PrintWriter.

Criterios de Evaluación:

    El servidor acepta múltiples clientes sin bloquearse.

    Cada cliente es gestionado en un hilo separado en el servidor.

    Se ha creado una clase GestionCliente que encapsula la lógica de comunicación y broadcast.

    Se ha implementado una lista compartida y sincronizada para los canales de salida de los clientes.

    Un mensaje enviado por un cliente es correctamente retransmitido a todos los demás.

    La desconexión de un cliente se maneja limpiamente, eliminándolo de la lista de difusión.

    El cliente utiliza dos hilos para separar la tarea de enviar mensajes de la de recibir, permitiendo una comunicación fluida.
