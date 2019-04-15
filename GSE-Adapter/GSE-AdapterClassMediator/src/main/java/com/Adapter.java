package com;


import org.apache.axiom.om.OMElement;
import org.apache.synapse.MessageContext;
import org.apache.synapse.commons.json.JsonUtil;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.json.JSONObject;

public class Adapter extends AbstractMediator { 

	public boolean mediate(MessageContext context) { 
		 try {
	            org.apache.axis2.context.MessageContext axis2MessageContext = ((Axis2MessageContext) context)
	                    .getAxis2MessageContext();
	            OMElement xmlResponse = context.getEnvelope().getBody().getFirstElement();
	            xmlResponse.build();
	            JSONObject jsonObject = new JSONObject(JsonUtil.toJsonString(xmlResponse).toString());

	            //setting the payload as the message payload
	            JsonUtil.getNewJsonPayload(axis2MessageContext, jsonObject.toString(), true, true);
	        } catch (Exception e) {
	            handleException("Error while converting the message", e, context);
	        }
	        return true;
	    }
}
