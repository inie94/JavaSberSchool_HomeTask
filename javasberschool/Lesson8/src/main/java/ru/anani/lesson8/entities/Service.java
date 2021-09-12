package ru.anani.lesson8.entities;

import ru.anani.lesson8.annotation.Cache;

import java.util.Date;
import java.util.List;

import static ru.anani.lesson8.annotation.CachedType.FILE;
import static ru.anani.lesson8.annotation.CachedType.IN_MEMORY;

public interface Service {
    @Cache(cacheType = FILE, fileNamePrefix = "data", zip = true,
            ignoreIdentificationBy = {3}
    )
    List<String> run(String item, double value, Date date);

    @Cache(cacheType = IN_MEMORY, listList = 5)
    List<String> work(String item);
}
