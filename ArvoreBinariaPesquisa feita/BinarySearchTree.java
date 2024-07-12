import java.util.List;
import java.util.LinkedList;

public class BinarySearchTree<E extends Comparable<E>> {

    private static final class Node<E> {

        public Node<E> parent;
        public Node<E> left;
        public Node<E> right;
        public E element;

        public Node(E element) {
            parent = null;
            left = null;
            right = null;
            this.element = element;
        }

        // Retorna o grau do nó: 0, 1 ou 2
        public int degree() {
            int res = 0;
            if (left != null)
                res++;
            if (right != null)
                res++;
            return res;
        }
    }

    // Atributos
    private int count; // contagem do número de nodos
    private Node<E> root; // referência para o nodo raiz

    public BinarySearchTree() {
        clear();
    }

    public void clear() {
        count = 0;
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return count;
    }

    public E getRoot() {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        return root.element;
    }

    public void add(E element) {
        Node<E> aux = new Node<E>(element);
        if (root == null) { // Se for o primeiro elemento, cria como raiz e retorna
            root = aux;
            count++;
            return;
        }
        // Completar
        boolean inseriu = false;
        Node<E> atual = root;
        while (!inseriu) {
            if (element.compareTo(atual.element) < 0) {
                if (atual.left == null) {
                    atual.left = aux;
                    aux.parent = atual;
                    inseriu = true;
                } else
                    atual = atual.left;
            } else {
                if (atual.right == null) {
                    atual.right = aux;
                    aux.parent = atual;
                    inseriu = true;
                } else
                    atual = atual.right;
            }
        }
        count++;
    }

    private Node<E> searchNode(E element) {
        Node<E> atual = root;
        while (atual != null) {
            if (atual.element.equals(element))
                return atual;
            if (element.compareTo(atual.element) < 0)
                atual = atual.left;
            else
                atual = atual.right;
        }
        return null;
    }

    public boolean contains(E element) {
        if (searchNode(element) != null)
            return true;
        return false;
    }

    public boolean remove2(E value) {
        Node<E> aux;
        if (value == null || root == null)
            return false;
        aux = searchNode(value);
        if (aux == null)
            return false;
        remove2(aux);
        count--;
        return true;
    }

    private void remove2(Node<E> n) {
        Node<E> aux, m;
        aux = n.parent;
        System.out.println("Vou remover o " + n.element);
        if (n.left == null) {
            if (n.right == null) { // Nodo a excluir é folha?
                if (root == n) {
                    root = null;
                    return;
                }
                if (aux.left == n)
                    aux.left = null;
                else
                    aux.right = null;
                return;
            } else { // Esquerda nula e direita não
                if (root == n) {
                    root = n.right;
                    root.parent = null;
                    return;
                }
                if (aux.left == n) {
                    aux.left = n.right;
                    n.right.parent = aux;
                } else {
                    aux.right = n.right;
                    n.right.parent = aux;
                }
            }
            return;
        }

        if (n.right == null) { // Direita nula e esquerda não
            if (root == n) {
                root = n.left;
                root.parent = null;
                return;
            }
            if (aux.left == n) {
                aux.left = n.left;
                n.left.parent = aux;
            } else {
                aux.right = n.left;
                n.left.parent = aux;
            }
            return;
        }

        // Esquerda e direita não nulas
        m = smallest(n.right);
        System.out.println("Menor da direita: " + m.element);
        n.element = m.element;
        remove2(m);
    }

    /**
     * Retorna referência para o nodo que contém o menor elemento da subárvore
     * cuja raiz foi passada por parâmetro.
     * 
     * @param n nodo a partir do qual deve-se procurar o menor elemento.
     * @return referência para o nodo que contém o menor elemento.
     */
    private Node<E> smallest(Node<E> n) {
        while (n != null && n.left != null) {
            n = n.left;
        }
        return n;
    }

    public boolean remove(E value) {
        Node<E> parent = null, current = root;
        boolean hasLeft = false;

        if (root == null)
            return true;

        while (current != null) {
            if (current.element.equals(value)) {
                break;
            }

            parent = current;
            if (value.compareTo(current.element) < 0) {
                hasLeft = true;
                current = current.left;
            } else {
                hasLeft = false;
                current = current.right;
            }
        }
        if (current == null) // não existe
            return false;

        if (parent == null) {
            remove(current);
            return true;
        }

        if (hasLeft) {
            parent.left = remove(current);
        } else {
            parent.right = remove(current);
        }

        return true;
    }

    private Node<E> remove(Node<E> node) {

        if (node != null) {
            if (node.left == null && node.right == null) {
                return null;
            }

            if (node.left != null && node.right != null) {
                Node<E> inOrderSuccessor = removeDuplicate(node);
                node.element = inOrderSuccessor.element;
            } else if (node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return node;
    }

    private Node<E> removeDuplicate(Node<E> node) {
        Node<E> parent = node;
        node = node.right;
        boolean rightChild = node.left == null;

        while (node.left != null) {
            parent = node;
            node = node.left;
        }

        if (rightChild) {
            parent.right = node.right;
        } else {
            parent.left = node.right;
        }

        node.right = null;
        return node;
    }

    public List<E> positionsCentral() {
        List<E> res = new LinkedList<>();
        positionsCentralAux(root, res);
        return res;
    }

    private void positionsCentralAux(Node<E> n, List<E> res) {
        if (n != null) {
            positionsCentralAux(n.left, res); // Visita a subárvore da esquerda
            res.add(n.element); // Visita o nodo
            positionsCentralAux(n.right, res); // Visita a subárvore da direita
        }
    }

    private void geraConexoesDOT(StringBuilder sb, Node<E> nodo) {
        if (nodo == null) {
            return;
        }

        geraConexoesDOT(sb, nodo.left);
        // "nodeA":esq -> "nodeB" [color="0.650 0.700 0.700"]
        if (nodo.left != null) {
            sb.append("\"node" + nodo.element + "\":esq -> \"node" + nodo.left.element + "\" \n");
        }

        geraConexoesDOT(sb, nodo.right);
        // "nodeA":dir -> "nodeB";
        if (nodo.right != null) {
            sb.append("\"node" + nodo.element + "\":dir -> \"node" + nodo.right.element + "\" \n");
        }
        // "[label = " << nodo->hDir << "]" <<endl;
    }

    private void geraNodosDOT(StringBuilder sb, Node<E> nodo) {
        if (nodo == null) {
            return;
        }
        geraNodosDOT(sb, nodo.left);
        // node10[label = "<esq> | 10 | <dir> "];
        sb.append("node" + nodo.element + "[label = \"<esq> | " + nodo.element + " | <dir> \"]\n");
        geraNodosDOT(sb, nodo.right);
    }

    public void geraConexoesDOT(StringBuilder sb) {
        geraConexoesDOT(sb, root);
    }

    public void geraNodosDOT(StringBuilder sb) {
        geraNodosDOT(sb, root);
    }

    // Gera uma saida no formato DOT
    // Esta saida pode ser visualizada no GraphViz
    // Versoes online do GraphViz pode ser encontradas em
    // http://www.webgraphviz.com/
    // http://viz-js.com/
    // https://dreampuf.github.io/GraphvizOnline
    public String toDOT() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph g { \nnode [shape = record,height=.1];\n");
        geraNodosDOT(sb);
        geraConexoesDOT(sb);
        sb.append("}\n");
        return sb.toString();
    }


    // Método ancestral(E i, E j)
    public E ancestral(E i, E j) {
        Node<E> current = root;
        while (current != null) {
            if (i.compareTo(current.element) < 0 && j.compareTo(current.element) < 0) {
                current = current.left;
            } else if (i.compareTo(current.element) > 0 && j.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                return current.element;
            }
        }
        return null; 
    }

    // Método caminho(E val)
    public void caminho(E val) {
        caminhoAux(root, val);
    }

    private boolean caminhoAux(Node<E> node, E val) {
        if (node == null) {
            return false;
        }
        if (node.element.equals(val) || caminhoAux(node.left, val) || caminhoAux(node.right, val)) {
            System.out.print(node.element + " ");
            return true;
        }
        return false;
    }

    public int maxsoma() {
        return maxsoma(root);
    }
    
    private int maxsoma(Node<E> node) {
        if (node == null) {
            return 0;
        }
    
        int leftSum = maxsoma(node.left);
        int rightSum = maxsoma(node.right);
    
        int nodeValue = Integer.parseInt(node.element.toString());
    
        return nodeValue + Math.max(leftSum, rightSum);
    }

    // Método isABP()
    public boolean isABP() {
        return isABPAux(root, null, null);
    }

    private boolean isABPAux(Node<E> node, E min, E max) {
        if (node == null) {
            return true;
        }
        if ((min != null && node.element.compareTo(min) <= 0) || (max != null && node.element.compareTo(max) >= 0)) {
            return false;
        }
        return isABPAux(node.left, min, node.element) && isABPAux(node.right, node.element, max);
    }
}

class EmptyTreeException extends RuntimeException {
    public EmptyTreeException(String message) {
        super(message);
    }

    public EmptyTreeException() {
    }
}