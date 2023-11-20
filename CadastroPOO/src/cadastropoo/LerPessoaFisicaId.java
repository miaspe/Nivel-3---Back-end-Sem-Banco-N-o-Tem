/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastropoo;

import static cadastropoo.CadastroPOO.MarcaMenu;
import java.io.*;
import java.util.List;
import java.util.Scanner;

class PessoaFisica implements Serializable {
    private static final long serialVersionUID = 123456789L;// UID 123456789L /932067521
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

public class LerPessoaFisicaId {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID da pessoa que deseja ler: ");
        int targetId = scanner.nextInt();
        scanner.nextLine(); 
        
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("pessoas.bin"))) {
            List<PessoaFisica> pessoas = (List<PessoaFisica>) inputStream.readObject();
            boolean pessoaEncontrada = false;

            int maiorId = encontrarMaiorId(pessoas);
            System.out.println("maior ID :" + maiorId);
            for (int i = 0; i < pessoas.size(); i++) {
                PessoaFisica pessoa = pessoas.get(i);
                if (targetId > maiorId ){
                    //System.out.println("Esse ID não existe no arquivo: Tente a opção(5) antes:");
                break;
            }

                if (pessoa.getId() == targetId) {
                    System.out.println("Pessoa encontrada:\n");
                    System.out.println(pessoa.toString());
                    pessoaEncontrada = true;
                    
                    if (MarcaMenu.equals("2")) {
                        while (true) {
                            System.out.print("Digite o novo Nome: ");
                            String nome = scanner.nextLine();
                                                        
                            System.out.print("Digite o novo CPF: ");
                            String cpf = scanner.nextLine();
                            
                            System.out.print("Digite a nova Idade: ");
                            int idade = scanner.nextInt();
                            scanner.nextLine(); // Consumir la nueva línea pendiente
                            
                            pessoa = new PessoaFisica(nome, cpf, idade);
                            pessoa.setId(targetId);
                            pessoas.set(targetId-1, pessoa);
                            //=======================================================================
                            //pessoas.set(i, pessoa);
                            //=======================================================================
                            
                            //System.out.println("Dados digitados : " + nome + " cpf " + cpf + " idade " + idade);
                            
                            //========================================================================
                            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("pessoas.bin"))) {
                                outputStream.writeObject(pessoas);
                                System.out.println("Atualizado com sucesso!");
                                break;
                            } catch (IOException e) {
                                System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
                                break;
                            }
                        }
                    }
                    //break;
                }
            }
            
            if (!pessoaEncontrada) {
                
                //System.out.println("id :" + targetId);
                //int pgid1 = pgid - 1;
                System.out.println("Nenhum arquivo encontrado com o ID informado.");
                
                while (true) {
                    if (targetId > maiorId ){
                    //System.out.println("Esse ID não existe no arquivo: Tente a opção(5) antes:");
                    break;
                    }
                    System.out.print("Deseja registrar arquivo pra posição " + targetId + " ? (S/N) ");
                    String retificad = scanner.nextLine();
                    if (retificad.equalsIgnoreCase("S")) {
                        System.out.print("Digite o novo Nome: ");
                        String nome = scanner.nextLine();
                        
                        System.out.print("Digite o novo CPF: ");
                        String cpf = scanner.nextLine();
                        
                        System.out.print("Digite a nova Idade: ");
                        int idade = scanner.nextInt();
                        scanner.nextLine(); // Consumir la nueva línea pendiente
                        
                        PessoaFisica pessoa = new PessoaFisica(nome, cpf, idade);
                        
                        //pessoas.set(targetId,pessoa);
                        
                        if (targetId >= 1 && targetId <= maiorId) {
                               if (targetId <= pessoas.size()) {
                                   pessoa.setId(targetId);
                                   pessoas.add(targetId-1, pessoa);
                                } else {
                                   pessoas.add(pessoa);
                                }
                            }   
                                                
                        //System.out.println("pgid : " + targetId);
                        //System.out.println("Dados digitados : " + nome + " cpf " + cpf + " idade " + idade);
                            
                        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("pessoas.bin"))) {
                            outputStream.writeObject(pessoas);
                            System.out.println("Atualizado com sucesso!");
                        } catch (IOException e) {
                            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
                        }
                        break;
                    }else{
                        break;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
 private static int encontrarMaiorId(List<PessoaFisica> pessoas) {
        int maiorId = -1;
        for (PessoaFisica pessoa : pessoas) {
            if (pessoa.getId() > maiorId) {
                maiorId = pessoa.getId();
            }
        }
        return maiorId;
    }
}


    