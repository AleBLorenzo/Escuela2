package Fichero3.src;
public class test {


    public static void main(String[] args) {
        
        try {
            
            ProcessBuilder pb = new ProcessBuilder("firefox", "https://www.google.es");
            pb.command();
        } catch (Exception e) {
        } finally {
        }
    }
}
