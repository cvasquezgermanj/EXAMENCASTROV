package com.castrovasquez.denunciaservice.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="denuncias")
@EntityListeners(AuditingEntityListener.class)


public class Denuncias {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="dni", nullable = false, length=8)
	private String dni;
	
	@Column(name="fecha",nullable= false)
	private Date fecha;
	
	@Column(name="direccion", length=3, nullable= false)
	private String direccion;
	
	@Column(name="titulo", length=200, nullable= false)
	private String titulo;
	
	@Column(name="descripcion", length=255, nullable= false)
	private String descripcion;
	
	@Column(name="created_at", nullable= false, updatable= false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column(name="update_at")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updateAt;
}
