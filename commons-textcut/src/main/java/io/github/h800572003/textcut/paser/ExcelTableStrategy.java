package io.github.h800572003.textcut.paser;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


@Slf4j
public class ExcelTableStrategy<T> implements IReaderStrategy<T>, IExcelContext<T> {


    private Workbook workbook;
    private Sheet sheet;


    private final IExcelTableMapper<T> mapper;

    private final T target;

    private Row next;


    private int rowIndex = 0;

    private final ICellValueFormat<String> stringICellStringValueFormat;


    public ExcelTableStrategy(Workbook workbook, IExcelTableMapper<T> mapper, T target, ICellValueFormat<String> stringICellStringValueFormat) {
        this.workbook = workbook;
        this.mapper = mapper;
        this.target = target;
        this.sheet = workbook.getSheetAt(0);
        this.rowIndex = 0;
        this.stringICellStringValueFormat = stringICellStringValueFormat;
    }
    public ExcelTableStrategy(Workbook workbook, IExcelTableMapper<T> mapper, T target){
        this(workbook,mapper,target,new CellStringValueFormat());
    }



    @Override
    public T getData() {
        mapper.execute(this);
        return target;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    @Override
    public boolean hasNext() {
        return this.rowIndex < this.sheet.getPhysicalNumberOfRows();
    }

    @Override
    public IExcelRow next() {
        return new ExcelRow<>(this.sheet.getRow(rowIndex++), this);

    }


    @Override
    public void setSheet(String sheetName) {
        this.sheet =
                workbook.getSheet(sheetName);
        this.rowIndex = 0;
    }


    public T getTarget() {
        return target;
    }

    interface IExcelTableMapper<T> {
        public void execute(IExcelContext<T> excelContext);

    }

    public ICellValueFormat<String> getStringICellStringValueFormat() {
        return stringICellStringValueFormat;
    }
}
