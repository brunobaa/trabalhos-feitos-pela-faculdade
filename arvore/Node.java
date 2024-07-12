class Node {                    // Classe do nó
     int info;                   // Informação tipo inteiro
     Node left;                  // Filho à esquerda
     Node right;                 // FIlho à direita
 
     Node(int info) {            // Inicializa os atributos da classe
         this.info = info;       // Inicializa a informação com o parâmetro recebido
         this.left = null;       // Inicializa o filho à esquerda como vazio
         this.right = null;      // Inicializa o filho à direita como vazio
    }
 }