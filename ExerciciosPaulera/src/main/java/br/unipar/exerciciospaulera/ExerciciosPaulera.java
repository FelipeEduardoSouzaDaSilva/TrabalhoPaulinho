package br.unipar.exerciciospaulera;

import javax.swing.JOptionPane;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ExerciciosPaulera {

    public static void main(String[] args) {
        boolean sair = false;
        while (!sair) {
            int opcao = Integer.parseInt(JOptionPane.showInputDialog("Selecione a opção:\n1 - Soma de 0 a 50\n2 - Fatorial de um número\n3 - Sequência Fibonacci\n4 - Fila de pacientes\n5 - Fila de pagamentos\n6 - Pesquisa de números\n7 - Cadastro de clientes\n9 - Sair"));
            

            switch (opcao) {
                case 1:
                    int soma = Soma.somaDeZeroAte50();
                    JOptionPane.showMessageDialog(null, "A soma de 0 a 50 é: " + soma);
                    break;
                case 2:
                    int numeroFatorial = Integer.parseInt(JOptionPane.showInputDialog("Informe um número para calcular o fatorial:"));
                    int fatorial = Fatorial.calcularFatorial(numeroFatorial);
                    JOptionPane.showMessageDialog(null, "O fatorial de " + numeroFatorial + " é: " + fatorial);
                    break;
                case 3:
                    int numeroFibonacci = Integer.parseInt(JOptionPane.showInputDialog("Informe um número para obter a sequência Fibonacci:"));
                    StringBuilder sequenciaFibonacci = new StringBuilder();
                    for (int i = 0; i <= numeroFibonacci; i++) {
                        sequenciaFibonacci.append(Fibonacci.fibonacci(i)).append(", ");
                    }
                    JOptionPane.showMessageDialog(null, "Sequência Fibonacci até " + numeroFibonacci + ":\n" + sequenciaFibonacci.toString());
                    break;
                case 4:
                    Queue<String> filaPacientes = new LinkedList<>();

                    while (true) {
                        int opcaoFilaPacientes = Integer.parseInt(JOptionPane.showInputDialog("Selecione a opção:\n1 - Adicionar paciente\n2 - Chamar próximo paciente"));

                        if (opcaoFilaPacientes == 1) {
                            String nome = JOptionPane.showInputDialog("Informe o nome do paciente:");
                            filaPacientes.add(nome);
                        } else if (opcaoFilaPacientes == 2) {
                            if (filaPacientes.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "A fila está vazia.");
                            } else {
                                String proximoPaciente = filaPacientes.poll();
                                JOptionPane.showMessageDialog(null, "Próximo paciente: " + proximoPaciente);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Opção inválida.");
                        }
                    }
                case 5:
                    Queue<String> filaPrioritaria = new LinkedList<>();
                    Queue<String> filaNormal = new LinkedList<>();
                    int contadorPrioritarios = 0;
                    int contadorNormais = 0;

                    while (true) {
                        int opcaoFilaPagamentos = Integer.parseInt(JOptionPane.showInputDialog("Selecione a opção:\n1 - Adicionar cliente\n2 - Chamar cliente"));

                        if (opcaoFilaPagamentos == 1) {
                            String nome = JOptionPane.showInputDialog("Informe o nome do cliente:");
                            int anoNascimento = Integer.parseInt(JOptionPane.showInputDialog("Informe o ano de nascimento do cliente:"));

                            if (anoNascimento <= 65) {
                                filaNormal.add(nome);
                                contadorNormais++;
                            } else {
                                filaPrioritaria.add(nome);
                                contadorPrioritarios++;
                            }
                        } else if (opcaoFilaPagamentos == 2) {
                            if (contadorPrioritarios >= 2) {
                                String proximoCliente = filaPrioritaria.poll();
                                JOptionPane.showMessageDialog(null, "Próximo cliente (prioritário): " + proximoCliente);
                                contadorPrioritarios--;
                            } else if (!filaPrioritaria.isEmpty()) {
                                String proximoCliente = filaPrioritaria.poll();
                                JOptionPane.showMessageDialog(null, "Próximo cliente (prioritário): " + proximoCliente);
                                contadorPrioritarios--;
                            } else if (contadorNormais >= 1) {
                                String proximoCliente = filaNormal.poll();
                                JOptionPane.showMessageDialog(null, "Próximo cliente: " + proximoCliente);
                                contadorNormais--;
                            } else if (!filaNormal.isEmpty()) {
                                String proximoCliente = filaNormal.poll();
                                JOptionPane.showMessageDialog(null, "Próximo cliente: " + proximoCliente);
                                contadorNormais--;
                            } else {
                                JOptionPane.showMessageDialog(null, "A fila está vazia.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Opção inválida.");
                        }
                    }
                case 6:
                    int quantidadeNumeros = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade de números a serem cadastrados:"));
                    int[] numeros = new int[quantidadeNumeros];

                    for (int i = 0; i < quantidadeNumeros; i++) {
                        numeros[i] = Integer.parseInt(JOptionPane.showInputDialog("Informe o número " + (i + 1) + ":"));
                    }

                    Arrays.sort(numeros);

                    int opcaoPesquisa = Integer.parseInt(JOptionPane.showInputDialog("Selecione a opção:\n1 - Pesquisa linear\n2 - Pesquisa binária"));
                    int numeroPesquisado = Integer.parseInt(JOptionPane.showInputDialog("Informe o número a ser pesquisado:"));

                    int indice = -1;

                    if (opcaoPesquisa == 1) {
                        indice = PesquisaNumeros.pesquisaLinear(numeros, numeroPesquisado);
                    } else if (opcaoPesquisa == 2) {
                        indice = PesquisaNumeros.pesquisaBinaria(numeros, numeroPesquisado);
                    } else {
                        JOptionPane.showMessageDialog(null, "Opção inválida.");
                    }

                    if (indice != -1) {
                        JOptionPane.showMessageDialog(null, "O número " + numeroPesquisado + " foi encontrado na posição " + (indice + 1) + ".");
                    } else {
                        JOptionPane.showMessageDialog(null, "O número " + numeroPesquisado + " não foi encontrado.");
                    }
                    break;
                case 7:
                    int quantidadeClientes = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade de clientes a serem cadastrados:"));
                    Cliente[] clientes = new Cliente[quantidadeClientes];

                    for (int i = 0; i < quantidadeClientes; i++) {
                        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Informe o código do cliente " + (i + 1) + ":"));
                        String nome = JOptionPane.showInputDialog("Informe o nome do cliente " + (i + 1) + ":");
                        String dataNascimento = JOptionPane.showInputDialog("Informe a data de nascimento do cliente " + (i + 1) + ":");
                        String cpf = JOptionPane.showInputDialog("Informe o CPF do cliente " + (i + 1) + ":");

                        Cliente cliente = new Cliente(codigo, nome, dataNascimento, cpf);
                        clientes[i] = cliente;
                    }

                    Arrays.sort(clientes);

                    int codigoClientePesquisado = Integer.parseInt(JOptionPane.showInputDialog("Informe o código do cliente a ser pesquisado:"));

                    int indiceCliente = PesquisaClientes.pesquisaBinaria(clientes, codigoClientePesquisado);

                    if (indiceCliente != -1) {
                        Cliente clientePesquisado = clientes[indiceCliente];
                        JOptionPane.showMessageDialog(null, "Cliente encontrado:\nCódigo: " + clientePesquisado.getCodigo()
                                + "\nNome: " + clientePesquisado.getNome()
                                + "\nData de Nascimento: " + clientePesquisado.getDataNascimento()
                                + "\nCPF: " + clientePesquisado.getCpf());
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                    }
                    break;
                case 9:
                    sair = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
    }
}

class Soma {

    public static int somaDeZeroAte50() {
        return somaDeZeroAteN(50);
    }

    private static int somaDeZeroAteN(int n) {
        if (n == 0) {
            return 0;
        } else {
            return n + somaDeZeroAteN(n - 1);
        }
    }
}

class Fatorial {

    public static int calcularFatorial(int numero) {
        return calcularFatorialRecursivo(numero);
    }

    private static int calcularFatorialRecursivo(int numero) {
        if (numero == 0) {
            return 1;
        } else {
            return numero * calcularFatorialRecursivo(numero - 1);
        }
    }
}

class Fibonacci {

    public static int fibonacci(int numero) {
        return calcularFibonacciRecursivo(numero);
    }

    private static int calcularFibonacciRecursivo(int numero) {
        if (numero == 0) {
            return 0;
        } else if (numero == 1 || numero == 2) {
            return 1;
        } else {
            return calcularFibonacciRecursivo(numero - 1) + calcularFibonacciRecursivo(numero - 2);
        }
    }
}

class PesquisaNumeros {

    public static int pesquisaLinear(int[] numeros, int numeroPesquisado) {
        return pesquisaLinearRecursiva(numeros, numeroPesquisado, 0);
    }

    private static int pesquisaLinearRecursiva(int[] numeros, int numeroPesquisado, int indice) {
        if (indice >= numeros.length) {
            return -1;
        } else if (numeros[indice] == numeroPesquisado) {
            return indice;
        } else {
            return pesquisaLinearRecursiva(numeros, numeroPesquisado, indice + 1);
        }
    }

    public static int pesquisaBinaria(int[] numeros, int numeroPesquisado) {
        return pesquisaBinariaRecursiva(numeros, numeroPesquisado, 0, numeros.length - 1);
    }

    private static int pesquisaBinariaRecursiva(int[] numeros, int numeroPesquisado, int esquerda, int direita) {
        if (esquerda > direita) {
            return -1;
        }

        int meio = (esquerda + direita) / 2;

        if (numeros[meio] == numeroPesquisado) {
            return meio;
        }

        if (numeros[meio] < numeroPesquisado) {
            return pesquisaBinariaRecursiva(numeros, numeroPesquisado, meio + 1, direita);
        } else {
            return pesquisaBinariaRecursiva(numeros, numeroPesquisado, esquerda, meio - 1);
        }
    }
}

class Cliente implements Comparable<Cliente> {

    private int codigo;
    private String nome;
    private String dataNascimento;
    private String cpf;

    public Cliente(int codigo, String nome, String dataNascimento, String cpf) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public int compareTo(Cliente outroCliente) {
        return this.codigo - outroCliente.codigo;
    }
}

class PesquisaClientes {

    public static int pesquisaBinaria(Cliente[] clientes, int codigoClientePesquisado) {
        return pesquisaBinariaRecursiva(clientes, codigoClientePesquisado, 0, clientes.length - 1);
    }

    private static int pesquisaBinariaRecursiva(Cliente[] clientes, int codigoClientePesquisado, int esquerda, int direita) {
        if (esquerda > direita) {
            return -1;
        }

        int meio = (esquerda + direita) / 2;

        if (clientes[meio].getCodigo() == codigoClientePesquisado) {
            return meio;
        }

        if (clientes[meio].getCodigo() < codigoClientePesquisado) {
            return pesquisaBinariaRecursiva(clientes, codigoClientePesquisado, meio + 1, direita);
        } else {
            return pesquisaBinariaRecursiva(clientes, codigoClientePesquisado, esquerda, meio - 1);
        }
    }
}
