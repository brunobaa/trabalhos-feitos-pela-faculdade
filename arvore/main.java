 import java.util.Random;                                // Usa a biblioteca de randomização
 
 class Main {                                            // Classe do programa principal
     public static void main(String[] args) {            // função incial do programa
         Random rand = new Random();                     // Inicializa a randomização
         Tree tree = new Tree();                         // Cria uma árvore binária
 
         for (int i = 0; i < 10; i++) {                  // Fazer 10 vezes
             tree.insert(rand.nextInt(100), tree.root);  // Insere um elemento aleatório de 0 até 99
        }

        System.out.println("\nPRE-ORDER:");
        tree.preOrder(tree.root);                       // Navega a árvore em pré ordem

        System.out.println("\nIN-ORDER:");
        tree.inOrder(tree.root);                        // Navega a árvore em ordem

        System.out.println("\nPOST-ORDER:");
        tree.postOrder(tree.root);                      // Navega a árvore em pós ordem
    }
}