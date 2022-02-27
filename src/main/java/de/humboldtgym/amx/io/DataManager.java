package de.humboldtgym.amx.io;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import de.humboldtgym.amx.exceptions.DataException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataManager {
    private final ObjectReader reader;
    private final ObjectWriter writer;

    private DataSet loadedSet;

    public DataManager() {
        ObjectMapper mapper = new ObjectMapper();
        this.reader = mapper.reader();
        this.writer = mapper.writerWithDefaultPrettyPrinter();
    }

    public void loadDataSet(Path jsonInput) throws DataException {
        try (InputStream in = Files.newInputStream(jsonInput)) {
            this.loadedSet = reader.forType(DataSet.class).readValue(in);
        } catch (JsonParseException e) {
            throw new DataException("Corrupted Json received", e);
        } catch (IOException e) {
            throw new DataException("An I/O error occurred while loading", e);
        }
    }

    public void saveDataSet(Path jsonOutput) throws DataException {
        Path parent = jsonOutput.getParent();
        if(parent != null && !Files.isDirectory(parent)) {
            try {
                Files.createDirectories(parent);
            } catch (IOException e) {
                throw new DataException("Failed to create parent directories for saving", e);
            }
        }

        try (OutputStream out = Files.newOutputStream(jsonOutput)) {
            this.writer.writeValue(out, this.loadedSet);
        } catch (IOException e) {
            throw new DataException("Failed to write Json data", e);
        }
    }

    public void unloadSet() {
        this.loadedSet = null;
    }

    public void newSet() {
        this.loadedSet = new DataSet();
    }

    public DataSet getLoadedSet() {
        return loadedSet;
    }

    public ObjectReader getObjectReader() {
        return reader;
    }
}
