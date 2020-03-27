package hd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import hd.util.HibernateUtil;

public class DaoGeneric<E> {

	private EntityManager entityManager = HibernateUtil.getEntityManager();

	public void salvar(E entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entidade);
		transaction.commit();
	}

	public E updateMerge(E entidade) { // salva ou atualiza
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		E entidadeSalva = entityManager.merge(entidade);// salva, atualiza e retorna o objeto
		transaction.commit();

		return entidadeSalva;
	}

	public E pesquisar(E entidade) {

		Object id = HibernateUtil.getPrimaryKey(entidade);

		E e = (E) entityManager.find(entidade.getClass(), id);

		return e;

	}

	public E pesquisar(Long id, Class<E> entidade) {
		E e = (E) entityManager.find(entidade, id); //ID = a Pk e o Entidade  e a Class do Objeto 
		return e;

	}

	public void deletarPoId(E entidade) throws Exception {

		Object id = HibernateUtil.getPrimaryKey(entidade);//obtem o ID do Objeto PK

		EntityTransaction transaction = entityManager.getTransaction();//Objeto de transação
		transaction.begin();//começa uma transação no banco de dados

		entityManager
				.createNativeQuery(
						"delete from " + entidade.getClass().//Monta a Query para  delele
						getSimpleName().toLowerCase() + " where id =" + id)
				.executeUpdate(); // Executa o delete no banco de dados
		transaction.commit();// grava alteração no banco

	}

	public List<E> listar(Class<E> entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		List<E> lista = entityManager.
				createQuery("from " + entidade.getName())//Cria a query de consulta
				.getResultList();// Retorna a lista de objetos consultas
		transaction.commit();

		return lista;
	}

}
