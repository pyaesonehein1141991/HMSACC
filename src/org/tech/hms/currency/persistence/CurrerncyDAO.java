package org.tech.hms.currency.persistence;

import org.springframework.stereotype.Repository;
import org.tech.hms.currency.persistence.interfaces.ICurrencyDAO;
import org.tech.java.component.persistence.BasicDAO;

@Repository("CurrerncyDAO")
public class CurrerncyDAO extends BasicDAO implements ICurrencyDAO{

	
}
