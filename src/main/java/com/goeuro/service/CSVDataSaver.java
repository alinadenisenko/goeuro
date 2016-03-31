package com.goeuro.service;

import com.goeuro.model.Data;
import com.goeuro.utils.ApplicationException;
import com.goeuro.utils.Ignore;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

public class CSVDataSaver implements DataSaver {

    @Override
    public <T> void save(Collection<? extends Data> collection, Class<T> memberClass) {
        String fileName = memberClass.getSimpleName() + "_" + System.currentTimeMillis() + ".csv";
        try (FileWriter fw = new FileWriter(fileName)) {
            collection.stream().forEach(data -> writeToFile(fw, dataView(data)));
        } catch (IOException e) {
            throw new ApplicationException(e);
        }
    }

    private String dataView(Data data) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(data.getClass().getDeclaredMethods())
                .filter(method -> method.getName().startsWith("get"))
                .filter(method -> !method.isAnnotationPresent(Ignore.class))
                .forEach(method -> sb.append("\"").append(getFieldValue(method, data)).append("\";"));
        return sb.toString().replaceAll(",$", "");
    }

    private void writeToFile(FileWriter fw, String pkg) {
        try {
            fw.write(String.format("%s%n", pkg));
        } catch (IOException e) {
            throw new ApplicationException(e);
        }
    }

    private String getFieldValue(Method method, Data o) {
        try {
            Object value = method.invoke(o);
            return value != null ? value.toString() : "";
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ApplicationException(e);
        }
    }
}
