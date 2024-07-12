#include <iostream>

using namespace std;

#include "BinarySearchTree.h"

void imprimeLista(list<int> &L, string msg)
{
    list<int>::iterator k;

    cout << msg;
    for (k = L.begin(); k != L.end(); k++)
    {
        cout << *k << " ";
    }
    cout << endl;
}

int main()
{
    BinarySearchTree<int> b;
    b.add(15);
    b.add(23);
    b.add(9);
    b.add(11);
    b.add(2);
    b.add(20);
    // b.add(38);
    b.add(17);

    list<int> L;

    L = b.positionsCentral();
    imprimeLista(L, "Varredura Central:\n");

    cout << "b contem 2? " << b.contains(2) << endl;
    cout << "b contem 12? " << b.contains(12) << endl;
    cout << "b contem 55? " << b.contains(55) << endl;
    cout << "b contem 38? " << b.contains(38) << endl;

    cout << "----------------" << endl;
    cout << b.toDOT();

    if (b.remove(23))
        cout << "23 removido!" << endl;
    else
        cout << "Não tem 23!!!" << endl;
    cout << "-------------" << endl;
    cout << b.toDOT();
    if (b.remove(15))
        cout << "15 removido!" << endl;
    else
        cout << "Não tem 15!!!" << endl;
    cout << "-------------" << endl;
    cout << b.toDOT() << endl;
    cout << "-------------" << endl;

    srand(42);
    for (int i = 0; i < 100; i++)
        b.add(rand() % 10000);
    cout << b.toDOT() << endl;
    cout << "Removendo 6166" << endl;
    b.remove(6166);
    cout << "-------------" << endl;
    cout << b.toDOT() << endl;
}
