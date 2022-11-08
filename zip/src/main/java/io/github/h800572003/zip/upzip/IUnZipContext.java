package io.github.h800572003.zip.upzip;

import io.github.h800572003.zip.IZip;

import java.util.List;
import java.util.function.Predicate;

public interface IUnZipContext {


    List<IZip> getZips(Predicate<String> predicate);

    default List<IZip> getZips() {
        return getZips(i -> true);
    }

}
