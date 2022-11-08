package io.github.h800572003.textcut.paser;

import org.apache.poi.ss.usermodel.Workbook;

public interface IExcelContext<T> {

    T getTarget();

    Workbook getWorkbook();


    boolean hasNext();

    IExcelRow next();

    void setSheet(String sheet);
}
