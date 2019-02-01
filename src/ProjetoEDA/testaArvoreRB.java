/**
 *
 * @author Viviane 
 */
//Classe de teste de execucao da classe Arvore Rubro-Negra
package ProjetoEDA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class testaArvoreRB {
    public static void main(String[] args) {
            
    ArvoreRB TreeRB = initArvore();
    
    // criar laço para buscar elemento
    Scanner ler = new Scanner(System.in);
    System.out.println("Palavra a ser buscada: ");
    String busca=ler.nextLine();   
    	  
  while (!busca.equals("")) {
            if (busca.length() <= 20) {   // tamanho máximo da palavra é de 20 caractere
                Node n = TreeRB.buscarElemento(busca);
                if(n != null){
                    System.out.println("Palavra encontrada: "+n.conteudoNo);
                }else{
                    System.out.println("Palavra não encontrada: "+busca);
                }

            } else {
                System.out.println("Busca invalida, palavra contém mais de 20 caracters."); // se a palavra tiver mais de 20 caracteres
            }
            System.out.println("Palavra a ser buscada: ");
            busca = ler.nextLine();
        }
        System.out.println("Running: Check Tree...\n");
        ArvoreRB.RBcheck(TreeRB, TreeRB.raiz);
        System.out.println("\n\n");
        System.out.println("Running: Print Tree...\n");
        ArvoreRB.RBprint(TreeRB, TreeRB.raiz);
    }     
    
    public static ArvoreRB initArvore(){
        System.out.printf("Informe o nome de Arquivo de Texto:\n"); 
        Scanner ler = new Scanner(System.in);
        String nome = ler.nextLine();
        System.out.printf("\nConteúdo do Arquivo Texto:\n"); //exibe o conteúdo do arquivo 
        Node nil = new Node("nil","BLACK");
        ArvoreRB TreeRB = new ArvoreRB(nil); //criacao da arvore rubro negra  
        
    try {
      FileReader arq = new FileReader(nome);
      BufferedReader lerArquivo = new BufferedReader(arq);  
      
 
      String linha = lerArquivo.readLine(); // lê a primeira linha 
        
      while (linha != null) {
       
          StringTokenizer st = new StringTokenizer(linha);
          if (st.countTokens()==2){ // testa se o número de tokens é igual a 2

          String palavra = st.nextToken(); // "palavra" corresponde a palavra no arquivo 
          String opcao = st.nextToken();  // "opcao" corresponde a "0" ou "1" no arquivo
            if (palavra.length()<=20){       // testa se a palavra é igual ou menor a 20 caracteres         
                      if(opcao.equals("0")){ // se a opção igual a "0" remover elemento
                          //metodo de remover
                          Node e = TreeRB.buscarElemento(palavra);
                          if (e != TreeRB.nil)
                             TreeRB.RB_DELETE(TreeRB, e);
                          else
                              linha = null;
                      }                       
                      else
                      { 
                        //método de inserir       
                          Node e = new Node(palavra,"vermelho");
                          TreeRB.RB_Insert(TreeRB,e);                          
                          
                      }
            }
            else{
                System.out.println("Palavra contém mais de 20 caracteres");
            }
                System.out.println(palavra+" "+opcao);
          }
          
          linha = lerArquivo.readLine(); // lê da segunda até a última linha

      }      
      
      arq.close();
    } catch (IOException e) {
      System.err.printf("Erro ao ler o arquivo: %s.\n" ,e.getMessage()); 
    }
   
        ArvoreRB.RBcheck(TreeRB, TreeRB.raiz);

        
    return TreeRB;
    
    
    }
 
}