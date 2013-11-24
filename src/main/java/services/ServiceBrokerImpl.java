package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Catalog;
import model.Plan;
import model.Service;
import model.ServiceBindResponse;
import model.ServiceRequest;

import org.springframework.stereotype.Component;

/**
 *	TODO: The implementation of this service broker is something that you will almost
 *	certainly want to replace with your own logic.  The implementation should be completely
 *	based on what your service should actually do.  The example service "random number" is
 *	intentionally trivial and has no real needs for provisioning/unprovisioning, binding/unbinding,
 *	so the code you see here only illustrates the absolute minimum implementation to support
 *	the Cloud Foundry API. 
 */
@Component
public class ServiceBrokerImpl implements ServiceBroker {

    private static final Catalog catalog = new Catalog();
    static {
    	Plan plan = new Plan();
    	plan.setId("plan-guid-here");
    	plan.setName("basic");
    	plan.setDescription("Basic, no-frills plan");
    	
    	Service service = new Service();
    	service.setId("service-guid-here");
    	service.setName("random_number");
    	service.setDescription("Produces a random number");
    	service.setBindable(true);
    	
    	ArrayList<Plan> plans = new ArrayList<Plan>();
    	plans.add(plan);
    	service.setPlans(plans);
    	
    	ArrayList<Service> services = new ArrayList<Service>();
    	services.add(service);
    	
    	catalog.setServices(services);
    }

    private Map<String, ServiceRequest> serviceInstanceMap = new HashMap<String, ServiceRequest>();
    
    public Catalog getCatalog() {
    	//	TODO: A real implementation should gather Service and Plan data
    	//	based on something more dynamic than the hard-coded, make-believe
    	//	values you see here.
    	return catalog;
    }
	
    
    
    public Map<String,String> putServiceInstance(String id, ServiceRequest serviceRequest) {

    	//	TODO: A real service broker would probably do something much more sophisticated
    	//	here.  For example a DBMS service would create a database, or an email service
    	//	would create an account.  Our example 'random number' service needs nothing so 
    	//	advanced, so we simply keep track of instances on an internal Map.
    	if(serviceInstanceMap.containsKey(id)) {
    		throw new AlreadyExistsException();
    	}
    	serviceInstanceMap.put(id, serviceRequest);
    	
    	// TODO Optionally provide "dashboard_url" as key and a URL pointing to a monitor.
    	return null;
    }

    public void deleteServiceInstance(String id, String serviceId, String planId ) {

    	//	TODO: A real service broker would do something much more sophisticated here.
    	//	for example a DBMS service broker would delete the database created earlier,
    	//	an email service would delete the account that was setup.  Our simple example
    	//	will just remove the item from the Map:
    	if(!serviceInstanceMap.containsKey(id)) {
    		throw new NotFoundException();
    	}
    	serviceInstanceMap.remove(id);
    }

	public ServiceBindResponse createServiceBinding(String instanceId, String id, ServiceRequest serviceRequest) {
    	//	TODO: Implement
		return new ServiceBindResponse();
	}

	@Override
	public void deleteServiceBinding(String instanceId, String id, ServiceRequest serviceRequest) {
    	//	TODO: Implement
	}	
    
    
}
