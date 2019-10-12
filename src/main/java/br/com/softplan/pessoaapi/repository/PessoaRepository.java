package br.com.softplan.pessoaapi.repository;

import br.com.softplan.pessoaapi.domain.Documento;
import br.com.softplan.pessoaapi.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Pessoa findByCpf(Documento cpf);
}
