package by.epam.logistics.service;

import by.epam.logistics.Base;
import by.epam.logistics.Van;
import by.epam.logistics.dao.exception.DAOException;
import by.epam.logistics.service.exception.ServiceException;
import by.epam.logistics.util.FileManipulator;
import by.epam.logistics.util.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class VanServiceImpl implements VanService {
    private FileManipulator fileManipulator;
    private Parser parser;

    private static VanServiceImpl instance = new VanServiceImpl();

    private VanServiceImpl() {
        fileManipulator = FileManipulator.getInstance();
        parser = Parser.getInstance();
    }

    public static VanServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Van> createFromFile(Base base) throws ServiceException {

        try {
            List<Van> vans = new ArrayList<>();
            List<String> ellipsesStr = fileManipulator.extractEntriesFromFile();

            for (String str : ellipsesStr) {
                Van currentVan = createFromString(str, base);
                vans.add(currentVan);
            }
            return vans;

        } catch (ServiceException | DAOException e) {
            throw new ServiceException("VanServiceImpl: createFromFile(): " + e.getMessage());
        }
    }

    private Van createFromString(String vanStr, Base base) throws ServiceException {
        try {
            String[] vanParam = parser.parseVan(vanStr);

            String name = vanParam[0];
            AtomicBoolean perishable = new AtomicBoolean(vanParam[1].equals("true"));
            return new Van(base, name, perishable);

        } catch (DAOException e) {
            throw new ServiceException("VanServiceImpl: createFromString(): " + e.getMessage());
        }
    }
}