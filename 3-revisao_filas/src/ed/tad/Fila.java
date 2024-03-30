package ed.tad;

public class Fila {

	public int inicio;
	public int fim;
	public int operacao;
	public final int tamanho;
	final int[] fila;

	public Fila(int tamanho) {
		this.inicio = 0;
		this.fim = 0;
		this.operacao = 0;
		this.tamanho = tamanho;
		fila = new int[tamanho];
	}

	public boolean isEmpty() {
		return this.inicio == this.fim && this.operacao <= 0;
	}

	public boolean isFull() {
		return this.inicio == this.fim && this.operacao == 1;
	}

	public Integer peek() {
		if (this.isEmpty()) {
			return null;
		}
		return this.fila[this.inicio];
	}

	public String enqueue(int elem) {

		if (this.isFull()) {
			return "OPS...: a fila já está cheia";
		}

		this.fila[this.fim] = elem;
		this.fim = (this.fim + 1) % this.tamanho;
		this.operacao = 1;
		return "Elemento inserido!";
	}

	public Integer dequeue() {

		if (this.isEmpty()) {
			return null;
		}

		// Integer elem = this.fila[this.ini];
		Integer elem = this.peek();
		this.inicio = (this.inicio + 1) % this.tamanho;
		this.operacao = -1;
		return elem;

	}

	public int size() {
		int s = 0;

		// caso INI venha antes de FIM
		if (this.inicio < this.fim) {
			s = this.fim - this.inicio;
		} else if (this.inicio == this.fim) { // caso INI == FIM
			if (this.isEmpty()) {
				s = 0;
			} else {
				s = this.tamanho;
			}
		} else if (this.inicio > this.fim) { // caso INI venha depois de FIM
			s = this.tamanho - this.inicio + this.fim;
		}

		return s;
	}

	public String print() {
		String ret = "";

		// verifica se está vazia
		if (this.isEmpty()) {
			ret = "A fila esta' vazia";
		} else if (this.inicio < this.fim) { // caso INI venha antes do FIM
			for (int i = this.inicio; i < this.fim; i++) {
				ret += this.fila[i] + " ";
			}
		} else if (this.isFull() || this.inicio > this.fim) { // fila está cheia OU INI vem depois do FIM
			for (int i = this.inicio; i < this.tamanho; i++) {
				ret += this.fila[i] + " ";
			}
			if (this.inicio > 0) {
				for (int i = 0; i < this.fim; i++) {
					ret += this.fila[i] + " ";
				}
			}
		}
		return ret;
	}
	public int encontrarPosicao(int valor) 
	{
	    int posicao = 1;
	    boolean encontrado = false;
	
	    if (isEmpty()) {
	        return -1;
	    }
	
	    for (int i = inicio; i != fim; i = (i + 1) % tamanho) {
	        if (fila[i] == valor) {
	            encontrado = true;
	            break;
	        }
	        posicao++;
	    }
	
	    if (encontrado) {
	        return posicao;
	    } else {
	        return -1;
	    }
	}



}
