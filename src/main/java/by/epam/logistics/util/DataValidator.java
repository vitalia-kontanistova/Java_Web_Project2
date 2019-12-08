package by.epam.logistics.util;

import by.epam.logistics.dao.exception.DAOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {

    private static final String VAN_1FORM_PATTERN = "^.+\\s(true)$";
    private static final String VAN_2FORM_PATTERN = "^.+\\s(false)$";


    private static final DataValidator instance = new DataValidator();

    private DataValidator() {
    }

    public static DataValidator getInstance() {
        return instance;
    }


    public boolean isVanValid(String van) throws DAOException {
        try {
            Pattern pattern = Pattern.compile(VAN_1FORM_PATTERN);
            Matcher matcher1 = pattern.matcher(van);
            pattern = Pattern.compile(VAN_2FORM_PATTERN);
            Matcher matcher2 = pattern.matcher(van);

            return matcher1.find() || matcher2.find();
        } catch (NullPointerException e) {
            throw new DAOException("DataValidator: isVanValid(): null object has been passed into method.");
        }
    }
}