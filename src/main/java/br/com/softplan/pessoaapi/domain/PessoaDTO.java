package br.com.softplan.pessoaapi.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PessoaDTO {

    private String nome;
    private String sexo;
    private String email;
    private LocalDate dataNascimento;
    private String naturalidade;
    private String nacionalidade;
    private String cpf;

    public PessoaDTO(Pessoa pessoa) {
        this.nome = pessoa.getNome();
        this.sexo = pessoa.getSexo();
        this.cpf = pessoa.getCpf() != null? pessoa.getCpf().toString():null;
        this.email = pessoa.getEmail();
        this.dataNascimento = pessoa.getDataNascimento();
        this.nacionalidade = pessoa.getNacionalidade();
        this.naturalidade = pessoa.getNaturalidade();
    }

    public String getNome() {
        return nome;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public String getCpf() {
        return cpf;
    }

    public Pessoa transformToEntity(){
        return new Pessoa(this.nome, this.sexo, this.email, this.dataNascimento, this.naturalidade, this.nacionalidade, this.cpf);
    }

    public static PessoaDTO of(Pessoa pessoa){
        return new PessoaDTO(pessoa);
    }

    public static List<PessoaDTO> convert(List<Pessoa> places){
        return places.stream().map(PessoaDTO::new).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PessoaDTO pessoaDTO = (PessoaDTO) o;
        return Objects.equals(cpf, pessoaDTO.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
