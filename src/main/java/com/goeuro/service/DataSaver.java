package com.goeuro.service;

import com.goeuro.model.Data;

import java.util.Collection;

public interface DataSaver {
    <T> void save(Collection<? extends Data> data, Class<T> memberClass);
}
