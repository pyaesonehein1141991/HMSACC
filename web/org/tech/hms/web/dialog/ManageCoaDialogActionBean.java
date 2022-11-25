package org.tech.hms.web.dialog;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.tech.hms.coa.ChartOfAccount;
import org.tech.hms.coa.service.interfaces.ICoaService;
import org.tech.hms.common.dto.coaDto.CoaDTO;
import org.tech.java.web.common.BaseBean;

import lombok.Getter;
import lombok.Setter;

@Named(value = "ManageCoaDialogActionBean")
@Scope(value = "view")
public class ManageCoaDialogActionBean extends BaseBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private final String BASEROOTNAME ="Chart Of Account";
	
	@Autowired
	private ICoaService coaService;
	
	@Getter
	private List<CoaDTO> dtoList;
	
	@Getter
	private TreeNode root;
	
	@Getter
	@Setter
	private TreeNode[] selectedNodes;
	
	@Getter
	@Setter
	private List<ChartOfAccount> coaList;
	

	@PostConstruct
	public void init() {
		coaList = coaService.findAllCoa();
	}

	

	
	public String navManageCoa() {
		return null;
	}
	
	public void selectCoa() {
		ChartOfAccount coa=null;
		selectedNodes = Stream.of(selectedNodes).filter(node -> !node.getData().equals("ChartOfAccount")).toArray(TreeNode[]::new);
		for (TreeNode node : selectedNodes) {
			coa=(ChartOfAccount) node.getData();
		}
		PrimeFaces.current().dialog().closeDynamic(coa);
	}
	
	
	public void loadData() {
		dtoList = coaService.findAllDTO();
	}
	
	public void createCoaTreeNode() {
		root = new DefaultTreeNode(BASEROOTNAME, null);
		
	}
	
	public void selectCoa(ChartOfAccount coa) {
		PrimeFaces.current().dialog().closeDynamic(coa);
	}

}
