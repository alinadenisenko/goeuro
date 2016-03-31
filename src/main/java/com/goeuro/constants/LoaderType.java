package com.goeuro.constants;

import com.goeuro.service.DataLoader;
import com.goeuro.service.RestAPILoader;

public enum LoaderType {
    RESTAPI(new RestAPILoader());

    private DataLoader loader;

    LoaderType(DataLoader loader) {
        this.loader = loader;
    }

    public DataLoader getLoader() {
        return loader;
    }
}
