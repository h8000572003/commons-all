package io.github.h800572003.textcut.paser;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public abstract class ReaderStrategy<T> implements ExcelTableStrategy.IExcelTableMapper {


    private ExcelContext<T>context;

}
