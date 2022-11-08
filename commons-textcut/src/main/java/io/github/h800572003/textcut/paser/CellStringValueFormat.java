package io.github.h800572003.textcut.paser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CellStringValueFormat implements ICellValueFormat<String> {

    private Map<CellType, Function<Cell, String>> map = new HashMap<>();
    private Function<Cell, String> defGetter;

    public CellStringValueFormat() {
        map.put(CellType.STRING,Cell::getStringCellValue);
        map.put(CellType.NUMERIC,i-> String.valueOf(i.getNumericCellValue()));


    }

    /**
     * @param cell
     * @param function
     * @param defGetter
     */
    public void reregister(CellType cell,
                           Function<Cell, String> function,
                           Function<Cell, String> defGetter) {
        map.put(cell, function);
        this.defGetter = defGetter;
    }

    @Override
    public String to(Cell cell) {
        return map.getOrDefault(cell.getCellType(), defGetter).apply(cell);
    }
}
