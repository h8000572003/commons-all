package io.github.h800572003.textcut.paser;


import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;

public class ExcelRow<T> implements IExcelRow {

    private Row row;


    private ExcelTableStrategy<T> strategy;

    public ExcelRow(Row row, ExcelTableStrategy<T> excelTableStrategy) {
        this.row = row;
        this.strategy = excelTableStrategy;

    }

    @Override
    public String getString(int i) {
        return strategy.getStringICellStringValueFormat().to(getCell(i));
    }



    public Cell getCell(int i) {
        return this.row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
    }

}
