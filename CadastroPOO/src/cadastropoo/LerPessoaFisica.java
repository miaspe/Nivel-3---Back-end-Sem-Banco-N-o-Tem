/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastropoo;

import java.io.*;
import java.util.List;

public class LerPessoaFisica {
    public static void main(String[] args) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("pessoas.bin"))) {
            List<PessoaFisica> pessoas = (List<PessoaFisica>) inputStream.readObject();
            System.out.println("Pessoas cadastradas:\n");
            
            for (PessoaFisica pessoa : pessoas) {
                System.out.println(pessoa.toString());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}

