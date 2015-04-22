package controllers;

import com.debortoliwines.openerp.api.FilterCollection;
import com.debortoliwines.openerp.api.ObjectAdapter;
import com.debortoliwines.openerp.api.Row;
import com.debortoliwines.openerp.api.RowCollection;
import com.debortoliwines.openerp.api.Session;
public class ClassOpenERP {
	
	
	
		   
	
	public static void insert(String name ) 
	{
		try{
			Session openSession = new Session("localhost",8069,"odoo","admin","odoo");
			openSession.startSession();
			System.out.println("Connection Successful");
			
		ObjectAdapter partnerAd = openSession.getObjectAdapter("crm.lead");
		//	ObjectAdapter partnerAd = openSession.getObjectAdapter("hr.department");
			//Row lead=partnerAd.getNewRow(new String[]{"name"});
			
			Row newLead = partnerAd.getNewRow(new String[]{"name", "email_from","partner_id"});
			newLead.put("name", "lead1");
			newLead.put("email_from", "biz123@gmail.com");
			newLead.put("partner_id_id","");
			//stock.put("product_id",product_id);
			//newpartner.put("last_name", last_name);
			//newpartner.put("middle_name", middle_name);
		//	lead.put("name","Weh Product");
			
			
			// "partner_id": fields.many2one("sale.order", 'Company', select=1),
			//System.out.println("Mobile :"+newpartner.get("last_name"));
			//System.out.println("Middle Name :"+newpartner.get("middle_name"));
			
			
		partnerAd.createObject(newLead);
			System.out.println("Lead is created : "+newLead.getID());
			
			//System.out.println("Product Id :"+stock.get("product_id"));
			System.out.println("Lead Name:"+newLead.get("name").toString());
			System.out.println("Email:"+newLead.get("email_from").toString());
			//System.out.println("Customer:"+newLead.get("partner_id"));
			}
		catch (Exception e) {
		    System.out.println("Error while reading data from server:\n\n" + e.getMessage());
		    
	}
	}
	
	public static void update(String name, String email_from )
	{
		try{
			Session openERPSession= new Session("localhost", 8069, "odoo", "admin", "odoo");
			openERPSession.startSession();
			ObjectAdapter partnerAd = openERPSession.getObjectAdapter("crm.lead");
			
			 FilterCollection filters=new FilterCollection();
				//filters.add("Customer", "=", true);
				filters.add("name", "=", "sub1");
				
				RowCollection partners= partnerAd.searchAndReadObject(filters ,new String[]{"name","email_from"});
				System.out.println("update size : "+partners.size());
				Row HRow=partners.get(0);
				HRow.put("email_from",email_from);
				
				boolean success = partnerAd.writeObject(HRow, true);

				if (success)
				 System.out.println("Update was successful");
			}
			catch (Exception e) {
			    System.out.println("Error while reading data from server:\n\n" + e.getMessage());
			   }		
	}
	
	public static void search(String email_from ){
		
		
		try{
			Session openERPSession= new Session("localhost", 8069, "odoo","admin","odoo");
			openERPSession.startSession();
			ObjectAdapter partnerAd = openERPSession.getObjectAdapter("crm.lead");
			
		    FilterCollection filters=new FilterCollection();
			//filters.add("Customer", "=", true);
			filters.add("email_from", "=", email_from);
			
			RowCollection partners= partnerAd.searchAndReadObject(filters ,new String[]{"email_from","name"});
	            
			
				for(Row row:partners){
				System.out.println("Email : "+row.get("email_from"));
				System.out.println("Name : "+row.get("name"));
		      }
		  }
		catch (Exception e) {
		    System.out.println("Error while reading data from server:\n\n" + e.getMessage());
		   }
	}

	
	public static void main(String []args) {

		 System.out.println("Connection success");

	// insert("account132");
 //  update("sub1","abc123@gmail.com");
		search("abc123@gmail.com");
//	
}}


