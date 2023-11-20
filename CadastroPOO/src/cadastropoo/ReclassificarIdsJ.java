/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastropoo;
import java.io.*;
import java.util.List;

class PessoaJuridica implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private static int nextId = 1;
    private int id;
    private String nome;
    private String cnpj; // Alterado de cpf para cnpj

    public PessoaJuridica(String nome, String cnpj) { // Removido o atributo "razaoSocial" do construtor
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
        return "ID: " + id + "\nNome: " + nome + "\nCNPJ: " + cnpj + "\n"; // Removido o atributo "razaoSocial"
    }
}

public class ReclassificarIdsJ {
    public static void main(String[] args) {
        List<PessoaJuridica> pessoas = lerPessoasJ(); // Alterado o tipo da lista

        if (pessoas != null) {
            // Reclassificar os IDs
            for (int i = 0; i < pessoas.size(); i++) {
                pessoas.get(i).setId(i + 1);
            }

            salvarPessoasJ(pessoas); // Alterado o nome do método de salvar
            System.out.println("IDs reclassificados e arquivo salvo com sucesso!");
        } else {
            System.out.println("Não foi possível ler o arquivo de pessoas.");
        }
    }

    private static List<PessoaJuridica> lerPessoasJ() { // Alterado o nome do método
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("pessoasJ.bin"))) { // Alterado o nome do arquivo
            return (List<PessoaJuridica>) inputStream.readObject(); // Alterado o tipo do objeto lido
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler o arquivo de pessoas: " + e.getMessage());
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