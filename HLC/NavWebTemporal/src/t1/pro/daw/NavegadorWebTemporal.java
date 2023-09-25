package t1.pro.daw;

import java.util.Scanner;

/**
 *
 * @author aromcon1407_iesmarti
 */
public class NavegadorWebTemporal {

    public static void main(String[] args) {

        String continuar;
        Boolean bo = false;
        Scanner sc = new Scanner(System.in);

        ProcessBuilder pb;
        do {
            try {

                System.out.println("Dime una URL");
                
                String a = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
                pb = new ProcessBuilder();
                pb.command().add(a);
                pb.command().add(sc.nextLine());
                pb.start();
                

                Thread.sleep(10000);

                System.out.println("Quiere abrir otra pagina? y/n");

                continuar = sc.nextLine();

                if (continuar.equalsIgnoreCase("y")) {
                       bo = true;
                } else if (continuar.equalsIgnoreCase("n")) {
                    bo = false;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (bo);
        sc.close();
    }

}
