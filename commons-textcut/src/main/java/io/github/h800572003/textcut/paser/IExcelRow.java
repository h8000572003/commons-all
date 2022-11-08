package io.github.h800572003.textcut.paser;

import org.apache.poi.ss.usermodel.Cell;


public interface IExcelRow {
    String getString(int i);


    Cell getCell(int i);
}
