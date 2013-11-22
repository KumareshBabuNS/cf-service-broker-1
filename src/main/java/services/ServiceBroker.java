package services;

import java.util.Map;

import model.Catalog;
import model.ServiceBindResponse;
import model.ServiceRequest;

public interface ServiceBroker {

    public Catalog getCatalog() ;
    
    public Map<String,String> putServiceInstance(String id, ServiceRequest serviceRequest);

    public void deleteServiceInstance(String id, ServiceRequest serviceRequest);
    
    public ServiceBindResponse createServiceBinding(String instanceId, String id, ServiceRequest serviceRequest);
    
    public void deleteServiceBinding(String instanceId, String id, ServiceRequest serviceRequest);
}
