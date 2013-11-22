package controllers;

import java.util.Map;

import model.Catalog;
import model.ServiceBindResponse;
import model.ServiceRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import services.NotFoundException;
import services.ServiceBroker;

/**
 *	Implementation of the REST layer for a V2 Cloud Foundry Service Broker.  See
 *	http://docs.cloudfoundry.com/docs/running/architecture/services/writing-service.html
 *	for the specifications behind the implementation you see here. 
 * 
 * @author 	Ken Krueger
 * @since	November 2013
 */
@Controller
@RequestMapping("/v2")
public class RestController {

    @Autowired
    private ServiceBroker serviceBroker;
    
    /**
     * Expose Catalog.
     */
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
    		@RequestBody ServiceRequest serviceRequest ) {
    	return serviceBroker.putServiceInstance(id, serviceRequest);
    }
    
    
    /**
     * Binding.
     */
    @RequestMapping(value="/service_instances/{instance_id}/service_bindings/{id}", method=RequestMethod.PUT)
    public @ResponseBody ServiceBindResponse createBinding(
    	@PathVariable String instanceId, 
    	@PathVariable String id,
		@RequestBody ServiceRequest serviceRequest ) {

    	return serviceBroker.createServiceBinding(instanceId, id, serviceRequest);
    }
    
    
    /**
     * Unbinding.
     */
    @RequestMapping(value="/service_instances/{instance_id}/service_bindings/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBinding(
    		@PathVariable String instanceId, 
    		@PathVariable String id,  
    		@RequestBody ServiceRequest serviceRequest ) {
 
    	// If not found, throw a NotFoundException, which is mapped to 404.
    	serviceBroker.deleteServiceBinding(instanceId, id, serviceRequest);
    }    

    /**
     * Unprovisioning.
     */
    @RequestMapping(value="/service_instances/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServiceInstance(@PathVariable String id,  
    		@RequestBody ServiceRequest serviceRequest) {
    	serviceBroker.deleteServiceInstance(id, serviceRequest);
    }
    
    /**
     * Exception handling / mapping.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public void handleNotFound() {}
    
}