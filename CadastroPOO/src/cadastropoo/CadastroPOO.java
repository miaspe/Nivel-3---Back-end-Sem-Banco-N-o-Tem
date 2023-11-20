package cadastropoo;
// * @author miaspe
//import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.IOException;

public class CadastroPOO {
    static String E_Pessoa = "F"; 
    static String MarcaMenu = ""; 
    static Scanner scanner = new Scanner(System.in); 
//----------------------------------------------------------------------------    
    public static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.flush();
            }
        } catch (Exception e) {
            // Lidar com exceções
            e.printStackTrace();
        }
    }

//---------------------------------------------------------------------------- 
    //public static void main(String[] args) {
    //    Scanner scanner = new Scanner(System.in);
    //}
//----------------------------------------------------------------------------     
//Parte cortada do código
//----------------------------------------------------------------------------     
        
    public static void main(String[] args)throws Exception {
        //String E_Pessoa = "F"; 
        int loop = 1;
        while (loop == 1){// TODO code application logic here
        //limparTela();
        System.out.println("  ----------  Arquivos de  Pessoas  ---------");
        System.out.println("  ----------  Fisicas / Juridicas  ----------");           
        System.out.println("[9] Altera Estado entre (F)isica e (J)uridica - atual=(" + E_Pessoa + ")");
        System.out.println("[1] Incluir Pessoa (" + E_Pessoa + ")");
        System.out.println("[2] Alterar Pessoa (" + E_Pessoa + ")");
        System.out.println("[3] Excluir Pessoa (" + E_Pessoa + ")");
        System.out.println("[4] Buscar pelo ID (" + E_Pessoa + ")");
        System.out.println("[5] Exibir Todos   (" + E_Pessoa + ")");
        System.out.println("[6] Reorganizar/ Persistir Dados");
        System.out.println("[7] Recuperar (Ler) Dados");
        System.out.println("[0] Finalizar Programa");
        
        System.out.print("Digite a sua escolha: ");
        String escolhaMenu = scanner.nextLine();
        MarcaMenu = escolhaMenu; 
        //this.String escolhaMenu2 = String escolhaMenu;
    
    System.out.println("escolha: " + escolhaMenu); 
    try{
    switch (escolhaMenu) {
        case "1"://System.out.println("1-");
        if(E_Pessoa.equals("F")){
            //System.out.println("FF");
            CadastroPessoaFisica.main(args);
            break;
        }else{
            //System.out.println("JJ");
            CadastroPessoaJuridica.main(args);
            break;
        }
        case "2"://System.out.println("2-");
        if(E_Pessoa.equals("F")){
            LerPessoaFisicaId.main(args);
            break;
        }else{
            LerPessoaJuridicaId.main(args);
            break;
        }
        case "3"://System.out.println("3-");
        if(E_Pessoa.equals("F")){
            ExcluirRegistro.main(args);
            break;
        }else{
            ExcluirRegistroJ.main(args);
            break;
        }
        case "4"://System.out.println("4-");
        if(E_Pessoa.equals("F")){
            LerPessoaFisicaId.main(args);
            break;
        }else{
            LerPessoaJuridicaId.main(args);
            break;
        }
        case "5"://System.out.println("5-");
        if(E_Pessoa.equals("F")){
            LerPessoaFisica.main(args);
            break;
        }else{
            LerPessoaJuridica.main(args);
            break;
        }
        case "6"://System.out.println("6-");
        if(E_Pessoa.equals("F")){
            ReclassificarIds.main(args);
            break;
        }else{
            ReclassificarIdsJ.main(args);
            break;
        }
        case "7":System.out.println("7-");break;
        case "9"://System.out.println("9-");
        if(E_Pessoa.equals("F")){E_Pessoa = "J";}else{E_Pessoa = "F";}break;
        case "0":System.out.println("Encerrando o programa:...");loop=0;break;
        default:
            System.out.println("Escolha de novo");
    }}catch(InputMismatchException e){
    System.out.println("Erro: Entrada inválida. Tente de novo.");
    scanner.next();}
    }
 //     return E_Pessoa;  
    }
        
    }
    
