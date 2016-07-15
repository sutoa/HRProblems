package org.nextgen.hackerranksolution.placechesspieces;

import java.util.List;
import java.util.OptionalInt;

import static java.util.Arrays.copyOf;
import static java.util.Arrays.fill;
import static java.util.stream.IntStream.range;

public class ChessBoard {
    static public final int EMPTY_CELL = 0;
    static public final int THREATENED_CELL = 1;
    static private BoardPosition STARTING_POSITION = new BoardPosition(0, 0);

    private int[] cells;
    private int rows;
    private int columns;
    private BoardPosition intendedPosition = STARTING_POSITION;


    public ChessBoard(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        cells = new int[rows * columns];
        fill(cells, EMPTY_CELL);
    }


    public boolean tryPlaceAPieceAtCurrentPosition(ChessPieces piece) {
        if (intendedPosition == null || !safePosition(intendedPosition))
            return false;

        final List<BoardPosition> impactedPositions = piece.impactedPositions(this, intendedPosition);
        for (BoardPosition p : impactedPositions) {
            if (isCellOccupied(p)) {
                return false;
            } else {
                markAsThreatened(p);
            }
        }
        placePiece(intendedPosition, piece);
        return true;

    }

    public int cellStatus(BoardPosition p) {
        return cells[toIndex(p)];
    }

    protected BoardPosition toPosition(int idxToTry) {
        return new BoardPosition(idxToTry / columns, idxToTry % columns);
    }

    private int toIndex(BoardPosition p) {
        return p.getX() * columns + p.getY();
    }

    protected BoardPosition shiftPositionRight(BoardPosition intendedPosition) {
        final int idx = toIndex(intendedPosition);
        if (idx == cells.length - 1)
            return null;
        else
            return toPosition(idx + 1);
    }

    private void markAsThreatened(BoardPosition p) {
        cells[toIndex(p)] = THREATENED_CELL;
    }

    private boolean isCellOccupied(BoardPosition p) {
        final int status = cellStatus(p);
        return status != EMPTY_CELL && status != THREATENED_CELL;
    }

    public ChessBoard copyBoard() {
        ChessBoard board = new ChessBoard(rows, columns);
        board.setCells(copyOf(cells, cells.length));
        board.intendedPosition = intendedPosition;
        return board;
    }

    protected BoardPosition nextEmptyCellAfter(BoardPosition p) {
        final OptionalInt emptyCellIdx = range(toIndex(p), cells.length)
                .filter(idx -> cells[idx] == EMPTY_CELL)
                .findFirst();
        if (!emptyCellIdx.isPresent())
            return null;
        else
            return toPosition(emptyCellIdx
                    .getAsInt());
    }

    public void placePiece(BoardPosition p, ChessPieces piece) {
        cells[toIndex(p)] = piece.value();
        if(noMorePositionToPlacePiece()) return;
        intendedPosition = shiftPositionRight(p);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setCells(int[] cells) {
        this.cells = cells;
    }

    public void advanceCurrentPosition() {
        advanceCurrentPosition(1);
    }

    public void advanceCurrentPosition(int positions) {
        for (int i = 0; i < positions; i++) {
            intendedPosition = nextEmptyCellAfter(shiftPositionRight(intendedPosition));
        }
    }

    private boolean safePosition(BoardPosition intendedPosition) {
        return cellStatus(intendedPosition) == EMPTY_CELL;
    }

    public boolean noMorePositionToPlacePiece() {
        return intendedPosition == null || toIndex(intendedPosition) == cells.length - 1;
    }

    @Override
    public String toString() {
        return range(0, cells.length)
                .mapToObj(i -> i%columns==0 ? "\n" + Integer.valueOf(cells[i]) : Integer.valueOf(cells[i]))
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    public boolean onTheBoard(BoardPosition p) {
        return p.getX() >= 0 && p.getX() < rows
                && p.getY() >= 0 && p.getY() < columns;
    }
}


