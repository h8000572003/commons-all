package io.github.h800572003.generator;

import io.github.h800572003.generator.new_code.INewFile;
import io.github.h800572003.generator.utils.CoderUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

/**
 * 檔案產出
 */

public class FileGeneratorOutput implements NewFileGenerator.IFileGeneratorOutput {

    private final String base;

    public FileGeneratorOutput(String base) {
        this.base = base;
    }

    @Override
    public void export(NewFileGenerator newFileGenerator) {
        newFileGenerator.getNewFiles().forEach(this::newCode);
    }

    private void newCode(INewFile file) {
        final String filePath = CoderUtils.getFilePath(base, file.getPath());
        final File outFile = new File(filePath);
        this.createPackageFolder(outFile);
        this.createNewFile(file, outFile);
    }

    private void createNewFile(INewFile file, File outFile) {
        File newOutFile = new File(outFile, file.getFileName());
        try (FileWriter writer = new FileWriter(newOutFile, false)) {
            IOUtils.write(file.toString(), writer);
        } catch (IOException e) {
            throw new NewFileException("file:" + newOutFile, e);
        }
    }

    private void createPackageFolder(File packageFolder) {
        if (!packageFolder.exists()) {
            try {
                Files.createDirectories(packageFolder.toPath());
            } catch (IOException e) {
                throw new NewFileException(packageFolder.toPath() + " error", e);
            }
        }
    }
}
