package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.ICodeContext;
import io.github.h800572003.generator.NewFileBuilder;
import io.github.h800572003.generator.NewFileException;
import io.github.h800572003.generator.contract.Protecteds;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class NewFileTest {

    private NewFile newFile;

    private ICodeContext context = Mockito.mock(ICodeContext.class);

    @BeforeEach
    void init() {
        NewFileBuilder newFileBuilder = new NewFileBuilder(context);
        newFileBuilder.setName("Apple");
        newFileBuilder.setProtectedValue(Protecteds.PUBLIC);
        this.newFile = newFileBuilder.build();
        this.newFile.getNewClass().setPackage("io.github.h800572003");
    }

    @Test
    void getPath() {
        String path = newFile.getPath();
        log.info("path:{}",path);
        assertThat(path).contains("io/github/h800572003");
    }

    @Test
    void getFileName() {
        String fileName = newFile.getFileName();
        log.info("fileName:{}",fileName);
        assertThat(fileName).contains("Apple.java");
    }
    @Test
    void test_exception_withoutName(){
        assertThatThrownBy(() -> {
            NewFileBuilder newFileBuilder = new NewFileBuilder(context);

            newFileBuilder.setProtectedValue(Protecteds.PUBLIC);
            this.newFile = newFileBuilder.build();

        }).isInstanceOf(NewFileException.class).hasMessageContaining("name");

    }
    @Test
    void test_exception_withoutProtected(){
        assertThatThrownBy(() -> {
            NewFileBuilder newFileBuilder = new NewFileBuilder(context);
            newFileBuilder.setName("Apple");
            this.newFile = newFileBuilder.build();

        }).isInstanceOf(NewFileException.class).hasMessageContaining("protectedValue");

    }
}
