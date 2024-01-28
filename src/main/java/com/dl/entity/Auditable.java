package com.dl.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Audited
public class Auditable {
	
	@CreatedBy
	protected String createdBy= "vivek";
	
	
	@CreationTimestamp
	@Column(name ="createdAt",nullable =false,updatable=false)
	private LocalDateTime createdAt;
	
//	@LastModifiedBy
//	protected String lastModifiedBy;
	
//	@Temporal(TemporalType.TIMESTAMP)
//	@LastModifiedDate
	@UpdateTimestamp
	private LocalDateTime updatedAt;

}
