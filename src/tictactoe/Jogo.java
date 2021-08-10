package tictactoe;
import java.util.Scanner;

public class Jogo {
	private int turno;
	private boolean jogoTermina;
	private Campo campo;

	public Jogo() {
		turno = 0;
		jogoTermina = false;
		campo = new Campo();
	}

	public void nextTurn() {
		turno++;
	}

	public void printNovoJogo() {
		System.out.println("###### Novo Jogo ######");
	}

	public void terminarJogo() {
		jogoTermina = true;
	}

	private String getEquipaQueJoga() { //"O" joga primeiro.
		if (turno % 2 == 0) {
			return "X";
		} else {
			return "O";
		}
	}

	public void printEquipaQueJoga() {
		System.out.println("Turno " + turno + "\nEquipa " + getEquipaQueJoga()
				+ " joga:\n<Seleciona uma casa para colocar a peça>");
	}

	private void startGame() {
		Scanner scanner = new Scanner(System.in);
		printNovoJogo();
		campo.printCampo();
		int valor = -1;
		while (!jogoTermina) {
			printEquipaQueJoga();
			valor = -1;
			try {
				String equipaString = getEquipaQueJoga();
				if(!equipaString.contentEquals("O"))
					valor = Integer.parseInt(scanner.next());
				else {
					valor = new GaloBot().generateValue1(campo.getCampo());
					System.out.println(valor);
				}
			} catch (NumberFormatException e) {
			}
			if (valor < 0 || valor > 8) {
				System.err.println("<Seleciona um valor entre 0 e 8>");
				campo.printCampo();
				continue;
			} else {
				if (!campo.getCampo().get(valor).equals(Piece.Vazio)) {
					System.err.println("<Esta casa já tem peça>");
				} else {
					campo.colocarPiece(Piece.valueOf(getEquipaQueJoga()), valor);
					if (!campo.getCampo().contains(Piece.Vazio)) {
						verQuemGanhou(true);
						continue;
					}
					turno++;
				}

			}
			verQuemGanhou(false);
			campo.printCampo();
		}

		scanner.close();

	}

	private void verQuemGanhou(boolean semVazios) {
		Object[] array = campo.getCampo().toArray();

		for (int i = 0; i < 9; i += 3) {
			if (!array[i].equals(Piece.Vazio) && array[i].equals(array[i + 1]) && array[i + 1].equals(array[i + 2])) {
				System.out.println("Ganhou a Equipa " + array[i].toString() + "!");
				jogoTermina = true;
				return;
			}
		}
		for (int i = 0; i < 3; i++) {
			if (!array[i].equals(Piece.Vazio) && array[i].equals(array[i + 3]) && array[i + 3].equals(array[i + 6])) {
				System.out.println("Ganhou a Equipa " + array[i].toString() + "!");
				jogoTermina = true;
				return;
			}
		}
		if (!array[0].equals(Piece.Vazio) && array[0].equals(array[4]) && array[4].equals(array[8])) {
			System.out.println("Ganhou a Equipa " + array[0].toString() + "!");
			jogoTermina = true;
			return;
		}
		if (!array[2].equals(Piece.Vazio) && array[2].equals(array[4]) && array[4].equals(array[6])) {
			System.out.println("Ganhou a Equipa " + array[2].toString() + "!");
			jogoTermina = true;
			return;
		}
		if (semVazios) {
			System.out.println("É um Empate!");
			jogoTermina = true;
		}

	}

	public static void main(String[] args) {
		Jogo jogo = new Jogo();
		jogo.startGame();
	}
}
