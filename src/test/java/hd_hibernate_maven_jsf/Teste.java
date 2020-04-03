package hd_hibernate_maven_jsf;

import java.util.List;

import javax.swing.JOptionPane;

import org.junit.Test;

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

		Cliente cliente = (Cliente) daoGeneric.pesquisar(3L, Cliente.class);
		Endereco endereco = new Endereco();
		endereco.setBairro("jõar");
		endereco.setRua("rua");
		endereco.setNumeroCasa("3004");
		endereco.setReferencia("");
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

		if (cliente != null) {// verifica se o objeto pesquisado no banco é nullo

			cliente.setNome(JOptionPane.showInputDialog("Digite o nome do cliente "));
			cliente.setTelefone(JOptionPane.showInputDialog("Digite o numero do telefone "));
			cliente = daoGeneric.updateMerge(cliente);// Atualiza no banco de dados
			System.out.println("O cliente do ID " + cliente.getId() + " Do nome " + cliente.getNome() + "Foi aterado.");

		} else {

			System.out.println("Clienete não encontrado");
		}
	}

	/*
	 * @Test public void testeNameQUery2() { DaoGeneric<Cliente> daoGeneric = new
	 * DaoGeneric<Cliente>(); List<Cliente> list =
	 * daoGeneric.getEntityMaager().createNameQuery("Cliente.buscaPorNome")
	 * .setParameter("nome", "Paulo").getResultList();
	 * 
	 * for(Cliente cliente : list) { System.out.println(cliente);
	 * 
	 * } }
	 */
}