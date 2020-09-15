package com.fr.Agenda.AgendaX.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User extends AbstractEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String pwd;
	
	@Column(nullable = false)
	protected String lastName;
	
	@Column(nullable = false)
	protected String firstName;
	
	@Column(nullable = false)
	protected String adress;
	
	@Column(nullable = false)
	protected String laboratory;
	
	@Column(nullable = false)
	protected String city;
	
	@Column(nullable = false)
	protected String phone;
	
	@Column(nullable = false)
	protected boolean admin;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_team")
	protected Team team;

}
