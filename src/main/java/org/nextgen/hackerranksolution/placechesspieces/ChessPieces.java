package org.nextgen.hackerranksolution.placechesspieces;

import com.google.common.base.Optional;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

import java.awt.*;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public enum ChessPieces implements ChessPiece {
    KING((byte)2) {
        @Override
        public List<BoardPosition> impactedPositions(ChessBoard board, BoardPosition position) {
            return neighborCells(board, position);
        }
    },

    QUEEN((byte)3) {
        @Override
        public List<BoardPosition> impactedPositions(ChessBoard board, BoardPosition position) {
            ImmutableList.Builder<BoardPosition> impactedPositions = new ImmutableList.Builder<>();
            return
                    impactedPositions
                            .addAll(diagnalPositions(board, position))
                            .addAll(horizontalPositions(board, position))
                            .addAll(verticalPositions(board, position))
                            .build();
        }
    },

    KNIGHT((byte)4) {
        @Override
        public List<BoardPosition> impactedPositions(ChessBoard board, BoardPosition position) {
            ImmutableList.Builder<BoardPosition> impactedPositions = new ImmutableList.Builder<>();
            final ImmutableList<BoardPosition> positions = impactedPositions
                    .add(position.upperCenter().upperCenter().left())
                    .add(position.upperCenter().upperCenter().right())
                    .add(position.lowerCenter().lowerCenter().left())
                    .add(position.lowerCenter().lowerCenter().right())
                    .add(position.left().left().upperCenter())
                    .add(position.left().left().lowerCenter())
                    .add(position.right().right().upperCenter())
                    .add(position.right().right().lowerCenter())
                    .build();

            return
                    positions.stream()
                            .filter(board::onTheBoard)
                            .collect(toList());
        }
    },

    BISHOP((byte)5) {
        @Override
        public List<BoardPosition> impactedPositions(ChessBoard board, BoardPosition position) {
            return diagnalPositions(board, position);
        }
    },

    ROOK((byte)6) {
        @Override
        public List<BoardPosition> impactedPositions(ChessBoard board, BoardPosition position) {
            final ImmutableList.Builder<BoardPosition> impactedPositions = new ImmutableList.Builder<>();
            impactedPositions
                    .addAll(verticalPositions(board, position))
                    .addAll(horizontalPositions(board, position));

            return impactedPositions.build();
        }
    };


    private static ImpactedPositionPool diagnalImpactedPositionPool = new ImpactedPositionPool();
    private static ImpactedPositionPool horizontalImpactedPositionPool = new ImpactedPositionPool();
    private static ImpactedPositionPool verticalImpactedPositionPool = new ImpactedPositionPool();

    protected static List<BoardPosition> diagnalPositions(ChessBoard board, BoardPosition position) {
        Optional<List<BoardPosition>> cachedPositions = diagnalImpactedPositionPool.findPositions(board, position);
        if(cachedPositions.isPresent()){
            return cachedPositions.get();
        }

        List<BoardPosition> positions = Lists.newArrayList();

        BoardPosition p = position.upperLeft();
        while (board.onTheBoard(p)) {
            positions.add(p);
            p = p.upperLeft();
        }
        p = position.upperRight();
        while (board.onTheBoard(p)) {
            positions.add(p);
            p = p.upperRight();
        }
        p = position.lowerLeft();
        while (board.onTheBoard(p)) {
            positions.add(p);
            p = p.lowerLeft();
        }
        p = position.lowerRight();
        while (board.onTheBoard(p)) {
            positions.add(p);
            p = p.lowerRight();
        }

        diagnalImpactedPositionPool.cachePositions(board, position, positions);

        return positions;
    }

    private static List<BoardPosition> horizontalPositions(ChessBoard board, BoardPosition position) {
        Optional<List<BoardPosition>> cachedPositions = horizontalImpactedPositionPool.findPositions(board, position);
        if(cachedPositions.isPresent()){
            return cachedPositions.get();
        }

        final List<BoardPosition> positions = range(0, board.getColumns())
                .filter(i -> i != position.getY())
                .mapToObj(i -> new BoardPosition(position.getX(), i))
                .collect(toList());
        horizontalImpactedPositionPool.cachePositions(board, position, positions);
        return positions;

    }

    private static List<BoardPosition> verticalPositions(ChessBoard board, BoardPosition position) {
        Optional<List<BoardPosition>> cachedPositions = verticalImpactedPositionPool.findPositions(board, position);
        if(cachedPositions.isPresent()){
            return cachedPositions.get();
        }

        final List<BoardPosition> positions = range(0, board.getRows())
                .filter(i -> i != position.getX())
                .mapToObj(i -> new BoardPosition(i, position.getY()))
                .collect(toList());
        verticalImpactedPositionPool.cachePositions(board, position, positions);
        return positions;
    }

    private static List<BoardPosition> neighborCells(ChessBoard board, BoardPosition location) {
        final ImmutableList<BoardPosition> allNeighbors = ImmutableList.of(
                location.upperLeft(),
                location.upperCenter(),
                location.upperRight(),
                location.left(),
                location.right(),
                location.lowerLeft(),
                location.lowerCenter(),
                location.lowerRight()
        );

        return validCells(board, allNeighbors);
    }

    private static List<BoardPosition> validCells(ChessBoard board, ImmutableList<BoardPosition> cells) {
        return cells.stream()
                .filter(boardPosition -> boardPosition.getX() >= 0 && boardPosition.getX() < board.getRows()
                        && boardPosition.getY() >= 0 && boardPosition.getY() < board.getColumns())
                .collect(toList());
    }

    private final byte val;

    ChessPieces(byte val) {
        this.val = val;
    }

    public byte value() {
        return val;
    }

}
class ImpactedPositionPool {

    private Table<Dimension, BoardPosition, List<BoardPosition>> cache = HashBasedTable.create();

    Optional<List<BoardPosition>> findPositions(ChessBoard board, BoardPosition position) {
        final List<BoardPosition> boardPositions = cache.get(new Dimension(board.getRows(), board.getColumns()), position);
        return boardPositions == null ? Optional.absent() : Optional.of(boardPositions);
    }


    public  void cachePositions(ChessBoard board, BoardPosition position, List<BoardPosition> positions) {
        cache.put(new Dimension(board.getRows(), board.getColumns()), position, positions);
    }
}


