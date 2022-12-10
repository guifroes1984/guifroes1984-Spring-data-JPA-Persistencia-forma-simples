package projeto.spring.data.aula;

import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.aula.dao.InterfaceSpringDataUser;
import projeto.spring.data.aula.dao.InterfaceTelefone;
import projeto.spring.data.aula.model.Telefone;
import projeto.spring.data.aula.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring-config.xml" })
public class AppSpringDataTest {

	@Autowired
	private InterfaceSpringDataUser interfaceSpringDataUser;
	
	@Autowired
	private InterfaceTelefone interfaceTelefone;

	@Test
	@Ignore
	public void testeInsert() {

		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();

		usuarioSpringData.setEmail("teste@gmail.com");
		usuarioSpringData.setIdade(20);
		usuarioSpringData.setLogin("teste 321");
		usuarioSpringData.setSenha("321");
		usuarioSpringData.setNome("Teste teste");

		interfaceSpringDataUser.save(usuarioSpringData);

		System.out.println("Ususários cadastrados -> " + interfaceSpringDataUser.count());

	}

	@Test
	public void testeConsulta() {

		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(1L);

		System.out.println(usuarioSpringData.get().getIdade());
		System.out.println(usuarioSpringData.get().getEmail());
		System.out.println(usuarioSpringData.get().getLogin());
		System.out.println(usuarioSpringData.get().getNome());
		System.out.println(usuarioSpringData.get().getSenha());
		System.out.println(usuarioSpringData.get().getId());
		
		for (Telefone telefone : usuarioSpringData.get().getTelefones()) {
			System.out.println(telefone.getNumero());
			System.out.println(telefone.getTipo());
			System.out.println(telefone.getId());
			System.out.println(telefone.getUsuarioSpringData().getNome());
			System.out.println("---------------------------------------");
		}

	}
	
	@Test
	@Ignore
	public void testeConsultaTodos() {
		
		Iterable<UsuarioSpringData> lista = interfaceSpringDataUser.findAll();
		
		for (UsuarioSpringData usuarioSpringData : lista) {
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getId());
			System.out.println("-----------------------------------------------");
			
		}
		
	}
	
	@Test
	@Ignore
	public void testeUpdater() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(2L);
		
		UsuarioSpringData data = usuarioSpringData.get();
		
		data.setNome("Henrique");
		data.setIdade(25);
		
		interfaceSpringDataUser.save(data);
		
	}
	
	@Test
	@Ignore
	public void testeDelete() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(4L);
		
		interfaceSpringDataUser.delete(usuarioSpringData.get());
		
	}
	
	@Test
	public void testeConsultaNome() {
		
		List<UsuarioSpringData> list = interfaceSpringDataUser.buscaPorNome("Froes");
		
		for (UsuarioSpringData usuarioSpringData : list) {
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getId());
			System.out.println("-----------------------------------------------");
			
		}
		
	}
	
	
	@Test
	@Ignore
	public void testeConsultaNomeParam() {
		
		UsuarioSpringData usuarioSpringData = interfaceSpringDataUser.buscaPorNomeParam("Henrique");
			
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getId());
			System.out.println("-----------------------------------------------");
			
		
	}
	
	@Test
	@Ignore
	public void testeDeletePorNome() {
		
		interfaceSpringDataUser.deletePorNome("Henrique");
		
	}
	
	@Test
	@Ignore
	public void testeUpdateEmailPorNome() {
		
		interfaceSpringDataUser.updateEmailPorNome("testeupdate@gmail.com", "Guilherme Froes");
		
	}
	
	@Test
	@Ignore
	public void testeTelefone() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(3L);
		
		Telefone telefone = new Telefone();
		telefone.setTipo("Celular");
		telefone.setNumero("988224912");
		telefone.setUsuarioSpringData(usuarioSpringData.get());
		
		interfaceTelefone.save(telefone);
		
	}

}
