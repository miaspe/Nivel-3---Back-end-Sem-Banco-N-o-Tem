/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastropoo;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class PessoaJuridica implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private static int nextId = 1;
    private int id;
    private String nome;
    private String cnpj; // Alterado de cpf para cnpj

    public PessoaJuridica(String nome, String cnpj) {
        this.id = nextId++;
        this.nome = nome;
        this.cnpj = cnpj;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nNome: " + nome + "\nCNPJ: " + cnpj + "\n";
    }
}

public class ExcluirRegistroJ {
    public static void main(String[] args) {
        List<PessoaJuridica> pessoas = lerPessoasJ(); // Alterado o tipo da lista
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Digite o ID da Empresa para excluir (ou 'sair' para encerrar): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("sair")) {
                salvarPessoasJ(pessoas); // Alterado o nome do método de salvar
                break;
            }

            int id = Integer.parseInt(input);
            Iterator<PessoaJuridica> iterator = pessoas.iterator();

            boolean encontrada = false;
            while (iterator.hasNext()) {
                PessoaJuridica pessoa = iterator.next();
                if (pessoa.getId() == id) {
                    iterator.remove();
                    encontrada = true;
                    System.out.println("Empresa excluída com sucesso!");
                    break;
                }
            }

            if (!encontrada) {
                System.out.println("ID fornecido não encontrado ou já excluído.");
            }
        }
    }

    private static List<PessoaJuridica> lerPessoasJ() { // Alterado o nome do método
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("pessoasJ.bin"))) { // Alterado o nome do arquivo
            return (List<PessoaJuridica>) inputStream.readObject(); // Alterado o tipo do objeto lido
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }
    }

    private static void salvarPessoasJ(List<PessoaJuridica> pessoas) { // Alterado o nome do método
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("pessoasJ.bin"))) { // Alterado o nome do arquivo
            outputStream.writeObject(pessoas); // Alterado o tipo do objeto a ser escrito
            System.out.println("Arquivo salvo com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
}
