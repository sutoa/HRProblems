package org.nextgen.hackerranksolution.placechesspieces;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nextgen.hackerranksolution.placechesspieces.ChessPieces.*;

public class ChessPieceDistributorTest {
    private ChessPieceDistributor chessPieceDistributor = new ChessPieceDistributor();

    @Test
    public void findNumberOfWaysToPlaceChessPieces() throws Exception {
        ChessBoard emptyBoard = new ChessBoard(3, 3);
        final ImmutableList<ChessPieces> pieces = ImmutableList.of(KING, KING, ROOK);
        assertThat(chessPieceDistributor.numberOfWaysToPlace(emptyBoard, pieces)).isEqualTo(4);
    }
    @Test
    public void singlePieceCanBePlacedAnywhere() throws Exception {
        ChessBoard emptyBoard = new ChessBoard(2, 2);
        final ImmutableList<ChessPieces> pieces = ImmutableList.of(KING);
        assertThat(chessPieceDistributor.numberOfWaysToPlace(emptyBoard, pieces)).isEqualTo(4);
    }

    @Test
    public void theMotherLoad() throws Exception {
        ChessBoard emptyBoard = new ChessBoard(6, 9);
        final ImmutableList<ChessPieces> pieces = ImmutableList.of(KING, KING, QUEEN, BISHOP, ROOK, KNIGHT);
        assertThat(chessPieceDistributor.numberOfWaysToPlace(emptyBoard, pieces)).isEqualTo(20136752);
    }
}
