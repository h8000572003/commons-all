package io.github.h800572003.zip;

import io.github.h800572003.zip.upzip.IRemoveStrategy;
import io.github.h800572003.zip.upzip.IUnZipService;
import io.github.h800572003.zip.upzip.UnZipService;
import io.github.h800572003.zip.upzip.UnZipStrategy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class UnZipServiceTest {

    final IUnZipService service = new UnZipService();


    @Test
    void unZip() throws IOException {
        this.unZip(Mockito.mock(IRemoveStrategy.class), "/test.zip", 4);
    }

    @Test
    void unFoldWithZip() throws IOException {
        this.unZip(Mockito.mock(IRemoveStrategy.class), "/fold.zip", 2);
    }


    boolean filte(String name) {
        List<String> fileNames = new ArrayList<>();
        fileNames.add("DS_Store");
        fileNames.add("_MACOSX");
        for(String file:fileNames){
            if(name.contains(file)){
                return false;
            }
        }


        return true;
    }

    void unZip(IRemoveStrategy removeStrategy, String name, int wantedNumberOfInvocations) throws IOException {
        //GIVE ZIP
        try (final InputStream inputStream = UnZipServiceTest.class.getResourceAsStream(name)) {
            this.service.execute(inputStream, context -> {
                for (IZip zip : context.getZips(this::filte)) {
                    if (zip.isDir()) {
                        //pass
                    } else {
                        log.info("file name:{} size:{}", zip.getName(),zip.getSize());
                        zip.remove(removeStrategy);
                    }
                }
            });
        } catch (Exception e) {
            log.error("e:{}", name, e);

        }
        Mockito.verify(removeStrategy, Mockito.times(wantedNumberOfInvocations)).remove(Mockito.any(), Mockito.any());

    }

    static UnZipStrategy



}