package io.github.h800572003.textcut.paser;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class ExcelTableStrategyTest implements ExcelTableStrategy.IExcelTableMapper<List<Stuednt>> {


    @Test
    void mapper() {

        List<Stuednt> stuednts = new ArrayList<>();
        try (InputStream resourceAsStream = ExcelTableStrategyTest.class.getResourceAsStream("/Book1.xlsx")) {
            if (resourceAsStream != null) {
                try (final Workbook sheets = WorkbookFactory.create(resourceAsStream)) {
                    final ExcelTableStrategy<List<Stuednt>> excelTableStrategy = new ExcelTableStrategy<>(
                            sheets,//
                            this, //
                            stuednts);//


                    List<Stuednt> target = excelTableStrategy.getData();
                    log.info("targer:{}", target);
                    assertThat(target).hasSize(2);
                } catch (IOException e) {
                    log.info("e", e);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void execute(IExcelContext<List<Stuednt>> excelContext) {
        excelContext.setSheet("Sheet1");
        int headerIndex = 0;
        while (excelContext.hasNext()) {

            if (headerIndex++ == 0) {
                excelContext.next();
//                表頭pass
            } else {
                final IExcelRow excelRow = excelContext.next();
                final Stuednt stuednt = new Stuednt();
                stuednt.setName(excelRow.getString(0));
                stuednt.setId(excelRow.getString(1));
                stuednt.setAge(excelRow.getString(2).toString());
                stuednt.setClassString(excelRow.getString(3).toString());
                excelContext.getTarget().add(stuednt);
            }
        }
    }
}