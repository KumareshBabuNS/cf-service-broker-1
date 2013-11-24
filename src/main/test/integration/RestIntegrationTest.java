package integration;

import model.ServiceRequest;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import config.Application;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *	Out-of-container test of the entire service broker interfaces: catalog, 
 *	provision, bind, unbinding, unprovisioning.  Based on specs located
 *	here:  http://docs.cloudfoundry.com/docs/running/architecture/services/writing-service.html
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration( classes= {Application.class})
public class RestIntegrationTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
          mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testGetCatalog() throws Exception {
        mockMvc.perform(
        	get("/v2/catalog"))
        	.andExpect(status().isOk())
        	.andExpect(content().contentType("application/json;charset=UTF-8"))
        	.andExpect(jsonPath("$services[0].plans[0].name").value("basic"))
        ;
    }

    @Test
    public void duplicateProvisioningNotAllowed() throws Exception {

    	// First request should be OK:
    	mockMvc.perform(
    		put("/v2/service_instances/1234")
    		.content("{\"service_id\":\"service-guid-here\"}")
    		.contentType(MediaType.APPLICATION_JSON)
    		)
    		.andExpect(status().isCreated());

    	//	Second should be detected as a conflict:
    	mockMvc.perform(
        		put("/v2/service_instances/1234")
    		.content("{\"service_id\":\"service-guid-here\"}")
        		.contentType(MediaType.APPLICATION_JSON)
        		)
        		.andExpect(status().isConflict());
    
    }

    @Test
    public void provisionThenUnProvision() throws Exception {

    	// Provision a service instance:
    	mockMvc.perform(
    		put("/v2/service_instances/567")
    		.content("{\"service_id\":\"service-guid-here\"}")
    		.contentType(MediaType.APPLICATION_JSON)
    		)
    		.andExpect(status().isCreated());
    	
    	// Then a request to unProvision should be successful:
    	mockMvc.perform(
    		delete("/v2/service_instances/567")
    		)
    		.andExpect(status().isOk())
    		.andExpect(content().string("{}"));

    }
    
    @Test
    public void unProvisionNotFound() throws Exception {

    	// An attempt to unprovision something which has not been provisioned
    	//	should result in a 404, and interestingly by spec, an empty json object:
    	mockMvc.perform(
    		delete("/v2/service_instances/should-throw-404")
    		)
    		.andExpect(status().isNotFound())
    		.andExpect(content().string("{}"));

    }
    
    @Test
    @Ignore
    public void quick() {
    	RestTemplate t = new RestTemplate();
    	ServiceRequest r = new ServiceRequest();
    	r.setService_id("service-guid-here");
    	t.getInterceptors();
    	t.getMessageConverters();
    	t.put("http://localhost:8082/v2/service_instances/1234", r );
    	
    }

    @Test
    @Ignore
    public void quickdelete() {
    	RestTemplate t = new RestTemplate();
    	ServiceRequest r = new ServiceRequest();
    	r.setService_id("service-guid-here");
    	t.getInterceptors();
    	t.getMessageConverters();
    	t.delete("http://localhost:8082/v2/service_instances/1234",r );
    	
    }    
}
