package org.tech.hms.web.dialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
		root = new DefaultTreeNode("Root", null);
		selectedNodes = null;
		coaList = coaService.findAllCoa();
		createParentNode();
	}

	
	
	public void createParentNode() {
		coaList.forEach(child->{
			createParentChild(child);
		});
		List<ChartOfAccount> parentList = new ArrayList<>();
		parentList = coaList.stream().filter(c->c.getParent() == null).collect(Collectors.toList());
		
		TreeNode node = new DefaultTreeNode("ChartOfAccount", root);
		parentList.forEach(coa -> {
			TreeNode pNode = new DefaultTreeNode(coa, node);
			node.getChildren().add(pNode);
			if(coa.getSubList().isEmpty() || null != coa.getSubList()) {
				for(ChartOfAccount subCoa :coa.getSubList()) {
					createSubNode(subCoa,pNode);
				}
			}
		});
		
		
		
		
	}
	
	public void createParentChild(ChartOfAccount coa) {
		if(null != coa.getParent()) {
			coa.getParent().getSubList().add(coa);
			createParentChild(coa.getParent());
		}
		
	}
	
	
	public void createSubNode(ChartOfAccount coa,TreeNode rootNode) {
		TreeNode pNode = new DefaultTreeNode(coa, rootNode);
		rootNode.getChildren().add(pNode);
		if(coa.getSubList().isEmpty() || null != coa.getSubList()) {
			for(ChartOfAccount subCoa :coa.getSubList()) {
				createSubNode(subCoa,pNode);
			}
		}
			
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
