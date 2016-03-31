package com.goeuro.service;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.goeuro.model.Data;
import com.goeuro.utils.ApplicationException;
import com.goeuro.utils.RestApiPath;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class RestAPILoader implements DataLoader {

    private static final String REST_URL = "http://api.goeuro.com/api/v2/%TYPE%";

    @Override
    public <T extends Data> Collection<? extends T> load(Set<String> params, Class<T> memberClass) {
        final StringBuilder url = new StringBuilder(REST_URL.replaceAll("%TYPE%", getPath(memberClass)));
        params.stream().forEach(param -> url.append('/').append(param));

        List<Object> providers = new ArrayList<>();
        providers.add( new JacksonJaxbJsonProvider() );
        WebClient client = WebClient.create(url.toString(), providers);
        client = client.accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON_TYPE);

        return client.getCollection(memberClass);
    }

    private <T extends Data> String getPath(Class<T> memberClass) {
        RestApiPath annotation = memberClass.getAnnotation(RestApiPath.class);
        if (annotation == null) {
            throw new ApplicationException("Rest Api path annotation was not found for class: " + memberClass);
        }
        return annotation.value();
    }
}
