package ed.view;


import java.util.Scanner;

import ed.tad.ListaDuplamenteLigadaCirgular;
import ed.tad.No;

public class Principal {

    static ListaDuplamenteLigadaCirgular l2lc;

    public static void main(String[] args) {

        l2lc = new ListaDuplamenteLigadaCirgular();

        String msg_or = "OPERAÇÃO REALIZADA COM SUCESSO.";
        String msg_of = "OPERAÇÃO FALHOU!";
        String msg_er = "Elemento removido: ";

        OUTER:
        while (true) {
            System.out.println("LISTA DUPLAMENTE LIGADA CIRCULAR");
            System.out.println("Quantidade de elementos: " + l2lc.getQtd());
            System.out.println(" [0] - Encerrar");
            System.out.println(" [1] - Inserir um elemento no inicio");
            System.out.println(" [2] - Inserir um elemento no final");
            System.out.println(" [3] - Inserir um elemento em uma posição genérica no sentido horário");
            System.out.println(" [4] - Inserir um elemento em uma posição genérica no sentido anti-horário");
            System.out.println(" [5] - Remover um elemento do inicio");
            System.out.println(" [6] - Remover um elemento do final");
            System.out.println(" [7] - Remover um elemento de uma posição genérica no sentido horário");
            System.out.println(" [8] - Remover um elemento de uma posição genérica no sentido anti-horário");
            System.out.println(" [9] - Imprimir no sentido horário");
            System.out.println("[10] - Imprimir no sentido anti-horário");
            System.out.println("[99] - Imprimir 15 elementos.");
            System.out.print("\nOpção: ");
            Scanner scn = new Scanner(System.in);

            try {
                int opc = scn.nextByte();
                if (opc == 0) {
                    break;
                } else if (opc == 1) {
                    No e = _criaElem();
                    l2lc.insereInicio(e);
                    System.out.println(msg_or);
                } else if (opc == 2) {
                	No e = _criaElem();
                    l2lc.insereUltimo(e);
                    System.out.println(msg_or);
                } else if (opc == 3) {
                    int pos = _posicao();
                    No eNovo = _criaElem();
                    No eAtual = l2lc.getPosHorario(l2lc.getInicio(), pos);
                    if (l2lc.insereHorario(eNovo, eAtual)) {
                        System.out.println(msg_or);
                    } else {
                        System.out.println(msg_of);
                    }
                } else if (opc == 4) {
                    int pos = _posicao();
                    No eNovo = _criaElem();
                    No eAtual = l2lc.getPosAntiHorario(l2lc.getInicio(), pos);
                    if (l2lc.insereAntihorario(eNovo, eAtual)) {
                        System.out.println(msg_or);
                    } else {
                        System.out.println(msg_of);
                    }
                } else if (opc == 5 || opc == 6) {
                	No e = null;
                    if (opc == 5) {
                        e = l2lc.removeInicio();
                    } else if (opc == 6) {
                        e = l2lc.removeFim();
                    }
                    if (e != null) {
                        System.out.println(msg_er);
                        System.out.print(msg_er);
                        l2lc._imprimeElemento(e);
                    } else {
                        System.out.println(msg_of);
                    }
                } else if (opc == 7 || opc == 8) {
                    int pos = _posicao();
                    No e = null;
                    if (opc == 7) {
                        e = l2lc.getPosHorario(l2lc.getInicio(), pos);
                    } else if (opc == 8) {
                        e = l2lc.getPosAntiHorario(l2lc.getInicio(), pos);
                    }
                    l2lc.removeElemento(e);
                    if (e != null) {
                        System.out.println(msg_er);
                        System.out.print(msg_er);
                        l2lc._imprimeElemento(e);
                    } else {
                        System.out.println(msg_of);
                    }

                } else if (opc == 9) {
                    l2lc.imprimeHorario();
                } else if (opc == 10) {
                    l2lc.imprimeAntiHorario();
                } else if (opc == 99) {
                	No e = l2lc.getInicio();
                    for (int i = 1; i <= 15; i++) {
                        l2lc._imprimeElemento(e);
                        e = e.getProximo();
                    }
                }
            } catch (Exception ex) {
                System.out.println(msg_of + "\n(mensagem do sistema: " + ex.getMessage() + ")");
            }
        }

    }

    private static No _criaElem() {
        Scanner scn = new Scanner(System.in);
        System.out.print("Informe o ID do elemento -> ");
        int id = scn.nextInt();
        No e = new No();
        e.setId(id);
        return e;
    }

    private static int _posicao() {
        Scanner scn = new Scanner(System.in);
        System.out.println("AVISO: a posição nao pode ser maior que a quantidade de elementos.");
        System.out.print("Informe a posição desejada -> ");
        return scn.nextInt();
    }

}
