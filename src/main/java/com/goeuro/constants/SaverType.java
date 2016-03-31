package com.goeuro.constants;

import com.goeuro.service.CSVDataSaver;
import com.goeuro.service.DataSaver;

public enum SaverType {
    CSV(new CSVDataSaver());

    private DataSaver saver;

    SaverType(DataSaver saver) {
        this.saver = saver;
    }

    public DataSaver getSaver() {
        return saver;
    }
}
