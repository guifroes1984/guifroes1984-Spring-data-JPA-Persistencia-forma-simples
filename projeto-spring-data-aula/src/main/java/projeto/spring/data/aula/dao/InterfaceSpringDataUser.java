package projeto.spring.data.aula.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import projeto.spring.data.aula.model.UsuarioSpringData;

@Repository
public interface InterfaceSpringDataUser extends CrudRepository<UsuarioSpringData, Long> {
	
	/*Método que seleciona uma lista de usuários passando o que quer trazer por parâmetro*/
	@Transactional(readOnly = true)/*Somente leitura*/
	@Query(value = "select p from UsuarioSpringData p where p.nome like %?1%")
	public List<UsuarioSpringData> buscaPorNome(String nome);
	
	/*Método que traz o nome especifico, passando o nome por parametro*/
	@Transactional(readOnly = true)/*Somente leitura*/
	@Query(value = "select p from UsuarioSpringData p where p.nome = :paramnome")
	public UsuarioSpringData buscaPorNomeParam(@Param("paramnome") String paramnome);
	
	default <S extends UsuarioSpringData> S saveAtual(S entity) {
		// Processa qualquer coisa
		return save(entity);
	}
	
	@Modifying/*Esse método vai fazer modificações no banco de dados*/
	@Transactional/*Abre uma transação com o banco de dados, para poder excluir e comitar*/
	@Query(value = "delete from UsuarioSpringData u where u.nome = ?1")
	public void deletePorNome (String nome);
	
	@Modifying/*Esse método vai fazer modificações no banco de dados*/
	@Transactional/*Abre uma transação com o banco de dados, para poder editar e comitar*/
	@Query(value = "update UsuarioSpringData u set u.email = ?1 where u.nome = ?2")
	public void updateEmailPorNome(String email, String nome);
	
}
