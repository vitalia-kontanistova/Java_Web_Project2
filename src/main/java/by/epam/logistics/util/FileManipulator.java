package by.epam.logistics.util;

import by.epam.logistics.dao.exception.DAOException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManipulator {
    private final String path = "src/main/resources/VanBase.txt";
    private DataValidator dataValidator;

    private static FileManipulator instance = new FileManipulator();

    private FileManipulator() {
        dataValidator = DataValidator.getInstance();
    }

    public static FileManipulator getInstance() {
        return instance;
    }


    public List<String> extractEntriesFromFile() throws DAOException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            List<String> aLLEntries = new ArrayList<>();

            String entry;
            while ((entry = bufferedReader.readLine()) != null) {
                if (dataValidator.isVanValid(entry)) {
                    aLLEntries.add(entry);
                }
            }

            return aLLEntries;
        } catch (NullPointerException | IOException | DAOException e) {
            throw new DAOException("FileManipulator: extractEntriesFromFile(): " + e.getMessage());
        }
    }
}