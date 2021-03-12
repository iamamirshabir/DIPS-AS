package com.pioneer.dips.symptom.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Symptom {

	private @Id @GeneratedValue long SYMPTOM_ID;
	private String SYMPTOM_NAME;
	private long SYMPTOM_CATEGORY_ID;
	
	public Symptom() {
		
	}
	
	public Symptom(String sYMPTOM_NAME, long sYMPTOM_CATEGORY_ID) {
		super();
		SYMPTOM_NAME = sYMPTOM_NAME;
		SYMPTOM_CATEGORY_ID = sYMPTOM_CATEGORY_ID;
	}
	public long getSYMPTOM_ID() {
		return SYMPTOM_ID;
	}
	public void setSYMPTOM_ID(long sYMPTOM_ID) {
		SYMPTOM_ID = sYMPTOM_ID;
	}
	public String getSYMPTOM_NAME() {
		return SYMPTOM_NAME;
	}
	public void setSYMPTOM_NAME(String sYMPTOM_NAME) {
		SYMPTOM_NAME = sYMPTOM_NAME;
	}
	public long getSYMPTOM_CATEGORY_ID() {
		return SYMPTOM_CATEGORY_ID;
	}
	public void setSYMPTOM_CATEGORY_ID(long sYMPTOM_CATEGORY_ID) {
		SYMPTOM_CATEGORY_ID = sYMPTOM_CATEGORY_ID;
	}
	  @Override
	  public int hashCode() {
	    return Objects.hash(this.SYMPTOM_ID, this.SYMPTOM_NAME, this.SYMPTOM_CATEGORY_ID);
	  }

	  @Override
	  public String toString() {
	    return "SYMPTOM{" + "id=" + this.SYMPTOM_ID + ", name='" + this.SYMPTOM_NAME + '\'' + ", category_id='" + this.SYMPTOM_CATEGORY_ID + '\'' + '}';
	  }
	
	
}
