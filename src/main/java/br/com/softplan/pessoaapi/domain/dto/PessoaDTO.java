package br.com.softplan.pessoaapi.domain.dto;

import br.com.softplan.pessoaapi.domain.Pessoa;
import br.com.softplan.pessoaapi.util.validation.Create;
import br.com.softplan.pessoaapi.util.validation.Edit;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PessoaDTO {

    @NotBlank(message = "Nome é obrigatório, deve ser informado.", groups = {Create.class, Edit.class})
    private String nome;
    private String sexo;
    private String email;
    @NotNull(message = "Data de nascimento é obrigatória, deve ser informada.", groups = {Create.class, Edit.class})
    private Date dataNascimento;
    private String naturalidade;
    private String nacionalidade;
    @NotBlank(message = "CPF é obrigatório, deve ser informado.", groups = Create.class)
    private String cpf;

    public PessoaDTO() {
    }

    public PessoaDTO(Pessoa pessoa) {
        this.nome = pessoa.getNome();
        this.sexo = pessoa.getSexo();
        this.cpf = pessoa.getCpf() != null? pessoa.getCpf().toString():null;
        this.email = pessoa.getEmail() != null? pessoa.getEmail().toString():null;
        this.dataNascimento = pessoa.getDataNascimento() != null? pessoa.getDataNascimento().getDataNascimento():null;
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

    public Date getDataNascimento() {
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
