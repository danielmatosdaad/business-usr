package br.com.app.smart.business.service;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import br.com.app.smart.business.dao.interfaces.IServicoRemoteDAO;
import br.com.app.smart.business.databuilder.SenhaBuilder;
import br.com.app.smart.business.databuilder.SenhaBuilder.TipoSenhaBuilder;
import br.com.app.smart.business.exception.InfraEstruturaException;
import br.com.app.smart.business.exception.NegocioException;
import br.com.app.smart.business.usuario.dto.SenhaDTO;
import br.com.app.smart.business.usuario.dto.StatusSenhaDTO;
import br.com.app.smart.business.util.ClassUtil;
import br.com.app.smart.business.util.PackageUtil;

/**
 * @author daniel-matos
 *
 */
@RunWith(Arquillian.class)
public class SenhaServiceImpTest {

	@Deployment
	public static Archive<?> createTestArchive() {

		PomEquippedResolveStage pom = Maven.resolver().loadPomFromFile("pom.xml");

		File[] libs = pom.resolve("br.com.app.smart.business:app-smart-business-common:0.0.1-SNAPSHOT")
				.withClassPathResolution(true).withTransitivity().asFile();

		File[] libs2 = pom.resolve("org.modelmapper:modelmapper:0.7.5").withClassPathResolution(true).withTransitivity()
				.asFile();

		WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
				.addAsLibraries(libs)
				.addAsLibraries(libs2)
				.addClasses(ClassUtil.getClassInfraDAO())
				.addPackage(PackageUtil.DATA_BUILDER.getPackageName())
				.addPackage(PackageUtil.BUILDER_INFRA.getPackageName())
				.addPackage(PackageUtil.CONVERSORES.getPackageName())
				.addPackage(PackageUtil.ENUMS.getPackageName())
				.addPackage(PackageUtil.EXCEPTION.getPackageName())
				.addPackage(PackageUtil.MODEL.getPackageName())
				.addPackage(PackageUtil.SERVICE.getPackageName())
				.addPackage(PackageUtil.UTIL.getPackageName())
				.addPackage(PackageUtil.FACEDE.getPackageName())
				.addPackage(PackageUtil.DATA.getPackageName())
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");

		System.out.println(war.toString(true));

		return war;
	}

	@EJB(beanName = "SenhaServiceImp", beanInterface = IServicoRemoteDAO.class)
	private IServicoRemoteDAO<SenhaDTO> senhaServiceImp;

	@Inject
	private Logger log;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * 
	 * @see a) processo define-se em criar sempre persisitir tres registros b)
	 *      buscar os dois registros e verificar se os dois novos ids c) alterar
	 *      os dois registros e verificar se esses registros foram d) excluir
	 *      todos regristros
	 */
	public void crud() {

		try {

			List<SenhaDTO> listaRemover = senhaServiceImp.bustarTodos();
			for (SenhaDTO item : listaRemover) {
				senhaServiceImp.remover(item);
			}

			SenhaDTO dto = SenhaBuilder.getInstanceDTO(TipoSenhaBuilder.SENHA_ACESSO);
			SenhaDTO dto2 = SenhaBuilder.getInstanceDTO(TipoSenhaBuilder.SENHA_ACESSO);

			dto = senhaServiceImp.adiconar(dto);

			SenhaDTO resutaldoBusca = senhaServiceImp.bustarPorID(dto.getId());
			Assert.assertNotNull(resutaldoBusca);
			Assert.assertEquals(dto.getId().longValue(), resutaldoBusca.getId().longValue());

			dto2 = senhaServiceImp.adiconar(dto2);
			SenhaDTO resutaldoBusca2 = senhaServiceImp.bustarPorID(dto2.getId());
			Assert.assertNotNull(resutaldoBusca2);

			Assert.assertEquals(dto2.getId().longValue(), resutaldoBusca2.getId().longValue());

			List<SenhaDTO> todos = senhaServiceImp.bustarTodos();
			Assert.assertNotNull(todos);
			Assert.assertTrue(todos.size() == 2);

			int range[] = { 0, 2 };
			List<SenhaDTO> todosIntervalo = senhaServiceImp.bustarPorIntervaloID(range);
			Assert.assertNotNull(todosIntervalo);
			Assert.assertTrue(todosIntervalo.size() == 2);

			resutaldoBusca2.setStatusSenha(StatusSenhaDTO.DESABILITADO);

			SenhaDTO resutaldoBusca3 = senhaServiceImp.alterar(resutaldoBusca2);
			Assert.assertEquals(resutaldoBusca2.getStatusSenha(), resutaldoBusca3.getStatusSenha());

			senhaServiceImp.remover(resutaldoBusca3);

			SenhaDTO dto4 = senhaServiceImp.bustarPorID(resutaldoBusca3.getId());
			Assert.assertNull(dto4);

		} catch (InfraEstruturaException e) {
			e.printStackTrace();
		} catch (NegocioException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void inserir() throws InfraEstruturaException, NegocioException {
		
		List<SenhaDTO> listaRemover = senhaServiceImp.bustarTodos();
		for (SenhaDTO item : listaRemover) {
			senhaServiceImp.remover(item);
		}
		System.out.println("####CLEAN#####");
		SenhaDTO senhaDTO = SenhaBuilder.getInstanceDTO(TipoSenhaBuilder.SENHA_ACESSO);
		senhaDTO = this.senhaServiceImp.adiconar(senhaDTO);

	}

	public void inserirRegistrosIguais() throws InfraEstruturaException, NegocioException {
		SenhaDTO senhaDTO = SenhaBuilder.getInstanceDTO(TipoSenhaBuilder.SENHA_ACESSO);
		senhaDTO = this.senhaServiceImp.adiconar(senhaDTO);
		thrown.expect(InfraEstruturaException.class);
		this.senhaServiceImp.adiconar(senhaDTO);

	}

	@Test
	public void buscarIdNaoExistente() {

		try {
			SenhaDTO dto = this.senhaServiceImp.bustarPorID(100L);
			Assert.assertNull(dto);

		} catch (InfraEstruturaException e) {
			e.printStackTrace();
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
