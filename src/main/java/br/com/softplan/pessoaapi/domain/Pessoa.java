package br.com.softplan.pessoaapi.domain;

import br.com.softplan.pessoaapi.domain.converter.DataNascimentoConverter;
import br.com.softplan.pessoaapi.domain.converter.DocumentoConverter;
import br.com.softplan.pessoaapi.domain.converter.EmailConverter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "email")
    @Convert(converter = EmailConverter.class)
    private Email email;

    @Convert(converter = DataNascimentoConverter.class)
    @Column(name = "data_nascimento")
    private DataNascimento dataNascimento;

    @Column(name = "naturalidade")
    private String naturalidade;

    @Column(name = "nacionalidade")
    private String nacionalidade;

    @Convert(converter = DocumentoConverter.class)
    @Column(name = "cpf", nullable = false, unique = true)
    private Documento cpf;

    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false, updatable=false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    public Pessoa() {
    }

    public Pessoa(String nome, String sexo, String email, Date dataNascimento, String naturalidade, String nacionalidade, String cpf) {
        this.nome = nome;
        this.sexo = sexo;
        this.email = Email.of(email);
        this.dataNascimento = DataNascimento.of(dataNascimento);
        this.naturalidade = naturalidade;
        this.nacionalidade = nacionalidade;
        this.cpf = Documento.of(cpf);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSexo() {
        return sexo;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public DataNascimento getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(DataNascimento dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public Documento getCpf() {
        return cpf;
    }

    public void setCpf(Documento cpf) {
        this.cpf = cpf;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public Pessoa id(Long id){
        this.id = id;
        return this;
    }

    public Pessoa nome(String nome){
        this.nome = nome;
        return this;
    }

    public Pessoa sexo(String sexo){
        this.sexo = sexo;
        return this;
    }

    public Pessoa email(Email email){
        this.email = email;
        return this;
    }

    public Pessoa naturalidade(String naturalidade){
        this.naturalidade = naturalidade;
        return this;
    }

    public Pessoa nacionalidade(String nacionalidade){
        this.nacionalidade = nacionalidade;
        return this;
    }

    public Pessoa dataNascimeto(DataNascimento dataNascimento){
        this.dataNascimento = dataNascimento;
        return this;
    }

    public Pessoa cpf(Documento documento){
        this.cpf = documento;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(cpf, pessoa.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sexo='" + sexo + '\'' +
                ", email='" + email + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", naturalidade='" + naturalidade + '\'' +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }
}
