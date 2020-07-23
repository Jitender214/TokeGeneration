package com.cepmops.pdp;

import com.cisco.epm.xacml.XacmlConstant;
import com.cisco.epm.xacml.XacmlResponse;

import net.securent.util.pep.AuthorizationManagerFactory;
import net.securent.util.pep.IAuthorizationManager;
import com.cisco.epm.xacml.XACMLGenerator;

public class GetAllowedUsersForResource {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String methodName = "getPermissibleResourcesForUser";
		String subject = "analli";
		String resourceFQN = "Cisco Service:Technical Services:Bot Unification Framework (BUFF)";
		String context="Global Context:Global Context";
		String[] roleBundles = new String[1];
		roleBundles[0] = "Default";
		IAuthorizationManager authMgr;
		try {
			authMgr = AuthorizationManagerFactory.getInstance().getAuthorizationManager();
			System.out.println("invoking authmgr");
			String[] responseObj = authMgr.getPermissibleResourcesForUser(subject,resourceFQN);
			System.out.println(responseObj);
		}
		catch(Exception e) {
			
		}
	}

}
