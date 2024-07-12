import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Guerreiro {
    String nome;
    int terras;
    List<Guerreiro> filhos;

    public Guerreiro(String nome, int terras) {
        this.nome = nome;
        this.terras = terras;
        this.filhos = new ArrayList<>();
    }

    public void adicionarFilho(Guerreiro filho, int terras) {
        filho.transferirTerras(terras);
        filhos.add(filho);
    }

    public void transferirTerras(int terras) {
        this.terras += terras;
    }

    public List<Guerreiro> getFilhos() {
        return filhos;
    }

    public int getTotalTerras() {
        int totalTerras = terras;
        for (Guerreiro filho : filhos) {
            totalTerras += filho.getTotalTerras();
        }
        return totalTerras;
    }

    public boolean temFilhos() {
        return !filhos.isEmpty();
    }

    public void distribuirTerrasEquitativamente() {
        if (temFilhos()) {
            distribuirTerrasEquitativamenteAuxiliar(this, terras);
        }
    }

    private static void distribuirTerrasEquitativamenteAuxiliar(Guerreiro guerreiro, int terrasParaDistribuir) {
        if (guerreiro.temFilhos()) {
            int terrasPorFilho = terrasParaDistribuir / guerreiro.getFilhos().size();
            int terrasRestantes = terrasParaDistribuir % guerreiro.getFilhos().size();

            for (Guerreiro filho : guerreiro.getFilhos()) {
                filho.transferirTerras(terrasPorFilho);
                distribuirTerrasEquitativamenteAuxiliar(filho, terrasPorFilho);
            }

            for (int i = 0; i < terrasRestantes; i++) {
                guerreiro.getFilhos().get(i).transferirTerras(1);
            }

            guerreiro.terras = 0;
        }
    }
}

public class TriboBarbara {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int terrasIniciais = scanner.nextInt();
        scanner.nextLine();

        Guerreiro raiz = new Guerreiro("Thorgestax", terrasIniciais);
        int x = 0;
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            if (linha.isEmpty())
                break;

            String[] dados = linha.split(" ");
            String nomePai = dados[0];
            if (x == 0) {
                raiz.nome = nomePai;
            }
            x++;
            String nomeFilho = dados[1];
            int terrasFilho = Integer.parseInt(dados[2]);

            Guerreiro pai = encontrarGuerreiro(raiz, nomePai);
            if (pai != null) {
                Guerreiro filho = new Guerreiro(nomeFilho, 0);
                pai.adicionarFilho(filho, terrasFilho);
            }
        }

        raiz.distribuirTerrasEquitativamente();

        Guerreiro guerreiroMaxTerras = encontrarGuerreiroMaxTerrasUltimaGeracao(raiz);
        if (guerreiroMaxTerras != null) {
            System.out.println("O guerreiro na última geração com mais terras é " +
                    guerreiroMaxTerras.nome + ", com um total de " + guerreiroMaxTerras.getTotalTerras() + " terras.");
        } else {
            System.out.println("Não foi encontrado nenhum guerreiro na última geração.");
        }

        scanner.close();
    }

    private static Guerreiro encontrarGuerreiroMaxTerrasUltimaGeracao(Guerreiro guerreiro) {
        List<Guerreiro> ultimaGeracao = new ArrayList<>();
        encontrarUltimaGeracao(guerreiro, ultimaGeracao);

        Guerreiro guerreiroMax = null;
        int maxTerras = -1;

        for (Guerreiro g : ultimaGeracao) {
            int terras = g.getTotalTerras();
            if (terras > maxTerras) {
                maxTerras = terras;
                guerreiroMax = g;
            }
        }

        return guerreiroMax;
    }

    private static void encontrarUltimaGeracao(Guerreiro guerreiro, List<Guerreiro> ultimaGeracao) {
        Queue<Guerreiro> fila = new LinkedList<>();
        fila.add(guerreiro);

        while (!fila.isEmpty()) {
            int tamanho = fila.size();
            ultimaGeracao.clear();

            for (int i = 0; i < tamanho; i++) {
                Guerreiro atual = fila.poll();
                ultimaGeracao.add(atual);

                fila.addAll(atual.getFilhos());
            }
        }
    }

    private static Guerreiro encontrarGuerreiro(Guerreiro guerreiro, String nome) {
        if (guerreiro.nome.equals(nome)) {
            return guerreiro;
        }

        for (Guerreiro filho : guerreiro.getFilhos()) {
            Guerreiro encontrado = encontrarGuerreiro(filho, nome);
            if (encontrado != null) {
                return encontrado;
            }
        }

        return null;
 }
}