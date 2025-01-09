package com.tcc.dreams.newdreams.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "pacotes")
public class Pacote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-Incremento
	private Long id;
	private String nomePacote;
	private Float preco;
	private String duracao;
	private String inclusos;
	private String tituloCard;
	private String descricaoCard;
	private String descricaoLugar;
	private String statusPacote;
	
	@Column(columnDefinition = "VARBINARY(max)")
	private byte[] foto; // Foto associada ao pacote.
	
	@Column(columnDefinition = "VARBINARY(max)")
	private byte[] fotoCard1; // Foto associada ao pacote.
	@Column(columnDefinition = "VARBINARY(max)")
	private byte[] fotoCard2; // Foto associada ao pacote.
	@Column(columnDefinition = "VARBINARY(max)")
	private byte[] fotoCard3; // Foto associada ao pacote.
	@Column(columnDefinition = "VARBINARY(max)")
	private byte[] fotoCard4; // Foto associada ao pacote.

	// Indica que o campo não deve ser persistido no banco de dados.
	@Transient
	// Representação em string da foto (usada para exibição na interface).
	private String fotoString;
	
	@Transient
	private String fotoCard1String;
	@Transient
	private String fotoCard2String;
	@Transient
	private String fotoCard3String;
	@Transient
	private String fotoCard4String;
	
	@OneToMany(mappedBy = "pacote")
	private List<Reserva> reservas = new ArrayList<Reserva>();

	public Pacote() {
	
	}

	public Pacote(String nomePacote) {

		this.nomePacote = nomePacote;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomePacote() {
		return nomePacote;
	}

	public void setNomePacote(String nomePacote) {
		this.nomePacote = nomePacote;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public String getInclusos() {
		return inclusos;
	}

	public void setInclusos(String inclusos) {
		this.inclusos = inclusos;
	}

	public String getTituloCard() {
		return tituloCard;
	}

	public void setTituloCard(String tituloCard) {
		this.tituloCard = tituloCard;
	}

	public String getDescricaoCard() {
		return descricaoCard;
	}

	public void setDescricaoCard(String descricaoCard) {
		this.descricaoCard = descricaoCard;
	}

	public String getDescricaoLugar() {
		return descricaoLugar;
	}

	public void setDescricaoLugar(String descricaoLugar) {
		this.descricaoLugar = descricaoLugar;
	}

	public String getStatusPacote() {
		return statusPacote;
	}

	public void setStatusPacote(String statusPacote) {
		this.statusPacote = statusPacote;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public byte[] getFotoCard1() {
		return fotoCard1;
	}

	public void setFotoCard1(byte[] fotoCard1) {
		this.fotoCard1 = fotoCard1;
	}

	public byte[] getFotoCard2() {
		return fotoCard2;
	}

	public void setFotoCard2(byte[] fotoCard2) {
		this.fotoCard2 = fotoCard2;
	}

	public byte[] getFotoCard3() {
		return fotoCard3;
	}

	public void setFotoCard3(byte[] fotoCard3) {
		this.fotoCard3 = fotoCard3;
	}

	public byte[] getFotoCard4() {
		return fotoCard4;
	}

	public void setFotoCard4(byte[] fotoCard4) {
		this.fotoCard4 = fotoCard4;
	}

	public String getFotoString() {
		return fotoString;
	}

	public void setFotoString(String fotoString) {
		this.fotoString = fotoString;
	}

	public String getFotoCard1String() {
		return fotoCard1String;
	}

	public void setFotoCard1String(String fotoCard1String) {
		this.fotoCard1String = fotoCard1String;
	}

	public String getFotoCard2String() {
		return fotoCard2String;
	}

	public void setFotoCard2String(String fotoCard2String) {
		this.fotoCard2String = fotoCard2String;
	}

	public String getFotoCard3String() {
		return fotoCard3String;
	}

	public void setFotoCard3String(String fotoCard3String) {
		this.fotoCard3String = fotoCard3String;
	}

	public String getFotoCard4String() {
		return fotoCard4String;
	}

	public void setFotoCard4String(String fotoCard4String) {
		this.fotoCard4String = fotoCard4String;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pacote other = (Pacote) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
