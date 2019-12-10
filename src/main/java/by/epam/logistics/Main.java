package by.epam.logistics;

import by.epam.logistics.service.VanServiceImpl;
import by.epam.logistics.service.exception.ServiceException;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Base base = Base.getInstance();

        try {
            List<Van> vans = VanServiceImpl.getInstance().createFromFile(base);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        base.processQueue();

    }

}

