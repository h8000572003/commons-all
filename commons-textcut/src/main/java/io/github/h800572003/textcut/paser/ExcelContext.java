package io.github.h800572003.textcut.paser;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelContext<T> {
    protected  Row next;
    protected  int rowIndex;
    protected  Sheet sheet;

    protected T target;



    public Row getNext() {
        return next;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public Sheet getSheet() {
        return sheet;
    }
}
