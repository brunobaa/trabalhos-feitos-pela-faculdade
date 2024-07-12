import java.util.Scanner;

public class NextDateCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a data no formato DD/MM/AAAA: ");
        String inputData = scanner.nextLine();

        if (validarData(inputData)) {
            inputData = calcularProximaData(inputData);
            System.out.println("A próxima data é: " + inputData);
        } else {
            System.out.println("Data inválida. Por favor, insira no formato DD/MM/AAAA.");
        }

        scanner.close();
    }

    private static boolean validarData(String data) {
        if (!data.matches("\\d{2}/\\d{2}/\\d{4}")) {
            return false;
        }

        String[] partes = data.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int ano = Integer.parseInt(partes[2]);

        if (mes < 1 || mes > 12) {
            return false;
        }

        int ultimoDiaDoMes = obterUltimoDiaDoMes(mes, ano);
        if (dia < 1 || dia > ultimoDiaDoMes) {
            return false;
        }

        return true;
    }

    private static String calcularProximaData(String data) {
        String[] partes = data.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int ano = Integer.parseInt(partes[2]);

        int ultimoDiaDoMes = obterUltimoDiaDoMes(mes, ano);

        if (dia == ultimoDiaDoMes) {
            dia = 1;
            if (mes == 12) {
                mes = 1;
                ano++;
            } else {
                mes++;
            }
        } else {
            dia++;
        }

        return String.format("%02d/%02d/%04d", dia, mes, ano);
    }

    private static int obterUltimoDiaDoMes(int mes, int ano) {
        if (mes == 2) {
            if ((ano % 4 == 0 && ano % 100 != 0) || ano % 400 == 0) {
                return 29;
            } else {
                return 28;
            }
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            return 30;
        } else {
            return 31;
        }
    }
}
