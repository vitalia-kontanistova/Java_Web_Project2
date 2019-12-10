package by.epam.logistics.service;


import by.epam.logistics.Base;
import by.epam.logistics.Van;
import by.epam.logistics.service.exception.ServiceException;

import java.util.List;

public interface VanService {
    List<Van> createFromFile(Base base) throws ServiceException;
}