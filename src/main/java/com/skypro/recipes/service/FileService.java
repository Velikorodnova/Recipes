package com.skypro.recipes.service;

import java.io.File;
import java.nio.file.Path;

public interface FileService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();

    File getDataFile();

    Path createTempFile(String suffix);
}
