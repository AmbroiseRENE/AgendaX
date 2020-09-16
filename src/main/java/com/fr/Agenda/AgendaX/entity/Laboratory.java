package com.fr.Agenda.AgendaX.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Laboratory extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String adress;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private int zipCode;
	
	@OneToMany
	@JoinColumn(name = "id_team")
	private List<Team> teams;
	
}
