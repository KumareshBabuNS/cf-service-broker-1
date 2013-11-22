package model;

import java.util.List;

public class Service {

	private String id; 		//	An identifier, unique within the broker, used to correlate this service in future requests to the catalog.
	private String name;	//	The CLI-friendly name of the service that will appear in the catalog.;	
	private String description;	//	A short description of the service that will appear in the catalog.;	
	private boolean bindable;	//	Whether the service can be bound to applications. Defaults to true.;
	private List<String> tag;	//	A list of permissions that the user would have to give the service, if they provision it. The only permission currently supported is syslog_drain.
	private Object metadata;	//	A list of metadata for a service offering. For more information, see Service Metadata.
	private List<String> requires;	//	A list of permissions that the user would have to give the service, if they provision it. The only permission currently supported is syslog_drain.
	private List<Plan> plans;		//	A list of plans for this service, schema defined below:
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isBindable() {
		return bindable;
	}
	public void setBindable(boolean bindable) {
		this.bindable = bindable;
	}
	public List<String> getTag() {
		return tag;
	}
	public void setTag(List<String> tag) {
		this.tag = tag;
	}
	public Object getMetadata() {
		return metadata;
	}
	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}
	public List<String> getRequires() {
		return requires;
	}
	public void setRequires(List<String> requires) {
		this.requires = requires;
	}
	public List<Plan> getPlans() {
		return plans;
	}
	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}


}
