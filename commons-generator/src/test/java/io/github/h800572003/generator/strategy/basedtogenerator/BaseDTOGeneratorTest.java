package io.github.h800572003.generator.strategy.basedtogenerator;

import io.github.h800572003.generator.IFileGeneratorOutput;
import io.github.h800572003.generator.NewFileGenerator;
import io.github.h800572003.generator.StringGeneratorOutput;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.StringWriter;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class BaseDTOGeneratorTest {


    BaseDTOGenerator baseDTOGenerator = new BaseDTOGenerator();
    StringGeneratorOutput output= new StringGeneratorOutput();


    @BeforeEach
    void init() {


        StringGeneratorOutput o=Mockito.spy(this.output);

        this.baseDTOGenerator.setName("Student");
        this.baseDTOGenerator.setMemo("學生");
        this.baseDTOGenerator.setPackageValue("io.github.h800572003.generator.strategy.basedtogenerator");

        this.baseDTOGenerator.add(new BaseGenDTO.BaseGenDTOBuilder()
                .type("String")
                .isNum(false)
                .memo("學號")
                .name("id")
                .value("").build());
        this.baseDTOGenerator.add(new BaseGenDTO.BaseGenDTOBuilder()
                .type("int")
                .isNum(true)
                .memo("年紀")
                .name("age")
                .value("0").build());
        this.baseDTOGenerator.add(new BaseGenDTO.BaseGenDTOBuilder()
                .type("String")
                .isNum(false)
                .memo("姓名")
                .name("name")
                .value("").build());


    }

    @Test
    void export() {
        final NewFileGenerator generator = new NewFileGenerator(baseDTOGenerator, output);
        generator.export();


        log.info("out:{}", output.toString());
        assertThat(output.toString()).isNotBlank();

    }


}




