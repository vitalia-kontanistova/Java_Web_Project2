package by.epam.logistics.util;


import by.epam.logistics.dao.exception.DAOException;

public class Parser {
    private DataValidator dataValidator;

    private static final Parser instance = new Parser();

    private Parser() {
        dataValidator = DataValidator.getInstance();
    }

    public static Parser getInstance() {
        return instance;
    }


    public String[] parseVan(String entry) throws DAOException {

        try {
            if (dataValidator.isVanValid(entry)) {
                return entry.split("\\s");
            } else {
                throw new DAOException("Parser: parseVan(): Incorrect string entry format.");
            }
        } catch (NullPointerException e) {
            throw new DAOException("Parser: parseVan(): " + e.getMessage());
        }
    }
}