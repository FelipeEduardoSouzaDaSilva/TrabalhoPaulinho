/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package br.unipar.sistemabancariosimulado;

import br.unipar.sistemabancariosimulado.models.ContaBancaria;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author 00239689
 */
public class SistemaBancarioSimulado {

    private static ContaBancaria[] contasCadastradas;
    private static int qtdContas;

    public static void main(String[] args) {


        int opcao;
        do {

            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
    }

 

    private static int lerOpcao() {

        return Integer.parseInt(JOptionPane.showInputDialog("Bem-vindo ao Sistema de Conta Bancária!\n\n"
                +"1. Cadastrar nova conta bancária\n"
                + "2. Exibir contas bancárias ordenadas\n"
                + "3. Realizar depósito\n"
                + "4. Realizar saque\n"
                + "5. Calcular saldo total do banco\n"
                + "6. Verificar saldo negativo\n"
                + "0. Sair\n"
                + "Digite a opção desejada:"));
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarConta();
                break;
            case 2:
                exibirContasOrdenadas();
                break;
            case 3:
                realizarDeposito();
                break;
            case 4:
                realizarSaque();
                break;
            case 5:
                calcularSaldoTotal();
                break;
            case 6:
                verificarSaldoNegativo();
                break;
            case 0:
                JOptionPane.showMessageDialog(null, "Saindo do sistema...");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida! Tente novamente.");
        }
    }

    private static void cadastrarConta() {

        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("\nDigite o número da conta: "));
        //JOptionPane.nextLine();
        String nomeTitular = JOptionPane.showInputDialog("Digite o nome do titular: ");
        double saldo = Double.parseDouble(JOptionPane.showInputDialog("Digite o saldo inicial: "));

        if (contasCadastradas == null) {
            contasCadastradas = new ContaBancaria[1];
            contasCadastradas[0] = new ContaBancaria(numeroConta, nomeTitular, saldo);
        } else {
            contasCadastradas = Arrays.copyOf(contasCadastradas, qtdContas + 1);
            contasCadastradas[qtdContas] = new ContaBancaria(numeroConta, nomeTitular, saldo);
        }
        qtdContas++;

        JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso!");
    }

    private static void exibirContasOrdenadas() {
        if (contasCadastradas == null || qtdContas == 0) {
            JOptionPane.showMessageDialog(null, "Não há contas cadastradas!");
        } else {
            ordenarContas();

            String msg = "\n";
            for (ContaBancaria conta : contasCadastradas) {
                msg += "\nNúmero da conta: " + conta.getNumeroConta()
                        + "\nNome do titular: " + conta.getTitularConta()
                        + "\nSaldo: " + conta.getSaldoInicial();

            }
            JOptionPane.showMessageDialog(null, "\nContas bancárias ordenadas:" + msg);
        }
    }

   private static void ordenarContas() {
    int n = qtdContas;
    boolean troca;
    
    do {
        troca = false;
        
        for (int i = 0; i < n - 1; i++) {
            if (contasCadastradas[i].getNumeroConta() > contasCadastradas[i + 1].getNumeroConta()) {
                // Troca de posições
                ContaBancaria temp = contasCadastradas[i];
                contasCadastradas[i] = contasCadastradas[i + 1];
                contasCadastradas[i + 1] = temp;
                
                troca = true;
            }
        }
        
        n--;
    } while (troca);
}

    private static void realizarDeposito() {
        if (contasCadastradas == null || qtdContas == 0) {
            JOptionPane.showMessageDialog(null, "Não há contas cadastradas!");
            return;
        }

        String termoPesquisa = JOptionPane.showInputDialog("\nDigite o número da conta ou o nome do titular:");

        boolean contaEncontrada = false;
        for (ContaBancaria conta : contasCadastradas) {
            if (Integer.toString(conta.getNumeroConta()).equals(termoPesquisa) || conta.getTitularConta().equalsIgnoreCase(termoPesquisa)) {
                double valorDeposito = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do depósito: "));
                conta.setSaldoInicial(conta.getSaldoInicial() + valorDeposito);
                contaEncontrada = true;
                break;
            }
        }

        if (!contaEncontrada) {
            JOptionPane.showMessageDialog(null, "Nenhuma conta encontrada com o número ou nome fornecido!");
        } else {
            JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!");
        }
    }

    private static void realizarSaque() {
        if (contasCadastradas == null || qtdContas == 0) {
            JOptionPane.showMessageDialog(null, "Não há contas cadastradas!");
            return;
        }

        ordenarContas();

        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("\nDigite o número da conta: "));

        int indiceConta = buscaBinaria(numeroConta);

        if (indiceConta == -1) {
            JOptionPane.showMessageDialog(null, "Conta não encontrada!");
        } else {
            ContaBancaria conta = contasCadastradas[indiceConta];
            double valorSaque = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do saque: "));

            if (valorSaque > conta.getSaldoInicial()) {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
            } else {
                conta.setSaldoInicial(conta.getSaldoInicial() - valorSaque);
                JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
            }
        }
    }

    private static int buscaBinaria(int numeroConta) {
        int esquerda = 0;
        int direita = qtdContas - 1;

        while (esquerda <= direita) {
            int meio = (esquerda + direita) / 2;
            int numeroContaMeio = contasCadastradas[meio].getNumeroConta();

            if (numeroContaMeio == numeroConta) {
                return meio;
            } else if (numeroContaMeio < numeroConta) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }

        return -1;
    }

    private static void calcularSaldoTotal() {
        double saldoTotal = calcularSaldoTotalRecursivo(0);

        JOptionPane.showMessageDialog(null, "\nSaldo total do banco: " + saldoTotal);
    }

    private static double calcularSaldoTotalRecursivo(int indice) {
        if (indice == qtdContas - 1) {
            return contasCadastradas[indice].getSaldoInicial();
        }

        return contasCadastradas[indice].getSaldoInicial() + calcularSaldoTotalRecursivo(indice + 1);
    }

    private static void verificarSaldoNegativo() {
        if (contasCadastradas == null || qtdContas == 0) {
            JOptionPane.showMessageDialog(null, "Não há contas cadastradas!");
        } else {
            boolean saldoNegativoEncontrado = verificarSaldoNegativoRecursivo(0);

            if (saldoNegativoEncontrado) {
                JOptionPane.showMessageDialog(null, "\nSaldo negativo encontrado em uma das contas!");
            } else {
                JOptionPane.showMessageDialog(null, "\nTodas as contas possuem saldo positivo.");
            }
        }
    }

    private static boolean verificarSaldoNegativoRecursivo(int indice) {
        if (indice == qtdContas - 1) {
            return contasCadastradas[indice].getSaldoInicial() < 0;
        }

        if (contasCadastradas[indice].getSaldoInicial() < 0) {
            JOptionPane.showMessageDialog(null, "Conta com saldo negativo:" + "\n"
                    + "Número da conta: " + contasCadastradas[indice].getNumeroConta() + "\n"
                    + "Nome do titular: " + contasCadastradas[indice].getTitularConta() + "\n"
                    + "Saldo: " + contasCadastradas[indice].getSaldoInicial());

            return true;
        }

        return verificarSaldoNegativoRecursivo(indice + 1);
    }
    

}
