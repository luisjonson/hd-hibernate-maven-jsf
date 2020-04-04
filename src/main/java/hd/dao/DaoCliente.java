package hd.dao;

import hd.model.Cliente;

public class DaoCliente extends DaoGeneric<Cliente> {

	public void removerCliente(Cliente cliente) throws Exception {
		String sqlDelEndereco = "delete from cliente where cliente = " + cliente.getId();
		getEntityManager().createNamedQuery(sqlDelEndereco).executeUpdate();
		getEntityManager().getTransaction().commit();
		super.deletarPoId(cliente);
	}
}
