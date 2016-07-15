package org.nextgen.hackerranksolution.placechesspieces;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;

public class ChessPieceDistributor {
    public int numberOfWaysToPlace(ChessBoard board, List pieces) {
        if(pieces.isEmpty()) return 1;

        Multiset<ChessPieces> groupedPieces = HashMultiset.create(pieces);
        final Set<ChessPieces> possiblePiecesForNextMove = groupedPieces.elementSet();

        int numberOfWays = 0;
        for(ChessPieces piece : possiblePiecesForNextMove){
            ChessBoard newBoard = board.copyBoard();
            Boolean successfulPlacement = newBoard.tryPlaceAPieceAtCurrentPosition(piece);
            if(successfulPlacement){
                if(lastPiece(pieces)){
//                    System.out.println(newBoard);
                    numberOfWays++;
                } else {
                    List<ChessPieces> remainingPieces = newArrayList(pieces);
                    remainingPieces.remove(piece);
                    numberOfWays += numberOfWaysToPlace(newBoard, remainingPieces) ;
                }
            }
        }
        
        if(!board.noMorePositionToPlacePiece()) numberOfWays += waysIfSkipTheCell(board, pieces);
        return numberOfWays;
    }

    private int waysIfSkipTheCell(ChessBoard board, List pieces) {
        board.advanceCurrentPosition();
        return numberOfWaysToPlace(board, pieces);
    }

    private boolean lastPiece(List pieces) {
        return pieces.size() == 1;
    }


}
