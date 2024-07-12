import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class datas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite uma data (DD/MM/AAAA): ");
        String dataEntrada = scanner.nextLine();
        scanner.close(); 

        String erro = validarEntrada(dataEntrada);
        if (erro != null) {
            System.out.println(erro);
        } else {
            LocalDate data = converterParaData(dataEntrada);
            if (data == null) {
                System.out.println("Erro: Data digitada é inválida.");
            } else {
                LocalDate diaSeguinte = data.plusDays(1);
                System.out.println("O dia seguinte é: " + diaSeguinte.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        }
    }

    private static String validarEntrada(String data) {
        if (data.length() != 10) {
            return "Erro: Tamanho informado incorreto.";
        }

        for (int i = 0; i < data.length(); i++) {
            if (i == 2 || i == 5) {
                if (data.charAt(i) != '/') {
                    return "Erro: Não utilizou barra como separador.";
                }
            } else {
                if (!Character.isDigit(data.charAt(i))) {
                    return "Erro: Não utilizou dígito.";
                }
            }
        }

        return null;
    }

    private static LocalDate converterParaData(String data) {
        int dia = Integer.parseInt(data.substring(0, 2));
        int mes = Integer.parseInt(data.substring(3, 5));
        int ano = Integer.parseInt(data.substring(6, 10));

        if (ano < 1600 || ano > 9998) {
            return null;
        }

        if (mes < 1 || mes > 12) {
            return null;
        }

        int[] diasNoMes = {31, (isAnoBissexto(ano) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (dia < 1 || dia > diasNoMes[mes - 1]) {
            return null;
        }

        return LocalDate.of(ano, mes, dia);
    }

    private static boolean isAnoBissexto(int ano) {
        if (ano % 4 == 0) {
            if (ano % 100 == 0) {
                return ano % 400 == 0;
            } else {
                return true;
            }
        }
        return false;
    }
}
