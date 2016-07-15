package org.nextgen.hackerranksolution.placechesspieces;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nextgen.hackerranksolution.placechesspieces.ChessPieces.KING;

public class ChessBoardTest {
    private ChessBoard board;

    @Before
    public void setUp() throws Exception {
        board = new ChessBoard(3, 4);
    }

    @Test
    public void cellStatusChangesWhenPlacingAPiece() throws Exception {
        final BoardPosition boardPosition = new BoardPosition(1, 2);
        assertThat(board.cellStatus(boardPosition)).isEqualTo(board.EMPTY_CELL);
        board.placePiece(boardPosition, KING);
        assertThat(board.cellStatus(boardPosition)).isEqualTo(KING.value());
    }

    @Test
    public void findNextEmpytCell() throws Exception {
        final BoardPosition startPosition = new BoardPosition(1, 1);
        assertThat(board.nextEmptyCellAfter(startPosition)).isEqualTo(startPosition);
        board.placePiece(startPosition, KING);
        assertThat(board.nextEmptyCellAfter(startPosition)).isEqualTo(startPosition.right());
    }

    @Test
    public void convertIndexToPosition() throws Exception {
        assertThat(board.toPosition(6)).isEqualTo(new BoardPosition(1, 2));
    }

    @Test
    public void cannotPlacePieceIfItThreatensExistingPiece() throws Exception {
        board.placePiece(new BoardPosition(1, 1), KING);
        assertThat(board.tryPlaceAPieceAtCurrentPosition(KING)).isFalse();
    }

    @Test
    public void canPlacePieceIfItDoesNotThreatenExistingPiece() throws Exception {
        board.placePiece(new BoardPosition(1, 1), KING);
        board.advanceCurrentPosition();
        assertThat(board.tryPlaceAPieceAtCurrentPosition(KING)).isTrue();

    }

    @Test
    public void cannotAdvanceToNextPositionIfAlreadyAtEnd() throws Exception {
        assertThat(board.shiftPositionRight(new BoardPosition(2, 3))).isNull();
        assertThat(board.shiftPositionRight(new BoardPosition(1, 3))).isEqualTo(new BoardPosition(2, 0));
    }

    @Test
    public void copyBoardIsADeepCopy() throws Exception {
        final ChessBoard newBoard = board.copyBoard();
        final BoardPosition p = new BoardPosition(1, 1);
        assertThat(board.cellStatus(p)).isEqualTo(board.EMPTY_CELL);
        newBoard.placePiece(p, KING);
        assertThat(board.cellStatus(p)).isEqualTo(board.EMPTY_CELL);
        assertThat(newBoard.cellStatus(p)).isEqualTo(KING.value());
    }
}