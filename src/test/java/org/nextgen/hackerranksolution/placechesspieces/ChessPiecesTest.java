package org.nextgen.hackerranksolution.placechesspieces;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nextgen.hackerranksolution.placechesspieces.ChessPieces.*;

public class ChessPiecesTest {
    @Test
    public void kingImpactsAllNeighbors() throws Exception {
        ChessBoard board = new ChessBoard(3, 3);

        final List<BoardPosition> impactedPositions = ChessPieces.KING.impactedPositions(board, new BoardPosition(1, 1));

        assertThat(impactedPositions).containsExactly(
                new BoardPosition(0, 0),
                new BoardPosition(0, 1),
                new BoardPosition(0, 2),
                new BoardPosition(1, 0),
                new BoardPosition(1, 2),
                new BoardPosition(2, 0),
                new BoardPosition(2, 1),
                new BoardPosition(2, 2)
        );
    }

    @Test
    public void rookImpactsVerticalAndHorizontalNeighbors() throws Exception {
        ChessBoard board = new ChessBoard(3, 3);

        final List<BoardPosition> impactedPositions = ROOK.impactedPositions(board, new BoardPosition(1, 1));

        assertThat(impactedPositions).containsExactly(
                new BoardPosition(0, 1),
                new BoardPosition(2, 1),
                new BoardPosition(1, 0),
                new BoardPosition(1, 2)
        );
    }

    @Test
    public void returnDiagnolHorizonalVerticalPositionsForQueue() throws Exception {
        ChessBoard board = new ChessBoard(3, 4);

        final List<BoardPosition> impactedPositions = QUEEN.impactedPositions(board, new BoardPosition(1, 1));

        assertThat(impactedPositions).containsExactly(
                new BoardPosition(0, 0),
                new BoardPosition(0, 2),
                new BoardPosition(2, 0),
                new BoardPosition(2, 2),
                new BoardPosition(1, 0),
                new BoardPosition(1, 2),
                new BoardPosition(1, 3),
                new BoardPosition(0, 1),
                new BoardPosition(2, 1)
        );
    }

    @Test
    public void returnPositionsForEdgeQueuePosition() throws Exception {
        ChessBoard board = new ChessBoard(3, 4);

        final List<BoardPosition> impactedPositions = QUEEN.impactedPositions(board, new BoardPosition(0, 1));
        assertThat(impactedPositions).containsExactly(
                new BoardPosition(1, 0),
                new BoardPosition(1, 2),
                new BoardPosition(2, 3),
                new BoardPosition(0, 0),
                new BoardPosition(0, 2),
                new BoardPosition(0, 3),
                new BoardPosition(1, 1),
                new BoardPosition(2, 1)
        );
    }

    @Test
    public void returnsDiagnolPositionsForBishop() throws Exception {
        ChessBoard board = new ChessBoard(4, 4);

        final List<BoardPosition> impactedPositions = BISHOP.impactedPositions(board, new BoardPosition(1, 2));
        System.out.println(impactedPositions);
        assertThat(impactedPositions).containsExactly(
                new BoardPosition(0, 1),
                new BoardPosition(0, 3),
                new BoardPosition(2, 1),
                new BoardPosition(3, 0),
                new BoardPosition(2, 3)
        );
    }

    @Test
    public void returnsPositionsForKnight() throws Exception {
        ChessBoard board = new ChessBoard(4, 4);

        final List<BoardPosition> impactedPositions = KNIGHT.impactedPositions(board, new BoardPosition(1, 2));
        assertThat(impactedPositions).containsExactly(
                new BoardPosition(3, 1),
                new BoardPosition(3, 3),
                new BoardPosition(0, 0),
                new BoardPosition(2, 0)
        );
    }
}