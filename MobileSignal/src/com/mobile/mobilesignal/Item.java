package com.mobile.mobilesignal;

public class Item {

	private String id_dato,valor_dato;
    public Item() {
        super();
    }
 
    public Item(String id_dato, String valor_dato) {
    	super();
    	this.id_dato=id_dato;
    	this.valor_dato=valor_dato;
    }
 
    public String getIdDato() {
        return id_dato;
    }
 
    public String getValorDato() {
        return valor_dato;
    }
     
}
