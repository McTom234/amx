package de.humboldtgym.amx.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import de.humboldtgym.amx.exceptions.DataException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataManager {
    private final Gson gson;

    private DataSet loadedSet;

    public DataManager() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public void loadDataSet(Path jsonInput) throws DataException {
        try(BufferedReader in = Files.newBufferedReader(jsonInput)) {
            this.loadedSet = gson.fromJson(in, DataSet.class);
        } catch(JsonSyntaxException e) {
            throw new DataException("Corrupted Json received", e);
        } catch (IOException e) {
            throw new DataException("An I/O error occurred while loading", e);
        }
    }

    public void saveDataSet(Path jsonOutput) throws DataException {
        // TODO: Parent paths?

        try(BufferedWriter out = Files.newBufferedWriter(jsonOutput)) {
            gson.toJson(this.loadedSet, DataSet.class, out);
        } catch (IOException e) {
            throw new DataException("Failed to write Json data", e);
        }
    }

    public DataSet getLoadedSet() {
        return loadedSet;
    }
}
