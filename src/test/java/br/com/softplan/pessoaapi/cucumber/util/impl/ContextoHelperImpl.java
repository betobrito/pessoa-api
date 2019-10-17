package br.com.softplan.pessoaapi.cucumber.util.impl;

import br.com.softplan.pessoaapi.cucumber.util.ContextoHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Date;

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ContextoHelperImpl implements ContextoHelper {

    private JdbcTemplate jdbcTemplate;

    public ContextoHelperImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void limparBaseDados() {
        jdbcTemplate.update("delete from public.pessoa;");
        jdbcTemplate.update("alter sequence public.pessoa_id_seq restart;");
    }

    @Override
    public void inserirPessoa(String nome, String sexo, String cpf, String email, String nacionalidade, String naturalidade, Date dataNascimento) {
        jdbcTemplate.update("insert into public.pessoa (nome, sexo, cpf, email, nacionalidade, naturalidade, data_nascimento, data_criacao, data_atualizacao) values (?,?,?,?,?,?,?,current_timestamp, current_timestamp)", nome, sexo, cpf, email, nacionalidade, naturalidade, dataNascimento);
    }


}
