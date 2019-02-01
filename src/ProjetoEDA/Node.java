package ProjetoEDA;
/**
 *
 * @author Viviane 
 */
//Classe que implementa o no da arvore 
public class Node {

//---------------ATRIBUTOS-------------/
public String conteudoNo;//atributo que guarda o conteúdo do no 
public Node dir;//atributo que aponta para o no direito - subárvore direita
public Node esq;//atributo que aponta para o no esquerdo - subárvore esquerda
public Node pai;//atributo que aponta para o pai do no 
public String cor; // atributo que guarda a cor do no
public int alturanegra; // atributo para guardar a altura negra

//--------------METODOS----------------/
public Node(String conteudoNo, String cor){
    this.conteudoNo=conteudoNo;
    this.cor=cor;
}

//--------------METODOS----------------/
//construtor com entrada da chave 
public Node(String x){
	this.conteudoNo = x;
	this.dir = null;
	this.esq = null;
	this.pai = null;
        this.alturanegra=0;
		
  }
}
