package services;

import java.util.ArrayList;
import java.util.Map;

import model.Catalog;
import model.Plan;
import model.Service;
import model.ServiceBindResponse;

import org.springframework.stereotype.Component;

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

    
    public Catalog getCatalog() {
    	//	TODO: Implement
    	return catalog;
    }
	
    public Map<String,String> putServiceInstance(String id) {
    	//	TODO: Implement
    	// Optionally provide "dashboard_url" as key and a URL pointing to a monitor.
    	return null;
    }

    public void deleteServiceInstance(String id) {
    	//	TODO: Implement
    }

	public ServiceBindResponse createServiceBinding(String instanceId, String id) {
    	//	TODO: Implement
		return new ServiceBindResponse();
	}

	@Override
	public void deleteServiceBinding(String instanceId, String id) {
    	//	TODO: Implement
	}	
    
    
}
