package com.goeuro.service;

import com.goeuro.model.Data;

import java.util.Collection;
import java.util.Set;

public interface DataLoader {
    <T extends Data> Collection<? extends Data> load(Set<String> params, Class<T> memberClass);
}
