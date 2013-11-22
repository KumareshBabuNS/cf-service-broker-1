package model;

/**
 * All-purpose model object used to accept input via REST requests and 
 * make this available to the service broker.
 * 
 */
public class ServiceRequest {

	private String service_id;
	private String plan_id;
	private String organization_guid;
	private String space_guid;

	/**
	 * The ID of the service within the catalog. 
	 * During provisioning this ID is not strictly necessary, but some brokers 
	 * might find a use for this when available.  
	 * During binding, unbinding, and unprovisioning, this ID is not required, 
	 * but the broker could make use of this to locate the resource.
	 */
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	
	/**
	 * The ID of the plan within the service (from the catalog endpoint) that the user 
	 * would like provisioned. Because plans have identifiers unique to a broker, 
	 * this is enough information to determine what to provision.
	 * During binding, unbinding, and unprovisioning, this ID is not required, 
	 * but the broker could make use of this to locate the resource.
	 */
	public String getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}
	
	/**
	 * The Cloud Controller GUID of the organization under which 
	 * the service is to be provisioned. Although most brokers will not use this field, 
	 * it could be helpful in determining data placement or applying custom business rules.
	 * Not used during Binding.
	 */
	public String getOrganization_guid() {
		return organization_guid;
	}
	public void setOrganization_guid(String organization_guid) {
		this.organization_guid = organization_guid;
	}
	
	/**
	 * Similar to organization_guid, but for the space.
	 * Not used during Binding.
	 */
	public String getSpace_guid() {
		return space_guid;
	}
	public void setSpace_guid(String space_guid) {
		this.space_guid = space_guid;
	}
	
}
