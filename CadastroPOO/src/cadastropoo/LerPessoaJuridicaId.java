/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastropoo;

//import static cadastropoo.CadastroPOO.E_Pessoa;

import static cadastropoo.CadastroPOO.MarcaMenu;
import java.io.*;
import java.util.List;
import java.util.Scanner;

class PessoaJuridica implements Serializable {
    private static final long serialVersionUID = 123456789L; // UID 123456789L /932067521
    private static int nextId = 1;
    private int id;
    private String nome;
    private String cnpj;

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

public class LerPessoaJuridicaId {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID da pessoa jurídica que deseja ler: ");
        int targetId = scanner.nextInt();
        scanner.nextLine();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("pessoasj.bin"))) {
            List<PessoaJuridica> pessoas = (List<PessoaJuridica>) inputStream.readObject();
            boolean pessoaEncontrada = false;

            int maiorId = encontrarMaiorId(pessoas);
            //System.out.println("Maior ID: " + maiorId);
            for (int i = 0; i < pessoas.size(); i++) {
                PessoaJuridica pessoa = pessoas.get(i);
                if (targetId > maiorId) {
                    // System.out.println("Esse ID não existe no arquivo: Tente a opção(5) antes:");
                    break;
                }

                if (pessoa.getId() == targetId) {
                    System.out.println("Pessoa jurídica encontrada:\n");
                    System.out.println(pessoa.toString());
                    pessoaEncontrada = true;

                    if (MarcaMenu.equals("2")) {
                        while (true) {
                            System.out.print("Digite o novo Nome: ");
                            String nome = scanner.nextLine();

                            System.out.print("Digite o novo CNPJ: ");
                            String cnpj = scanner.nextLine();

                            pessoa = new PessoaJuridica(nome, cnpj);
                            pessoa.setId(targetId);
                            pessoas.set(targetId-1, pessoa);

                            // ===========================================================================
                            //pessoas.set(i, pessoa);
                            // ===========================================================================

                            //System.out.println("Dados digitados : " + nome + " CNPJ " + cnpj);

                            // ===========================================================================
                            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("pessoasj.bin"))) {
                                outputStream.writeObject(pessoas);
                                System.out.println("Atualizado com sucesso!");
                                break;
                            } catch (IOException e) {
                                System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
                                break;
                            }
                        }
                    }
                    // break;
                }
            }

            if (!pessoaEncontrada) {
                System.out.println("Nenhum arquivo encontrado com o ID informado.");
                while (true) {
                    if (targetId > maiorId) {
                        // System.out.println("Esse ID não existe no arquivo: Tente a opção(5) antes:");
                        break;
                    }
                    System.out.print("Deseja registrar arquivo pra posição " + targetId + " ? (S/N) ");
                    String retificad = scanner.nextLine();
                    if (retificad.equalsIgnoreCase("S")) {
                        System.out.print("Digite o novo Nome: ");
                        String nome = scanner.nextLine();

                        System.out.print("Digite o novo CNPJ: ");
                        String cnpj = scanner.nextLine();

                        PessoaJuridica pessoa = new PessoaJuridica(nome, cnpj);
                        
                        if (targetId >= 1 && targetId <= maiorId) {
                            if (targetId <= pessoas.size()) {
                                pessoa.setId(targetId);
                                pessoas.add(targetId-1, pessoa);
                            } else {
                                pessoas.add(maiorId+1,pessoa);
                            }
                        }

                        //System.out.println("pgid : " + targetId);
                        //System.out.println("Dados digitados : " + nome + " CNPJ " + cnpj);

                        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("pessoasj.bin"))) {
                            outputStream.writeObject(pessoas);
                            System.out.println("Atualizado com sucesso!");
                        } catch (IOException e) {
                            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
                        }
                        break;
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private static int encontrarMaiorId(List<PessoaJuridica> pessoas) {
        int maiorId = -1;
        for (PessoaJuridica pessoa : pessoas) {
            if (pessoa.getId() > maiorId) {
                maiorId = pessoa.getId();
            }
        }
        return maiorId;
    }
}
