## commons-generator 共用程式碼產生

減少重複性撰寫程式，提供共用元件使用

### CHANGELOG

- 2022.09.29 v.0.0.1
> 初版
- 2022.10.03 v.0.0.2
> 加入策略機制，產生共通結構檔案

### 建立元件

- NewClass 物件
- NewAnnontation 標籤
- NewComment 備註
- NewField 欄位
- NewStringLine 任意輸入文字
- NewMethod 建立方法

###

### 程式範例(NewFileGeneratorTest)

### 內容建立策略

- SqlGenerator

輸入sql產生對應物件之結果物件

- BaseDTOGenerator

透過程式加入DTO 物件建立

```java
          newClass
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
                .addConstructorArg(new MethodArgs.MethodArg("String", "name"));¬
```

檔案產製

```java
 void exportTest() {

        NewFileGenerator.IFileGeneratorOutput output = new FileGeneratorOutput("./src/main/java");

        NewFileGenerator generator = new NewFileGenerator(this::generator, output);
        generator.export();


    }
```

### 物件關係圖

```mermaid
classDiagram
  
    ICode <|-- INewFile
    ICode <|-- IBodyCode
  
   

    ICode <|-- NewField
    ICode <|-- NewClass
    ICode <|-- NewComment
    ICode <|-- NewAnnontation
    ICode <|-- NewStringLine
    ICode <|-- NewMethod

    INewFile <|-- NewFile
  
```

### mavne 依賴


```xml
<dependency>
        <groupId>io.github.h8000572003</groupId>
	<artifactId>commons-generator</artifactId>
	<version>0.0.1</version>
</dependency>
```
