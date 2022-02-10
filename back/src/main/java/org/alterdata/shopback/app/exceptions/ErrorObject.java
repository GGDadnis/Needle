package org.alterdata.shopback.app.exceptions;

public class ErrorObject {
	
	private final String menssage;
    private final String field;
    private final Object parameter;
    
	public ErrorObject(String menssage, String field, Object parameter) {
		this.menssage = menssage;
		this.field = field;
		this.parameter = parameter;
	}
	
	public String getMessage() {
		return menssage;
	}
	public String getField() {
		return field;
	}
	public Object getParameter() {
		return parameter;
	}   
    
}
