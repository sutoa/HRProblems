package org.nextgen.hackerranksolution.placechesspieces;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public enum ChessPieces implements ChessPiece {
    KING(2) {
        @Override
        public List<BoardPosition> impactedPositions(ChessBoard board, BoardPosition position) {
            return neighborCells(board, position);
        }
    },

    QUEEN(3) {
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

    KNIGHT(4) {
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
                            .filter(p -> board.onTheBoard(p))
                            .collect(toList());
        }
    },

    BISHOP(5) {
        @Override
        public List<BoardPosition> impactedPositions(ChessBoard board, BoardPosition position) {
            return diagnalPositions(board, position);
        }
    },

    ROOK(6) {
        @Override
        public List<BoardPosition> impactedPositions(ChessBoard board, BoardPosition position) {
            final ImmutableList.Builder<BoardPosition> impactedPositions = new ImmutableList.Builder<>();
            impactedPositions
                    .addAll(verticalPositions(board, position))
                    .addAll(horizontalPositions(board, position));

            return impactedPositions.build();
        }
    };

    protected static List<BoardPosition> diagnalPositions(ChessBoard board, BoardPosition position) {
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

        return positions;
    }

    private static List<BoardPosition> horizontalPositions(ChessBoard board, BoardPosition position) {
        return range(0, board.getColumns())
                .filter(i -> i != position.getY())
                .mapToObj(i -> new BoardPosition(position.getX(), i))
                .collect(toList());

    }

    private static List<BoardPosition> verticalPositions(ChessBoard board, BoardPosition position) {
        return range(0, board.getRows())
                .filter(i -> i != position.getX())
                .mapToObj(i -> new BoardPosition(i, position.getY()))
                .collect(toList());
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

    private final int val;

    ChessPieces(int val) {
        this.val = val;
    }

    public int value() {
        return val;
    }

}
