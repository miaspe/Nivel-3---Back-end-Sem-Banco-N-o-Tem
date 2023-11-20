/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastropoo;

//import static cadastropoo.CadastroPOO.E_Pessoa;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class PessoaFisica implements Serializable {
    private static final long serialVersionUID = 123456789L;// UID 123456789L /932067521
    private static int nextId = 1;
    private static int cad1 = 0;
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
    //public static void setNextId(int nextId) {
    //    PessoaFisica.nextId = nextId;
    //}

    // ===========================================================================================
    @Override
    public String toString() {
        return "ID: " + id + "\nNome: " + nome + "\nCPF: " + cpf + "\nIdade: " + idade + "\n";
    }
}

public class CadastroPessoaFisica {
    public static void main(String[] args) {
        var cad1 = 0;
        List<PessoaFisica> pessoas = lerPessoas();
        Scanner scanner = new Scanner(System.in);
        int maxId = 0;
            for (PessoaFisica pessoa : pessoas) {
                System.out.println(pessoa.toString());
                if (pessoa.getId() > maxId) {
                    maxId = pessoa.getId();
                }
            }
            
            //System.out.println("maxId : "+maxId+" e");
            
        while (true) {
            System.out.print("Digite o nome da pessoa (ou 'sair' para encerrar): ");
            String nome = scanner.nextLine();
            if (nome.equalsIgnoreCase("sair")) {
                if(cad1 == 0){
                    System.out.println("Nenhum registro a salvar:");
                    break;
                }else{
                    System.out.print(" Salvar as informações novas com as já existente? (S/N): ");
                    String resposta = scanner.nextLine();
                    if (resposta.equalsIgnoreCase("S")) {
                        salvarPessoas(pessoas);
                        break;
                    }else{
                        break;
                    }
                }    
            }

            System.out.print("Digite o CPF da pessoa: ");
            String cpf = scanner.nextLine();

            System.out.print("Digite a idade da pessoa: ");
            int idade = Integer.parseInt(scanner.nextLine());

            PessoaFisica pessoa = new PessoaFisica(nome, cpf, idade);
            pessoa.setId(maxId=maxId+1);
            pessoas.add(pessoa);
            
            cad1 = 1;

            System.out.println("Pessoa cadastrada com sucesso!  cad\n" + cad1);
        }
        
    }

    private static List<PessoaFisica> lerPessoas() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("pessoas.bin"))) {
            List<PessoaFisica> pessoas = (List<PessoaFisica>) inputStream.readObject();
            //int maxId = 0;
            for (PessoaFisica pessoa : pessoas) {
                System.out.println(pessoa.toString());
                //if (pessoa.getId() > maxId) {
                //    maxId = pessoa.getId();
                //}
            }
            //PessoaFisica.setNextId(maxId + 1);
            return pessoas;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Arquivo de pessoas não encontrado. Iniciando novo Arquivo...");
            return new ArrayList<>();
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

//======================