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
	
	@Column(nullable = true)
	protected String adress;
	
	@Column(nullable = true)
	protected int zipCode;
	
	@Column(nullable = false)
	protected String laboratory;
	
	@Column(nullable = true)
	protected String city;
	
	@Column(nullable = true)
	protected String phone;
	
	@Column(nullable = false)
	protected boolean admin;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_team")
	protected Team team;

	public User(String email, String pwd, String lastName, String firstName, String adress, int zipCode,
			String laboratory, String city, String phone, boolean admin) {
		super();
		this.email = email;
		this.pwd = pwd;
		this.lastName = lastName;
		this.firstName = firstName;
		this.adress = adress;
		this.zipCode = zipCode;
		this.laboratory = laboratory;
		this.city = city;
		this.phone = phone;
		this.admin = admin;
	}

	public User(Long id, String email, String pwd, String lastName, String firstName, String adress, int zipCode,
			String laboratory, String city, String phone, boolean admin) {
		super();
		this.id = id;
		this.email = email;
		this.pwd = pwd;
		this.lastName = lastName;
		this.firstName = firstName;
		this.adress = adress;
		this.zipCode = zipCode;
		this.laboratory = laboratory;
		this.city = city;
		this.phone = phone;
		this.admin = admin;
	}
	
}
