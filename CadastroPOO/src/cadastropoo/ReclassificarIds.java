/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastropoo;

import java.io.*;
import java.util.List;

class PessoaFisica implements Serializable {
    private static final long serialVersionUID = 123456789L; // UID 123456789L /932067521
    private static int nextId = 1;
    private int id;
    private String nome;
    private String cpf;
    private int idade;

    public PessoaFisica(String nome, String cpf, int idade) {
        this.id = nextId++;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nNome: " + nome + "\nCPF: " + cpf + "\nIdade: " + idade + "\n";
    }
}

public class ReclassificarIds {
    public static void main(String[] args) {
        List<PessoaFisica> pessoas = lerPessoas();

        if (pessoas != null) {
            // Reclassificar os IDs
            for (int i = 0; i < pessoas.size(); i++) {
                pessoas.get(i).setId(i + 1);
            }

            salvarPessoas(pessoas);
            System.out.println("IDs reclassificados e arquivo salvo com sucesso!");
        } else {
            System.out.println("Não foi possível ler o arquivo de pessoas.");
        }
    }

    private static List<PessoaFisica> lerPessoas() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("pessoas.bin"))) {
            return (List<PessoaFisica>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler o arquivo de pessoas: " + e.getMessage());
            return null;
        }
    }

    private static void salvarPessoas(List<PessoaFisica> pessoas) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("pessoas.bin"))) {
            outputStream.writeObject(pessoas);
            System.out.println("Arquivo salvo com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
}