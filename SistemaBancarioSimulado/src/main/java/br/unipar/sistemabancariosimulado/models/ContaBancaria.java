package br.unipar.sistemabancariosimulado.models;


public class ContaBancaria {
        private int numeroConta;
        private String titularConta; 
        private double saldoInicial;

    public ContaBancaria(int numeroConta, String titularConta, double saldoInicial) {
        this.numeroConta = numeroConta;
        this.titularConta = titularConta;
        this.saldoInicial = saldoInicial;
    }

    public ContaBancaria() {
    }


    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getTitularConta() {
        return titularConta;
    }

    public void setTitularConta(String titularConta) {
        this.titularConta = titularConta;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    @Override
    public String toString() {
        return "ContaBancaria{" + "numeroConta=" + numeroConta + ", titularConta=" + titularConta + ", saldoInicial=" + saldoInicial + '}';
    }
        
    
}
