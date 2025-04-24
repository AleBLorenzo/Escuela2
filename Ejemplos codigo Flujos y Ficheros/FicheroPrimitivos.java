import java.io.*;

public class DatosPrimitivos {
    public static void main(String[] args) {
        // Escribir datos primitivos en un archivo
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("datos.dat"))) {
            dos.writeInt(25);
            dos.writeDouble(3.14);
            dos.writeBoolean(true);
            dos.writeUTF("Hola mundo");
            System.out.println("Datos escritos correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }

        // Leer datos primitivos del archivo
        try (DataInputStream dis = new DataInputStream(new FileInputStream("datos.dat"))) {
            int edad = dis.readInt();
            double valor = dis.readDouble();
            boolean activo = dis.readBoolean();
            String texto = dis.readUTF();

            System.out.println("Edad: " + edad);
            System.out.println("Valor: " + valor);
            System.out.println("Activo: " + activo);
            System.out.println("Texto: " + texto);
        } catch (IOException e) {
            System.err.println("Error al leer: " + e.getMessage());
        }
    }
}
