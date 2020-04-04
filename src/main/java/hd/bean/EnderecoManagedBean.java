package hd.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import hd.dao.DaoGeneric;
import hd.model.Cliente;
import hd.model.Endereco;

@ManagedBean
public class EnderecoManagedBean {
	private Endereco endereco = new Endereco();
	private DaoGeneric<Endereco> daoGeneric = new DaoGeneric<Endereco>();
	private List<Endereco> list = new ArrayList<Endereco>();

	public void init() {
		String codcliente = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("codigouser");
		endereco = daoGeneric.pesquisar(Long.parseLong(codcliente), Cliente.class);
	}

	// zera o campos do endereco.
	public String novo() {
		endereco = new Endereco();
		return "";
	}

	public void salvar() {
		daoGeneric.salvar(endereco);
		list.add(endereco);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "Salvo com sucesso. "));
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public DaoGeneric<Endereco> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<Endereco> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	//lista o endereco na table da view endereco.xhtml
	public List<Endereco> getListeEnderecos() {
		list = daoGeneric.listar(Endereco.class);
		return list;
	}

	

}
