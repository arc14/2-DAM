package prose.tema.pkg1;

public class PROSETema1 {

    public static void main(String[] args) {

        try {

            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int c = Integer.parseInt(args[2]);

            if (!args[0].equals("NULL") || !args[1].equals("NULL") || !args[2].equals("NULL")) {
                   System.out.println("El mayor es: " + calcularMayorTres(a, b, c));
            }
        } catch (Exception e) {
            System.out.println("Falta alguna variable de entrada o no son numeros");
            System.out.println("Por favor revise los valores de entrada");
        }
    }

    public static int calcularMayorTres(int a, int b, int c) {
        if ((a > b) && (a > c)) {
            return a;
        } else if ((b > a) && (b > c)) {
            return b;
        } else {
            return c;
        }
    }
}
