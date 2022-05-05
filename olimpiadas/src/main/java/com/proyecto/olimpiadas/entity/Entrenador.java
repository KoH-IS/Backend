package com.proyecto.olimpiadas.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "entrenador")
public class Entrenador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id_entrenador")
	@Column(name = "id_entrenador")
	private Integer id_entrenador;
	
	@JsonProperty("nombre")
	@Column(name = "nombre")
	@NotNull(message = "El nombre del entrenador es requerido")
	@Size(min = 2, message = "El nombre del entrenador debe tener al menos 2 letras")
	private String nombre;
	
	@JsonProperty("apellido_paterno")
	@Column(name = "apellido_paterno")
	@NotNull(message = "El apellido paterno del entrenador es requerido")
	@Size(min = 2, message = "El apellido paterno del entrenador debe tener al menos 2 letras")
	private String apellido_paterno;
	
	@JsonProperty("apellido_materno")
	@Column(name = "apellido_materno")
	@Size(min = 2, message = "El apellido materno del entrenador debe tener al menos 2 letras")
	private String apellido_materno;
	
	@JsonIgnore
	@Column(name = "status")
	@Min(value = 0, message = "status must be 0 or 1")
	@Max(value = 1, message = "status must be 0 or 1")
	private Integer status;
	
	@JsonProperty("disciplina_id")
	@Column(name = "disciplina_id")
	@NotNull(message = "El id de la disciplina es requerido")
	private Integer disciplina_id;
	
	@Transient
	@JsonProperty("disciplina")
	private Disciplina disciplina;
	
	@OneToMany(mappedBy = "entrenador", cascade = CascadeType.ALL)
	private Set<Competidor> competidores;
	
	@Email
	private String email;
	
	private String password;

	public Integer getId_entrenador() {
		return id_entrenador;
	}

	public void setId_entrenador(Integer id_entrenador) {
		this.id_entrenador = id_entrenador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido_paterno() {
		return apellido_paterno;
	}

	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}

	public String getApellido_materno() {
		return apellido_materno;
	}

	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDisciplina_id() {
		return disciplina_id;
	}

	public void setDisciplina_id(Integer disciplina_id) {
		this.disciplina_id = disciplina_id;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Set<Competidor> getCompetidores() {
		return competidores;
	}

	public void setCompetidores(Set<Competidor> competidores) {
		this.competidores = competidores;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}