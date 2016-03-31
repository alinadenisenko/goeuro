package com.goeuro;

import com.goeuro.constants.LoaderType;
import com.goeuro.constants.SaverType;
import com.goeuro.model.Data;
import com.goeuro.model.Location;
import com.goeuro.utils.ApplicationException;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Application {
    private final LoaderType loaderType;
    private final SaverType saverType;

    public Application(LoaderType loaderType, SaverType saverType) {
        this.loaderType = loaderType;
        this.saverType = saverType;
    }

    public <T extends Data> void process(Set<String> params, Class<T> dataType) {
        Collection<? extends Data> data = loaderType.getLoader().load(params, dataType);
        if (data.isEmpty()) {
            throw new ApplicationException("Data was not found for:"
                    + dataType.getSimpleName() + ", params: " + params);
        }
        saverType.getSaver().save(data, dataType);
    }

    public static void main(String[] args) {
        Set<String> params = new HashSet<>(Arrays.asList(args));
        if (params.isEmpty()) {
            throw new ApplicationException("Input params cannot be empty");
        }
        Application app = new Application(LoaderType.RESTAPI, SaverType.CSV);
        try {
            app.process(params, Location.class);
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }
}
