package tictactoe;

public enum Piece {
	O('O'),X('X'),Vazio(' ');
	private char pieceChar;
	
	Piece(char pieceChar){
		this.pieceChar = pieceChar;
	}

	public char getChar(){
		return pieceChar;
	}
	
}
