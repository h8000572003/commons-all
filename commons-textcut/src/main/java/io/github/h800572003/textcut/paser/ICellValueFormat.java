package io.github.h800572003.textcut.paser;

import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @param <T>
 */
public interface ICellValueFormat<T> {
    /**
     *
     * @param cell
     * @return
     */
    T to(Cell cell);
}
