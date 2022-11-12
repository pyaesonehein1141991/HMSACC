package org.tech.hms.web.dialog;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.tech.java.web.common.BaseBean;

@Named(value = "ManageCoaDialogActionBean")
@Scope(value = "view")
public class ManageCoaDialogActionBean extends BaseBean implements Serializable{

	private static final long serialVersionUID = 1L;

}
