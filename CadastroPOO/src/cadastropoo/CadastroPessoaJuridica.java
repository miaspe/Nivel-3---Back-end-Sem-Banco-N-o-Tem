/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastropoo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class PessoaJuridica implements Serializable {
    private static final long serialVersionUID = 123456789L;// UID 123456789L /932067521
    static int nextId = 1;
    //private static int cad1 = 0;
    private int id;
    private String nome;
    private String cnpj;
    //private int idade;

    public PessoaJuridica(String nome, String cnpj) {
        this.id = nextId++;
        this.nome = nome;
        this.cnpj = cnpj;
        //this.idade = idade;
    }

    public int getId() {
        return id;
    }

    //public static void setNextId(int nextId) {
    //    PessoaJuridica.nextId = nextId;
    //}

    // Resto dos getters e setters

    @Override
    public String toString() {
        return "ID: " + id + "\nNome: " + nome + "\nCNPJ: " + cnpj + "\n";
    }
}

public class CadastroPessoaJuridica {
    public static void main(String[] args) {
        var cad1 = 0;
        List<PessoaJuridica> pessoas2 = lerPessoas();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print("Digite o nome da empresa (ou 'sair' para encerrar): ");
            String nome = scanner.nextLine();
            if (nome.equalsIgnoreCase("sair")) {
                if(cad1 == 0){
                    System.out.println("Nenhum registro a salvar:");
                    break;
                }else{
                    System.out.print(" Salvar as informações novas com as já existente? (S/N): ");
                    String resposta = scanner.nextLine();
                    if (resposta.equalsIgnoreCase("S")) {
                        salvarPessoas(pessoas2);
                        break;
                    }else{
                        break;
                    }
                }    
            }

            System.out.print("Digite o CNPJ da Empresa: ");
            String cnpj = scanner.nextLine();

            //System.out.print("Digite a idade da pessoa: ");
            //int idade = Integer.parseInt(scanner.nextLine());

            PessoaJuridica pessoa2 = new PessoaJuridica(nome, cnpj);
            pessoas2.add(pessoa2);
            
            cad1 = 1;

            System.out.println("Empresa cadastrada com sucesso!  cad\n" + cad1);
        }
        
        //System.out.print(" Salvar as informações novas com as já existente? (S/N): ");
        //String resposta = scanner.nextLine();
        //if (resposta.equalsIgnoreCase("S")) {
        //    salvarPessoas(pessoas);
        //}

        //scanner.close();
    }

    private static List<PessoaJuridica> lerPessoas() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("pessoasJ.bin"))) {
            List<PessoaJuridica> pessoas = (List<PessoaJuridica>) inputStream.readObject();
            int maxId = 0;
            for (PessoaJuridica pessoa2 : pessoas) {
                if (pessoa2.getId() > maxId) {
                    maxId = pessoa2.getId();
                }
            }
            //PessoaJuridica.setNextId(maxId + 1);
            return pessoas;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Arquivo de pessoas não encontrado. Iniciando com ID automático.");
            return new ArrayList<>();
        }
    }

    private static void salvarPessoas(List<PessoaJuridica> pessoas) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("pessoasJ.bin"))) {
            outputStream.writeObject(pessoas);
            System.out.println("Arquivo salvo com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
}