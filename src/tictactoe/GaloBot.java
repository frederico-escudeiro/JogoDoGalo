package tictactoe;

import java.util.ArrayList;
import java.util.List;

public class GaloBot {

	private int bestPos;
	private int bestSum;

	public GaloBot() {
		reset();
	}

	private void reset() {
		bestPos = -1;
		bestSum = -1;
	}

	public int generateValue(List<Piece> pieceArray) {
		int[] indexArray = new int[pieceArray.size()];
		System.out.println(pieceArray.size());
		int j = 0;
		for (int i = 0; i < pieceArray.size(); i++) {
			if (pieceArray.get(i).equals(Piece.Vazio)) {
				indexArray[j++] = i;
			}
		}
		int valor = (int) (Math.random() * indexArray.length);
		return indexArray[valor];
	}

	private List<Integer> getNeighbourPosition(int position) {
		ArrayList<Integer> neib = new ArrayList<>();
		// esquerda
		if (position % 3 != 0) {
			neib.add(position - 1);
		}
		// direita
		if ((position + 1) % 3 != 0) {
			neib.add(position + 1);
		}
		// cima
		if (position >= 3) {
			neib.add(position - 3);
		}
		// baixo
		if (position <= 5) {
			neib.add(position + 3);
		}
		if (position % 2 == 0) {
			if (position != 4) {
				neib.add(4);
			} else {
				neib.add(0);
				neib.add(2);
				neib.add(6);
				neib.add(8);
			}
		}
		return neib;
	}

	private boolean vitoriaCerta(List<Piece> campo, int pos) {
		// 
		if (pos == 0 || pos == 3 || pos == 6) {
			if (campo.get(pos).equals(campo.get(pos + 1)) && campo.get(pos + 1).equals(campo.get(pos + 2))) {
				return true;
			}
		}
		if (pos == 0 || pos == 1 || pos == 2) {
			if (campo.get(pos).equals(campo.get(pos + 3)) && campo.get(pos + 3).equals(campo.get(pos + 6))) {
				return true;
			}
		}
		if (pos == 0 || pos == 8) {
			// diagonal 0 4 8
			if (campo.get(Math.abs(pos - 4)) == Piece.O && (campo.get(Math.abs(pos - 8)) == Piece.O)) {
				return true;
			}
		}

		if (pos == 2 || pos == 6) {
			int auxPos = pos - 2;
			if (campo.get(Math.abs(auxPos - 2) + 2) == Piece.O && (campo.get(Math.abs(auxPos - 4) + 2) == Piece.O)) {
				return true;
			}
		}
		return false;

	}

	public int generateValue1(List<Piece> campo) {
		reset();
		if (campo.get(4) == Piece.Vazio) {
			return 4;
		}
		for (int i = 0; i < campo.size(); i++) {
			if (campo.get(i) == Piece.Vazio) {

				if (vitoriaCerta(campo, i)) {
					bestPos = i;
					break;
				}
				List<Integer> array = getNeighbourPosition(i);
				int sum = 0;
				for (int j = 0; j < array.size(); j++) {
					if (campo.get(j) == Piece.O) {
						sum++;
					}
				}
				if (sum > bestSum) {
					bestPos = i;
					bestSum = sum;
				}
			}
		}
		return bestPos;
	}

}
