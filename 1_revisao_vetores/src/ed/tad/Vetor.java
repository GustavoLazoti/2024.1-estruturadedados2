package ed.tad;
import java.util.Random;

public class Vetor {

	private final int tamanho;
	private final int minimo;
	private final int maximo;
	private final int vaga;
	private final int repete; // 0 = não repete; 1 = pode repetir
	private int[] dados;

	public Vetor(int tamanho, int minimo, int maximo, int vaga, int repete) {
		this.tamanho = tamanho;
		this.minimo = minimo;
		this.maximo = maximo;
		this.vaga = vaga;
		this.repete = repete;

		this.dados = new int[this.tamanho];

		if (this.vaga != 0) {
			for (int i = 0; i < this.tamanho; i++) {
				this.dados[i] = this.vaga;
			}
		}
	}

	private boolean valorValido(int valor) {
		return valor >= this.minimo && valor <= this.maximo;
	}

	private boolean posicaoValida(int posicao) {
		return posicao >= 0 && posicao < this.tamanho;
	}

	private boolean podeRepetir() {
		return this.repete == 1;
	}

	public int[] localizar(int valor, int nPrimeiros) {

		int[] res = new int[this.tamanho];
		if (!this.valorValido(valor)) {
			return res;
		}

		if (this.repete == 0) {
			nPrimeiros = 1;
		}

		for (int i = 0; i < this.tamanho; i++) {
			if (this.dados[i] == valor) {
				res[0]++;
				res[res[0]] = i;
				if (res[0] == nPrimeiros) {
					break;
				}
			}
		}

		return res;
	}

	public ResultadoOperacao armazenar(int valor, int posicao) {

		if (!this.valorValido(valor)) { // valor válido?
			return ResultadoOperacao.FORADAFAIXA;
		}

		if (!this.posicaoValida(posicao)) { // posição é válida?
			return ResultadoOperacao.FORADASFRONTEIRAS;
		}

		if (this.dados[posicao] != this.vaga) { // posicao está vaga?
			return ResultadoOperacao.PREVISTOOPERACAO;
		}

		if (!this.podeRepetir()) { // se não pode repetir, então verificar se o elemento existe
			int[] existe = this.localizar(valor, 0);
			if (existe[0] > 0) {
				return ResultadoOperacao.VALOREXISTENTE;
			}

		}

		this.dados[posicao] = valor; // ok até aqui, armazenar
		return ResultadoOperacao.BEMSUCEDIDA;
	}

	public ResultadoOperacao alterar(int valor, int posicao) {

		if (!this.valorValido(valor)) { // valor válido?
			return ResultadoOperacao.FORADAFAIXA;
		}

		if (!this.posicaoValida(posicao)) { // posicão válida?
			return ResultadoOperacao.FORADASFRONTEIRAS;
		}

		if (this.dados[posicao] == this.vaga) { // posição contém um valor válido?
			return ResultadoOperacao.PREVISTOOPERACAO;
		}

		if (!this.podeRepetir()) { // se não pode repetir, verificar se já existe no vetor
			int[] existe = this.localizar(valor, 0);
			if (existe[0] > 0) {
				return ResultadoOperacao.VALOREXISTENTE;
			}

		}

		this.dados[posicao] = valor; // tudo bem até aqui, armazenar
		return ResultadoOperacao.BEMSUCEDIDA;
	}

	public ResultadoOperacao excluir(int posicao) {

		if (!this.posicaoValida(posicao)) { // posição de exclusão é válida?
			return ResultadoOperacao.FORADAFAIXA;
		}

		if (this.dados[posicao] == this.vaga) { // posição contém um valor válido?
			return ResultadoOperacao.FORADASFRONTEIRAS;
		}

		this.dados[posicao] = this.vaga; // pode excluir

		return ResultadoOperacao.BEMSUCEDIDA;
	}

	// ToDo: retorno do tipo incorreto. verificar
	public int ler(int posicao) {
		if (!this.posicaoValida(posicao)) { // posição válida?
			return 2;
		}
		return this.dados[posicao];
	}

	public ResultadoOperacao armazenar1Vaga(int valor) {

		if (!this.valorValido(valor)) { // o valor é válido?
			return ResultadoOperacao.FORADAFAIXA;
		}

		for (int i = 0; i < this.tamanho; i++) {
			if (this.dados[i] == this.vaga) {
				return (this.armazenar(valor, i));
			}
		}
		return ResultadoOperacao.INEXISTENTE;
	}

	public ResultadoOperacao removerUltima() {

		for (int i = this.tamanho - 1; i >= 0; i--) {
			if (this.dados[i] != this.vaga) {
				return this.excluir(i);
			}
		}
		return ResultadoOperacao.INEXISTENTE;
	}

	public void limparVetor() {
		for (int i = 0; i < tamanho; i++) {
			this.dados[i] = this.vaga;
		}
	}
	//1)C)
	public void preencherAleatorio() {
	    Random random = new Random();
	    for (int i = 0; i < tamanho; i++) {
	        int valor;
	        if (repete == 1) {
	            valor = random.nextInt(maximo - minimo + 1) + minimo;
	        } else {
	            boolean valorUnico = false;
	            do {
	                valor = random.nextInt(maximo - minimo + 1) + minimo;
	                if (localizar(valor, 0)[0] == 0) {
	                    valorUnico = true;
	                }
	            } while (!valorUnico);
	        }
	        dados[i] = valor;
	    }
	}
	//1)B)
	public void bubbleSort() {
        int n = tamanho;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (dados[j] > dados[j + 1]) {
                    int temp = dados[j];
                    dados[j] = dados[j + 1];
                    dados[j + 1] = temp;
                }
            }
        }
    }

	    public void insertionSort() {
	        int n = tamanho;
	        for (int i = 1; i < n; ++i) {
	            int key = dados[i];
	            int j = i - 1;
	
	            while (j >= 0 && dados[j] > key) {
	                dados[j + 1] = dados[j];
	                j = j - 1;
	            }
	            dados[j + 1] = key;
	        }
	    }
	
	    public void selectionSort() {
	        int n = tamanho;
	
	        for (int i = 0; i < n - 1; i++) {
	            int minIndex = i;
	            for (int j = i + 1; j < n; j++) {
	                if (dados[j] < dados[minIndex]) {
	                    minIndex = j;
	                }
	            }
	            int temp = dados[minIndex];
	            dados[minIndex] = dados[i];
	            dados[i] = temp;
	        }
	    }
	
	    public void quickSort(int menor, int maior) {
	        if (menor < maior) {
	            int pi = partition(menor, maior);
	            quickSort(menor, pi - 1);
	            quickSort(pi + 1, maior);
	        }
	    }
	
	    private int partition(int menor, int maior) {
	        int pivot = dados[maior];
	        int i = (menor - 1);
	        for (int j = menor; j < maior; j++) {
	            if (dados[j] < pivot) {
	                i++;
	                int temp = dados[i];
	                dados[i] = dados[j];
	                dados[j] = temp;
	            }
	        }
	        int temp = dados[i + 1];
	        dados[i + 1] = dados[maior];
	        dados[maior] = temp;
	        return i + 1;
	    }
	//1) C)
		public int buscaBinaria(int chave) {
	    int esquerda = 0;
	    int direita = tamanho - 1;
	
	    while (esquerda <= direita) {
	        int meio = esquerda + (direita - esquerda) / 2;
	
	        if (dados[meio] == chave)
	            return meio;
	
	        if (dados[meio] < chave)
	            esquerda = meio + 1;
	        else
	            direita = meio - 1;
	    }
	
	    return -1;
	}

}
