package controllers;

import java.util.Map;

import model.Catalog;
import model.ServiceBindResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import services.NotFoundException;
import services.ServiceBroker;

@Controller
@RequestMapping("/v2")
public class RestController {

    @Autowired
    private ServiceBroker serviceBroker;
    
    @RequestMapping(value="/catalog", method=RequestMethod.GET)
    public @ResponseBody Catalog getCatalog() {
        return serviceBroker.getCatalog();
    }
    
    
    /**
     * Provisioning.
     */
    @RequestMapping(value="/service_instances/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Map<String,String> putServiceInstance(
    		@PathVariable String id,
    		@RequestParam("service_id") String serviceId,
    		@RequestParam("plan_id") String planId,
    		@RequestParam("organization_guid") String organizationGuid,
    		@RequestParam("space_guid") String spaceGuid ) {
    	return serviceBroker.putServiceInstance(id);
    }
    

    
    
    /**
     * Binding.
     */
    @RequestMapping(value="/service_instances/{instance_id}/service_bindings/{id}", method=RequestMethod.PUT)
    public @ResponseBody ServiceBindResponse createBinding(
    	@PathVariable String instanceId, 
    	@PathVariable String id,
		@RequestParam("service_id") String serviceId,
		@RequestParam("plan_id") String planId	) {

    	return serviceBroker.createServiceBinding(instanceId, id);
    }
    
    /**
     * Unbinding.
     */
    @RequestMapping(value="/service_instances/{instance_id}/service_bindings/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBinding(
    		@PathVariable String instanceId, 
    		@PathVariable String id,  
    		@RequestParam String serviceId, 
    		@RequestParam String planId) {
 
    	// If not found, throw a NotFoundException, which is mapped to 404.
    	serviceBroker.deleteServiceBinding(instanceId, id);
    }    

    /**
     * Unprovisioning.
     */
    @RequestMapping(value="/service_instances/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServiceInstance(@PathVariable String id) {
    	serviceBroker.deleteServiceInstance(id);
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public void handleNotFound() {}
    
}