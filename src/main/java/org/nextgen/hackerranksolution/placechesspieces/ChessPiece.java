package org.nextgen.hackerranksolution.placechesspieces;

import java.util.List;

public interface ChessPiece {
    List<BoardPosition> impactedPositions(ChessBoard board, BoardPosition location);

}
