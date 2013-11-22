package services;

import java.util.Map;

import model.Catalog;
import model.ServiceBindResponse;

public interface ServiceBroker {

    public Catalog getCatalog() ;
    
    public Map<String,String> putServiceInstance(String id);

    public void deleteServiceInstance(String id);
    
    public ServiceBindResponse createServiceBinding(String instanceId, String id);
    
    public void deleteServiceBinding(String instanceId, String id);
}
