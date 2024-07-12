#ifndef _ABP
#define _ABP

#include <iostream>
#include <sstream>
#include <string>
#include <list>

#include "NodoAB.h"

using namespace std;

template <class T>
class BinarySearchTree
{

public:
    BinarySearchTree();
    void clear();
    bool isEmpty();
    int size();
    T getRoot();
    void add(T elem);
    bool contains(T elem);
    bool remove(T elem);
    list<T> positionsCentral();
    string toDOT();

private:
    NodoAB<T> *root;
    int count;

    void clear(BinarySearchTree<T> *tree); // recursive clear
    void remove(NodoAB<T> *n);             // recursive remove
    NodoAB<T> *searchNode(T elem);
    NodoAB<T> *smallest(NodoAB<T> *n);
    void positionsCentralAux(NodoAB<T> *n, list<T> &lista);
    void geraConexoesDOT(ostringstream &aux);
    void geraNodosDOT(ostringstream &aux);
    void geraConexoesDOT(ostringstream &aux, NodoAB<T> *nodo);
    void geraNodosDOT(ostringstream &aux, NodoAB<T> *nodo);

public:
};

template <class T>
BinarySearchTree<T>::BinarySearchTree()
{
    root = nullptr;
    count = 0;
}

/**
 * Remove todos os elementos da arvore.
 */
template <class T>
void BinarySearchTree<T>::clear()
{
    clear(root);
    root = nullptr;
    count = 0;
}

template <class T>
void BinarySearchTree<T>::clear(BinarySearchTree<T> *tree)
{
    if (tree->left != nullptr)
        clear(tree->left);
    if (tree->right != nullptr)
        clear(tree->right);
    // cout << "clear: "<< tree->item << endl;
    delete tree;
}

/**
 * Verifica se a arvore esta vazia ou nao.
 * @return true se arvore vazia e false caso contrario.
 */
template <class T>
bool BinarySearchTree<T>::isEmpty()
{
    return (root == nullptr);
}

/**
 * Retorna o total de elementos da arvore.
 * @return total de elementos
 */
template <class T>
int BinarySearchTree<T>::size()
{
    return count;
}

/**
 * Retorna o elemento armazenado na raiz da arvore.
 * @throw excecao se arvore vazia.
 * @return elemento da raiz.
 */
template <class T>
T BinarySearchTree<T>::getRoot()
{
    if (isEmpty())
    {
        throw 0;
    }
    return root->element;
}

/**
 * Insere o elemento na arvore.
 * @param element a ser inserido.
 */
template <class T>
void BinarySearchTree<T>::add(T element)
{
    NodoAB<T> *aux = new NodoAB<T>(element);
    if (root == nullptr)
    { // Se for o primeiro nó, cria como raiz e retorna
        root = aux;
        count++;
        return;
    }
    // Completar
    NodoAB<T> *atual = root;
    bool inseriu = false;
    while (!inseriu)
    {
        if (element < atual->element)
        {
            if (atual->left == nullptr)
            {
                atual->left = aux;
                aux->parent = atual;
                inseriu = true;
            }
            else
                atual = atual->left;
        }
        else
        {
            if (atual->right == nullptr)
            {
                atual->right = aux;
                aux->parent = atual;
                inseriu = true;
            }
            else
                atual = atual->right;
        }
        count++;
    }
}

/**
 * Verifica se element esta ou nao armazenado na arvore.
 * @param element
 * @return true se element estiver na arvore, false caso contrario.
 */
template <class T>
bool BinarySearchTree<T>::contains(T elem)
{
    if (searchNode(elem) != nullptr)
        return true;
    return false;
}

template <class T>
NodoAB<T> *BinarySearchTree<T>::searchNode(T elem)
{
    NodoAB<T> *atual = root;
    while (atual != nullptr)
    {
        if (atual->element == elem)
            return atual;
        if (elem < atual->element)
            atual = atual->left;
        else
            atual = atual->right;
    }
    return nullptr;
}

template <class T>
bool BinarySearchTree<T>::remove(T elem)
{
    NodoAB<T> *aux;
    if (root == nullptr)
        return false;
    aux = searchNode(elem);
    if (aux == nullptr)
        return false;
    remove(aux);
    count--;
    return true;
}

template <class T>
void BinarySearchTree<T>::remove(NodoAB<T> *n)
{
    NodoAB<T> *aux, *m;
    aux = n->parent;
    if (n->left == nullptr)
    {
        if (n->right == nullptr)
        { // Nodo a excluir é folha?
            if (root == n)
                root = nullptr;
            else if (aux->left == n)
                aux->left = nullptr;
            else
                aux->right = nullptr;
            delete n; // libera mem.
            return;
        }
        else
        { // Esquerda nula e direita não
            if (root == n)
            {
                delete root; // libera mem.
                root = n->right;
                root->parent = nullptr;
                return;
            }
            if (aux->left == n)
            {
                aux->left = n->right;
                n->right->parent = aux;
            }
            else
            {
                aux->right = n->right;
                n->right->parent = aux;
            }
        }
        delete n; // libera mem.
        return;
    }

    if (n->right == nullptr)
    { // Direita nula e esquerda não
        if (root == n)
        {
            delete root; // libera mem.
            root = n->left;
            root->parent = nullptr;
            return;
        }
        if (aux->left == n)
        {
            aux->left = n->left;
            n->left->parent = aux;
        }
        else
        {
            aux->right = n->left;
            n->left->parent = aux;
        }
        delete n; // libera mem.
        return;
    }

    // Esquerda e direita não nulas
    m = smallest(n->right);
    n->element = m->element;
    remove(m);
}

template <class T>
NodoAB<T> *BinarySearchTree<T>::smallest(NodoAB<T> *n)
{
    while (n != nullptr && n->left != nullptr)
        n = n->left;
    return n;
}

/**
 * Retorna uma lista com todos os elementos da arvore na ordem do
 * caminhamento central.
 * @return lista com todos os elementos da arvore.
 */
template <class T>
list<T> BinarySearchTree<T>::positionsCentral()
{
    list<T> lista;
    positionsCentralAux(root, lista);
    return lista;
}

template <class T>
void BinarySearchTree<T>::positionsCentralAux(NodoAB<T> *n, list<T> &lista)
{
    if (n != nullptr)
    {
        // Visita subarvore da esquerda
        positionsCentralAux(n->left, lista);
        // Visita a raiz
        lista.push_back(n->element);
        // Visita subarvore da direita
        positionsCentralAux(n->right, lista);
    }
}

template <class T>
void BinarySearchTree<T>::geraConexoesDOT(ostringstream &aux, NodoAB<T> *nodo)
{
    if (nodo == nullptr)
        return;

    geraConexoesDOT(aux, nodo->left);
    //   "nodeA":esq -> "nodeB" [color="0.650 0.700 0.700"]
    if (nodo->left)
        aux << "\"node" << nodo->element << "\":esq -> \"node" << nodo->left->element << "\" " <<
            //"[label = " << nodo->hEsq << "]" << endl;
            endl;
    geraConexoesDOT(aux, nodo->right);
    //   "nodeA":dir -> "nodeB";
    if (nodo->right)
        aux << "\"node" << nodo->element << "\":dir -> \"node" << nodo->right->element << "\" " <<
            //"[label = " << nodo->hDir << "]" <<endl;
            endl;
}

template <class T>
void BinarySearchTree<T>::geraNodosDOT(ostringstream &aux, NodoAB<T> *nodo)
{
    if (nodo == nullptr)
        return;
    geraNodosDOT(aux, nodo->left);
    // node10[label = "<esq> | 10 | <dir> "];
    aux << "node" << nodo->element << "[label = \"<esq> | " << nodo->element << " | <dir> \"]" << endl;
    geraNodosDOT(aux, nodo->right);
}

template <class T>
void BinarySearchTree<T>::geraConexoesDOT(ostringstream &aux)
{
    geraConexoesDOT(aux, root);
}

template <class T>
void BinarySearchTree<T>::geraNodosDOT(ostringstream &aux)
{
    geraNodosDOT(aux, root);
}

template <class T>
string BinarySearchTree<T>::toDOT()
{
    ostringstream tmp;
    tmp << "digraph g { \nnode [shape = record,height=.1];\n"
        << endl;
    // node [style=filled];

    geraNodosDOT(tmp);
    tmp << endl;
    geraConexoesDOT(tmp);
    tmp << "}" << endl;
    return tmp.str();
}
#endif
