package creacionprocesos;

import java.io.IOException;

/**
 *
 * @author AsEh
 */
public class CreacionProcesos {

    public static void main(String[] args) {

        if (args.length == 1 && args[0].equals("help")) {
            System.out.println("-- HELP --");
            System.out.println("Primer argumento puede ser:");
            System.out.println("P : lanza ProcessBuilder");
            System.out.println("R : lanza RunTime");
            System.out.println("Segundo argumento puede ser:");
            System.out.println("C : lanza Chrome");
            System.out.println("N : lanza notepad");
        } else if (args.length == 2) {
            param1(args[0], args[1]);

        } else if (args.length == 3) {

        } else {
            System.out.println("Parametro no reconocido o vacio");
        }
    }

    private static void param1(String a, String b) {
        if (a.equals("P")) {
            try {
                ProcessBuilder pb;
                pb = new ProcessBuilder(param2(b));
                pb.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (a.equals("R")) {
            try {
                String[] comando = {param2(b)};
                Runtime.getRuntime().exec(comando);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Parametro no reconocido");
        }
    }

    private static String param2(String a) {
        if (a.equals("C")) {
            return "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
        } else if (a.equals("N")) {
            return "C:\\Windows\\System32\\notepad.exe";
        } else {
            System.out.println("Parametro no reconocido");
            return null;
        }
    }
    
    //----
    
    private static void param13(String a, String b, String c) {
        if (a.equals("P")) {
            try {
                ProcessBuilder pb;
                pb = new ProcessBuilder(param2(b), param23(c));
                pb.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (a.equals("R")) {
            try {
                String[] comando1 = {param2(b)};
                String[] comando2 = {param23(c)};
                Runtime.getRuntime().exec(comando1, comando2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Parametro no reconocido");
        }
    }

    private static String param23(String a) {
        if (a.equals("C")) {
            return "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
        } else if (a.equals("N")) {
            return "C:\\Windows\\System32\\notepad.exe";
        } else {
            System.out.println("Parametro no reconocido");
            return null;
        }
    }
    
}
