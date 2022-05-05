package com.proyecto.olimpiadas.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "competidor")
public class Competidor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id_competidor")
	@Column(name = "id_competidor")
	private Integer id_competidor;
	
	@JsonProperty("nombre")
	@Column(name = "nombre")
	@NotNull(message = "El nombre del competidor es requerido")
	@Size(min = 2, message = "El nombre del competidor debe tener al menos 2 letras")
	private String nombre;
	
	@JsonProperty("apellido_paterno")
	@Column(name = "apellido_paterno")
	@NotNull(message = "El apellido paterno del competidor es requerido")
	@Size(min = 2, message = "El apellido paterno del competidor debe tener al menos 2 letras")
	private String apellido_paterno;
	
	@JsonProperty("apellido_materno")
	@Column(name = "apellido_materno")
	@Size(min = 2, message = "El apellido materno del competidor debe tener al menos 2 letras")
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
	
	@Email
	private String email;
	
	private String password;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_entrenador", referencedColumnName = "id_entrenador")
	private Entrenador entrenador;
	
	@JsonProperty("entrenador_id")
	@Column(name = "entrenador_id")
	@NotNull(message = "El id del entrenador es requerido")
	private Integer entrenador_id;

	public Integer getId_competidor() {
		return id_competidor;
	}

	public void setId_competidor(Integer id_competidor) {
		this.id_competidor = id_competidor;
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

	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

	public Integer getEntrenador_id() {
		return entrenador_id;
	}

	public void setEntrenador_id(Integer entrenador_id) {
		this.entrenador_id = entrenador_id;
	}
}