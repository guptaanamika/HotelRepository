package com.booking.repository.entity;

import java.util.Date;

import lombok.Data;

/**
 * 
 * This class is created to be used commonly across all entities to maintain
 * detailed information about all audit made by any system or developer along
 * with the timestamp of edit.
 * 
 * @author anamika.gupta created_on : 16-Feb-2017
 **/
@Data
public class EditInfo {

	private Date createdOn;

	private String createdBy;

	private Date updatedOn;

	private String updatedBy;
}
