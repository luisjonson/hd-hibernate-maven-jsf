package hd.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import hd.dao.DaoGeneric;
import hd.model.Cliente;

@ManagedBean(name = "clienteManegedBean")
@ViewScoped
public class ClienteManegedBean {
	private Cliente cliente = new Cliente();
	private DaoGeneric<Cliente> daoGeneric = new DaoGeneric<Cliente>();
	private List<Cliente> list = new ArrayList<Cliente>();

	@PostConstruct
	public void init() {
		list = daoGeneric.listar(Cliente.class);
	}

	public String novo() {
		cliente = new Cliente();
		return "";
	}

	// salva o cliente e atualiza ao mesmo tempo
	public void salvar() {
		daoGeneric.salvar(cliente);// salva o cliente o banco.
		list.add(cliente);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "Salvo com sucesso. "));
	}

	public String remove() {
		try {
			daoGeneric.deletarPoId(cliente); // deleta o cliente do banco de dados
			list.remove(cliente);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "Delete com sucesso."));
			cliente = new Cliente();// limpa os dados da memoria para que não aconteça um acidente e dados volte a
									// ser inserido ao banco de dados..

		} catch (Exception e) {
			if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"informação", " Não existem endereços para cliente"));

			}
		}

		return "";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public DaoGeneric<Cliente> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<Cliente> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	// lista o endereco na table da view endereco.xhtml
	public List<Cliente> getList() {
		list = daoGeneric.listar(Cliente.class);
		return list;
	}
}
