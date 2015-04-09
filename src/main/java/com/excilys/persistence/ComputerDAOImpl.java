package com.excilys.persistence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.mapper.ComputerMapper;
import com.excilys.model.ComputerModel;

@Repository
public class ComputerDAOImpl implements ComputerDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOImpl.class);

	@Override
	public List<ComputerModel> listAll() {
		LOGGER.info("computerDAO listAll");
		return jdbcTemplate.query("SELECT * FROM computer as computer left outer join company as company on company.id=computer.company_id", new ComputerMapper());
	}

	@Override
	public ComputerModel getById(long id) {
		String query = "SELECT * FROM computer as computer left outer join company as company on company.id=computer.company_id where computer.id=?";

		LOGGER.info("computerDAO getById");
		return jdbcTemplate.queryForObject(query, new Object[] {id}, new ComputerMapper());
	}

	@Override
	public long create(String name, LocalDateTime introduced, LocalDateTime discontinued, long idCompany) {
		String query = "insert into computer (name,introduced,discontinued,company_id) values (?, ?, ?, ?);";
		List<Object> list = new ArrayList<Object>();
		list.add(name);
		if (introduced != null) {
			list.add(introduced.toString());
		} else {
			list.add(null);
		}
		if (discontinued != null) {
			list.add(discontinued.toString());
		} else {
			list.add(null);
		}
		list.add(idCompany);

		jdbcTemplate.update(query, list.toArray());
		LOGGER.info("computerDAO create succeed");
		return 1;
	}

	@Override
	public void update(ComputerModel computer) {
		String query = "SELECT * FROM computer as computer left outer join company as company on company.id=computer.company_id where computer.id=" + computer.getId();

		ComputerModel comp = jdbcTemplate.queryForObject(query, new ComputerMapper());

		String actualName = comp.getName();
		LocalDateTime actualIntroduced = ((comp.getIntroduced() == null) ? null : comp.getIntroduced());
		LocalDateTime actualDiscontinued = ((comp.getDiscontinued() == null) ? null : comp.getDiscontinued());
		long actualCompany = comp.getCompany().getId();

		query = "UPDATE computer " +
				"SET name = '" + (((computer.getName() == null) || (computer.getName().equals(""))) ? actualName : computer.getName()) +
				"', introduced = '" + ((computer.getIntroduced() == null) ? actualIntroduced : computer.getIntroduced()) + 
				"', discontinued = '" + ((computer.getDiscontinued() == null) ? actualDiscontinued : computer.getDiscontinued()) +
				"', company_id = '" + ((computer.getCompany().getId() == -1) ? actualCompany : computer.getCompany().getId()) + "' " +
				"WHERE id = " + computer.getId() + ";";

		jdbcTemplate.update(query);
		LOGGER.info("computerDAO update succeed");
	}

	@Override
	public void delete(long id) {
		jdbcTemplate.update("DELETE FROM computer WHERE id = ?", new Object[] {id});
		LOGGER.info("computerDAO delete succeed");
	}

	public List<ComputerModel> getComputersByPage(int offset, int limit, String searchBy, String orderBy, String option) {
		if (offset < 0) {
			offset = 0;
		}
		List<Object> list = new ArrayList<Object>();
		String query = "SELECT * FROM computer as computer left outer join company as company on company.id=computer.company_id";
		if (!searchBy.isEmpty()) {
			query += " WHERE computer.name LIKE ? OR company.name LIKE ?";
			list.add("%" + searchBy + "%");
			list.add("%" + searchBy + "%");
		}
		if (!orderBy.isEmpty()) {
			query += " ORDER BY computer." + orderBy;
		}
		if (!option.isEmpty()) {
			query += " " + option;
		}
		query += " limit ? offset ?";

		list.add(limit);
		list.add(offset);

		LOGGER.info("computerDAO getComputersByPage succeed");
		return jdbcTemplate.query(query, list.toArray(), new ComputerMapper());
	}

	@Override
	public int totalRow(String searchBy) {
		String query = "SELECT count(*) FROM computer as computer left outer join company as company on company.id=computer.company_id";

		if (!searchBy.isEmpty()) {
			query += " WHERE computer.name LIKE '%" + searchBy + "%' OR company.name LIKE '%" + searchBy + "%'";
		}

		LOGGER.info("computerDAO totalRow succeed");
		return jdbcTemplate.queryForObject(query, Integer.class);
	}

}
