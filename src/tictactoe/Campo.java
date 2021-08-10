package tictactoe;

import java.util.ArrayList;

public class Campo {
	public static final int SIZE = 9;
	private ArrayList<Piece> campoArrayList = new ArrayList<>(SIZE);

	public Campo() {
		for (int i = 0; i < SIZE; i++) {
			campoArrayList.add(Piece.Vazio);
		}
	}

	public void colocarPiece(Piece piece, int index) {
		campoArrayList.set(index, piece);
		System.out.println("Colocada peça " + piece.getChar() + " na posição " + index + ".");
	}

	public ArrayList<Piece> getCampo() {
		return campoArrayList;
	}

	public void printCampo() {
		StringBuilder campoStr = new StringBuilder();
		for (int i = 0; i < SIZE; i++) {
			campoStr.append(campoArrayList.get(i).getChar());
			if (i == 8)
				break;
			if ((i + 1) % 3 == 0) {
				campoStr.append("\n-+-+-\n");
			} else {
				campoStr.append('|');
			}
		}
		System.out.println(campoStr);
	}

	public static void main(String[] args) {
		Campo campo = new Campo();
		for(int i = 0; i< 10;i++) {
			campo.colocarPiece(Piece.X,i);
		}
		campo.printCampo();
	}

	public int getSize() {
		return SIZE;
	}

}
