package service;

import com.goeuro.model.Data;
import com.goeuro.model.Location;
import com.goeuro.service.DataLoader;
import com.goeuro.service.RestAPILoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TestsRestAPILoader {

    private DataLoader loader = new RestAPILoader();

    @Test
    public void testSuccessGetData() throws Exception {
        Set<String> params = new HashSet<>();
        params.add("Berlin");
        Collection<? extends Data> data = loader.load(params, Location.class);
        Assert.assertFalse(data.isEmpty());
    }

    @Test
    public void testNoDataReturn() throws Exception {
        Set<String> params = new HashSet<>();
        params.add(String.valueOf(System.currentTimeMillis()));
        Collection<? extends Data> data = loader.load(params, Location.class);
        Assert.assertTrue(data.isEmpty());
    }
}
