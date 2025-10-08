import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class LogAnalyzer {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Iniciando análisis de logs...");

        // 1. Construir el comando que queremos ejecutar
        // Es equivalente a: grep "ERROR" access.log
        ProcessBuilder processBuilder = new ProcessBuilder(
                "grep",
                "ERROR",
                "access.log");

        // Opcional pero recomendado: Redirigir el error estándar del subproceso
        // para que se muestre en la consola de nuestro programa Java.
        processBuilder.redirectErrorStream(true);

        try {
            // 2. Iniciar el proceso
            System.out.println("Lanzando worker 'grep'...");
            Process process = processBuilder.start();

            // En los siguientes pasos, procesaremos la salida de este 'process'.
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter("errors.log"));

            String line;
            int errorCount = 0;

            System.out.println("Master leyendo la salida del worker y procesando...");
            while ((line = reader.readLine()) != null) {
                // 3a. Escribir la línea de error en nuestro fichero
                writer.write(line + "\n");

                // 3b. Incrementar el contador
                errorCount++;
            }

            // No olvides cerrar los recursos
            reader.close();
            writer.close();

            int exitCode = process.waitFor();

            System.out.println("\nEl proceso worker 'grep' ha finalizado con el código de salida: " + exitCode);
            System.out.println("--------------------------------------------------");
            System.out.println("Total de errores encontrados: " + errorCount);
            System.out.println("Las líneas de error han sido guardadas en 'errors.log'");
            System.out.println("--------------------------------------------------");

            System.out.println("Análisis completado.");

            countErrorsWithPipeline();

        } catch (IOException e) {
            System.err.println(
                    "Error al ejecutar el comando. Asegúrate de que 'grep' está instalado y en el PATH del sistema.");
            e.printStackTrace();
        }
    }

    // Puedes añadir este método a tu clase LogAnalyzer
    public static void countErrorsWithPipeline() throws IOException, InterruptedException {
        
        System.out.println("\n--- Contando errores con startPipeline ---");

        // Worker 1: grep "ERROR" access.log
        ProcessBuilder grepBuilder = new ProcessBuilder("grep", "ERROR", "access.log");

        // Worker 2: wc -l
        ProcessBuilder wcBuilder = new ProcessBuilder("wc", "-l");

        // Crear la lista de workers para la tubería
        List<ProcessBuilder> pipeline = List.of(grepBuilder, wcBuilder);

        // Iniciar la tubería. stdout del primero se conecta a stdin del segundo.
        List<Process> processes = ProcessBuilder.startPipeline(pipeline);

        // El resultado final está en la salida del ÚLTIMO proceso de la tubería
        Process lastProcess = processes.get(processes.size() - 1);

        // Leer el resultado final
        String count;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(lastProcess.getInputStream()))) {
            count = reader.readLine();
        }

        // Esperar a que el último proceso termine
        lastProcess.waitFor();

        System.out.println("Resultado de la tubería (wc -l): " + count.trim());
    }
}
