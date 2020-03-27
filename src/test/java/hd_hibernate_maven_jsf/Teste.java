package hd_hibernate_maven_jsf;

import javax.swing.JOptionPane;

import org.junit.Test;

import com.sun.faces.facelets.tag.jsf.EditableValueHolderRule;

import hd.dao.DaoGeneric;
import hd.model.Cliente;
import hd.model.Endereco;
import hd.util.HibernateUtil;

public class Teste {

	@Test
	public void testeHibernateUtil() {
		// testa a coneção do banco
		// HibernateUtil.getEntityManager();

		DaoGeneric<Cliente> daoGeneric = new DaoGeneric<Cliente>();// Intância o Dao generico
		Cliente cliente = new Cliente();// Criar o objeto para ser salvo
		// Seta todas as propriedades do objeto
		cliente.setNome("joão 27-03");
		cliente.setTelefone("30038777");
		daoGeneric.salvar(cliente);

	}

	@Test
	public void testeEndereco() {
		DaoGeneric daoGeneric = new DaoGeneric();

		Cliente cliente = (Cliente) daoGeneric.pesquisar(3L, Endereco.class);
		Endereco endereco = new Endereco();
		endereco.setBairro("carlito");
		endereco.setRua("rua teste");
		endereco.setNumeroCasa("3004");
		endereco.setCliente(cliente);

		daoGeneric.salvar(endereco);

	}

	@Test
	public void testBnco() {

		try {

			HibernateUtil.getEntityManager();

		} catch (Exception e) {

			e.printStackTrace();
			// TODO: handle exception
		}

	}

	@Test
	public void testeUpdate() {
		String id = JOptionPane.showInputDialog("digite um ID");

		DaoGeneric<Cliente> daoGeneric = new DaoGeneric<Cliente>();
		Cliente cliente = daoGeneric.pesquisar(Long.parseLong(id), Cliente.class);// Carrega o objeto para Editar

		if (cliente != null) {//verifica se o objeto pesquisado no banco é nullo

			cliente.setNome("Paulo");
			cliente.setTelefone("(85 9 8560-0987)");
			cliente = daoGeneric.updateMerge(cliente);// Atualiza no banco de dados
			System.out.println("O cliente do ID " + cliente.getId() + " Do nome " + cliente.getNome() + "Foi aterado.");

		} else {

			System.out.println("Clienete não encontrado");
		}
	}
}
