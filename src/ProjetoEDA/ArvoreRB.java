package ProjetoEDA;
import java.util.Scanner;

//-------------------------------------------------------------------------------------//
/* ArvoreRB.java
 * Arvore Rubro-Negra
 * Objetivo: Implementacao de uma árvore rubro-negra que será utilizada para
 buscar palavras de um dicionário
 * Desenvolvedora : Viviane de Menezes Ramalho Souza
 * Disciplina: Estrutura de Dados e Algoritmos
 * Prof: Bruno Motta
 */
 //Classe que implementa uma Árvore Rubro Negra
//-------------------------------------------------------------------------------------//

public class ArvoreRB {

//-------------ATRIBUTOS--------------/
	public Node raiz; // no raiz da arvore
	public Node aux;// guarda o ultimo percurso acessado
        public Node nil;
	public int pos; // posicao do no, 1 = esquerda, 2 = direita
        public Node pr; // no de percurso
	public int tamanho;//guarda a quantidade de nos na arvore
	public String print; //atributo para impressao da arvore
               	
//-------------METODOS----------------/	
//construtor padrao
public ArvoreRB(Node nil){
	this.raiz = nil;
	this.pr = nil;
	this.aux = nil;
	this.tamanho = 0;
	this.print = " ";
        this.nil=nil;
}
//construtor com entrada de chave
public ArvoreRB(String n){
	this.raiz = new Node(n);
	this.pr = raiz;
	this.tamanho = 0;
	this.print = " ";
}
/*========================================================================================*/
//Metodo que retorna a raiz da arvore
public Node raiz(){
	return this.raiz;
}

/*========================================================================================*/
//Metodo que verifica se o no n é raiz
public boolean eRaiz(Node n){
	if (raiz == n) return true;
	else return false;
}
/*========================================================================================*/
//Metodo que calcula a altura negra

/*========================================================================================*/
	//Metodo de busca de um elemento com chave x na arvore
public Node buscarElemento(String x){
		//verifica se a arvore é vazia
		if (raiz == null){
			pos = 0;
			return raiz;
		}		
                if(pr == nil){
                    pr = raiz;
                }
		//testa se o no é vazio
		if(pr == null)return null;		
		//achou o no
		else if (pr.conteudoNo.equals(x)){
                    Node aux=pr;
                    pr=raiz;
                    return aux; 
                }    
		//no a esquerda
		else if((compare(x, pr.conteudoNo)) < 0){ // faz comparação, se palavra menor que nó
			aux = pr;
			pr = pr.esq;
			aux.esq = pr;
			pos = 1; //posicao indica a insersao na esquerda - valor definido 1(esquerda) e 2(direita)
			return buscarElemento(x);
		}		
		else { 
			aux = pr;
			pr= pr.dir;
			aux.dir = pr;
			pos = 2;//posicao indica a insercao na direita - valor definido 1(esquerda) e 2(direita)
			return buscarElemento(x);
                 }
       }		
/*========================================================================================*/
     // Método usado na busca (buscarElemento)   
        public static int compare(String palavra, String no){ // compara strings e retorna um inteiro
                 return palavra.toString().compareToIgnoreCase(no.toString());//-1 menor, 1 maior, 0 iguais
 }
 
        /*======================================================================================*/

    	//Rotação esquerda
          public void rotacaoEsq(ArvoreRB A,Node x) {
            Node y = x.dir;
                 x.dir = y.esq;
            if (y.esq != A.nil) {
                y.esq.pai = x;  
            }
                y.pai = x.pai;
            if (x.pai == A.nil) {
                A.raiz = y;
            } else{
                if (x == x.pai.esq) {
                x.pai.esq = y;
            } else {
                x.pai.dir = y;
              }
           }
        y.esq = x;
        x.pai = y;
    }
                  
  /*========================================================================================*/
    	//Rotação direita
         public void rotacaoDir(ArvoreRB A,Node x) {
            Node y = x.esq;
            x.esq = y.dir;
            if (y.dir != A.nil) {
                 y.dir.pai = x;
            }
                y.pai = x.pai; 
            if (x.pai == A.nil) {
                A.raiz = y;
            } else {
                if (x == x.pai.dir) {
                x.pai.dir = y;
            } else {
                x.pai.esq = y;
            }
            }
        y.dir = x;
        x.pai = y;
    }     
                
      /*========================================================================================*/ 
       //Metodo de insercao de um elemento com chave z na arvore
	public void RB_Insert(ArvoreRB A, Node z){
            Node y = A.nil;
            Node x = A.raiz; 

        while (x != A.nil) {
             y = x;
             if (z.conteudoNo.compareToIgnoreCase(x.conteudoNo) < 0) {  
                x = x.esq;
             } else {
                x = x.dir;
             }
        }
            z.pai = y;
            if (y == A.nil) {
              A.raiz = z;
            } else if (z.conteudoNo.compareToIgnoreCase(y.conteudoNo) < 0) {
                y.esq = z;
            } else {
                y.dir = z;
        }
        z.esq = A.nil;
        z.dir = A.nil;
        z.cor = "vermelho";
        insertFixup(A,z);
    }
                 
     /*========================================================================================*/          
        //Metodo de insercao FIXUP 
	public void insertFixup(ArvoreRB A,Node z){           
          
           while ( z.pai.cor == "vermelho"){ 
            if (z.pai == z.pai.pai.esq) {
                Node y = z.pai.pai.dir;            
            if (y.cor== "vermelho") {
                    z.pai.cor = "preto";
                    y.cor = "preto";
                    z.pai.pai.cor = "vermelho";
                    z = z.pai.pai;
               } else {
                 if (z == z.pai.dir) {
                        z = z.pai;
                        rotacaoEsq(A,z);
                }
                        z.pai.cor = "preto";
                        z.pai.pai.cor = "vermelho";
                        rotacaoDir(A,z.pai.pai);
                 }
            }else {
               if (z.pai == z.pai.pai.dir) {                    
               Node y = z.pai.pai.esq;
                if (y.cor == "vermelho") {
                    z.pai.cor = "preto";
                    y.cor = "preto";
                    z.pai.pai.cor = "vermelho";
                    z = z.pai.pai;                                     
                }
                else{
                    if (z == z.pai.esq) {
                        z = z.pai;
                        rotacaoDir(A,z);  
                }
                        z.pai.cor = "preto";
                        z.pai.pai.cor = "vermelho";
                        rotacaoEsq(A,z.pai.pai);
              }               
            
          }   
           A.raiz.cor = "preto";
        }   
    }
 }
 /*========================================================================================*/        
static public void RBcheck(ArvoreRB A,Node x){
    if(x!=A.nil){
        System.out.println(x.pai.conteudoNo+","+ x.conteudoNo+","+x.cor+","+x.esq.conteudoNo+","+x.dir.conteudoNo);
        RBcheck(A,x.esq);
        RBcheck(A,x.dir);
    }
} 
       
/*========================================================================================*/        
static public void RBprint(ArvoreRB A,Node x){
    if(x!=A.nil){
        RBprint(A,x.esq);
        System.out.println(x.conteudoNo+","+x.esq.conteudoNo+","+x.dir.conteudoNo);
        RBprint(A,x.dir);
    }
   } 
/*========================================================================================*/        
public void RBTransplante(ArvoreRB A, Node u, Node v){
   if (u.pai==A.nil){
       A.raiz=v;
   }
   else if(u==u.pai.esq){
       u.pai.esq=v;
    }
   else{
       u.pai.dir=v;
   v.pai=u.pai;    
   }
  }

/*========================================================================================*/        
// Método mínimo 
public Node TREEMinimo(ArvoreRB A, Node x){
    while ((x != A.nil) && (x.esq!=A.nil)){
         x=x.esq;
    }
    return x;
}
/*========================================================================================*/
// Método para Remoção
public void RB_DELETE(ArvoreRB A, Node z){
      Node x; 
      Node y = z;
      String corOrig=y.cor;
      
      if(z.esq==A.nil){
          x=z.dir;
          RBTransplante(A, z, z.dir);
      }      
      else if
          (z.dir==A.nil){
           x= z.esq;
           RBTransplante(A, z, z.esq);
      }
      else{
            y=TREEMinimo(A, z.dir);
            corOrig=y.cor;
            x = y.dir;
      }
      if (y.pai==z){
          x.pai=y;
      }      
      else{
          RBTransplante(A, y, y.dir);
          y.dir = z.dir;
          y.esq.pai=y;
      RBTransplante(A, z, y);
      y.esq= z.esq;
      y.esq.pai=y;
      y.cor=z.cor;       
      if(corOrig=="preto"){
      RBDelete_Fixup (A,x);      
      }
   }
}
/*========================================================================================*/
// Método para Remoção Fixup
public void RBDelete_Fixup(ArvoreRB A, Node z){
    Node x = A.nil;
    Node w = A.nil;
    
    while (x!=raiz && x.cor=="preto"){
        if (x==x.pai.esq){
            w=x.pai.dir;
            if(w.cor=="vermelha"){
             w.cor="preto";
             x.pai.cor="vermelho";
             rotacaoEsq(A,x.pai);  
             w=x.pai.dir;            
         }   
         if (w.esq.cor == "preto" && w.esq.cor =="preto") {
             w.cor="vermelho";
             x=x.pai;  
         
          } else {
                if (w.dir.cor == "preto") {
                w.esq.cor = "preto";
                w.cor = "vermelho";
                rotacaoDir(A,w);
                w = x.pai.dir;
                }
                w.cor = x.pai.cor;
                x.pai.cor = "preto";
                w.dir.cor = "preto";
                rotacaoEsq(A,x.pai);
                x = raiz;
                }
           
        }else{
            w = x.pai.esq;
                if (w.cor == "vermelho") {
                w.cor = "preto";
                x.pai.cor = "vermelho";
                rotacaoDir(A,x.pai);
                w = x.pai.esq;
                }
                if (w.dir.cor =="preto" && w.esq.cor == "preto") {
                w.cor ="vermelho";
                x = x.pai;
           
                   } else {
                    if (w.esq.cor =="preto") {
                        w.dir.cor = "preto";
                        w.cor = "vermelho";
                        rotacaoEsq(A,w);
                        w = x.pai.esq;
                    }
                    w.cor = x.pai.cor;
                    x.pai.cor = "preto";
                    w.esq.cor = "preto";
                    rotacaoDir(A,x.pai);
                    x = raiz;
                }
        }   
                
                
         x.cor = "preto";
    }                   
}
/*========================================================================================*/
   // menu do usuário     
         
     static public void menu() {
          Scanner entrada = new Scanner(System.in);
          int opcao= entrada.nextInt();
		do {
                      System.out.println("============Menu do Usuário==============");
                      System.out.println("Escolha uma opção:");
                      System.out.println("[1] - Inserção ");
                      System.out.println("[2] - Busca ");
                      System.out.println("[3] - Remoção ");
                      System.out.println("[4] - Impressão ");
                 switch(opcao){ 
                    case 1: // Inserção
                       
                        break;
                    case 2: // Busca				
			break;
                    case 3:  // Remoção				
                        break;
                    case 4: // Impressão
			break;
		    default:
			System.out.println("Opção Inválida!");
			break;
			}
		} while (opcao != 0);
          }
     } 

 




     
            
 
