package org.openmrs.module.icchange.pharmacy.web.dwr;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import org.openmrs.api.context.Context;

import org.openmrs.module.openhmis.inventory.api.model.Item;
//import org.openmrs.module.icchange.pharmacy.inventory.Item;

public class DWRItem {

	private Integer itemId;
	private String itemName;
	private Integer quantity;
	
	public DWRItem () {}

	
	public DWRItem (Item item) {
		this.itemId = item.getId();
		this.itemName = item.getName();
		this.quantity = 0;
		//this.quantity = item.getQuantity();
	}
	
	public String toJasonRepresentation () {
		
		StringBuilder ret = new StringBuilder();		
		ret.append("{");

		for (Field f : this.getClass().getFields()) {			
			Object val = null;
			try { val = f.get(this); } catch (Exception e) { }
		
			if (val != null) {
				ret.append(f.getName());
				ret.append(":\"");		
				ret.append(val.toString());
				ret.append("\",");
			}
		}		
		
		if (ret.length() > 1)
			ret.setCharAt(ret.length() - 1, ' ');
					
		ret.append("}");
		return ret.toString();
	}
		
	@Override
	public String toString () {
		return this.toJasonRepresentation();
	}
	
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String name) {
		this.itemName = itemName;
	}

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}