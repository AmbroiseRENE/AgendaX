package com.fr.Agenda.AgendaX.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Experiment extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private LocalDateTime begginDate;
	
	@OneToMany
	@JoinColumn(name = "id_Manipulation")
	private List<Manipulation> Manipulations;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_project")
	private Project project;
	
	@OneToOne
	@JoinColumn(name = "id_plan")
	protected Plan plan;
}
