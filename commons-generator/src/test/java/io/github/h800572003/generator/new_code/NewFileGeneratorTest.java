package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.ICodeContext;
import io.github.h800572003.generator.NewFileGenerator;
import io.github.h800572003.generator.contract.Protecteds;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@Slf4j
class NewFileGeneratorTest implements NewFileGenerator.IFileGeneratorOutput {
    public static final String MEMO = "測試memo";

    private void generator(ICodeContext codeContext) {


        codeContext.createNewFile()//
                .setProtectedValue(Protecteds.PUBLIC)//
                .setName("Apple")//
                .build()//建立新檔案
                .getNewClass().setPackage(NewClassTest.class.getPackage().getName())//
                .addImport(NewClassTest.class.getName())//
                .addImport(NewClass.class.getName())//
                .addBody("//TODO;")
                .addBody(new NewField(Protecteds.PUBLIC, "name", "String").setFinal(false))
                .addBody(new NewMethod(Protecteds.PUBLIC, "hello")//
                        .addMethodArg(new MethodArgs.MethodArg("String", "helloName")))//
                .setMemo(MEMO)//
                .addAnnotation(new NewAnnotation("@Slf4j"))
                .addConstructorArg(new MethodArgs.MethodArg("String", "name"));

    }

    @Test
    void export() {

        NewFileGenerator.IFileGeneratorOutput output = Mockito.spy(this);

        NewFileGenerator generator = new NewFileGenerator(this::generator, output);
        generator.export();

        Mockito.verify(output, Mockito.times(1)).export(Mockito.eq(generator));

    }

    @Override
    public void export(NewFileGenerator newFileGenerator) {
        for (NewFile newFile : newFileGenerator.getNewFiles()) {
            log.info(newFile.toString());
        }
    }


}