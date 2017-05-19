package com.taweesoft.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.taweesoft.mapper.PatternMapper;
import com.taweesoft.model.Pattern;

/**
 * Implementation of DAO (Pattern)
 * @author Taweerat Chaiman 5710546259
 *
 */
public class PatternDAOImpl implements PatternDAO{

	/*Data source and jdbc attributes*/
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	/**
	 * Set data source
	 * Called from beans.xml
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		System.out.println("Set Data Source");
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	/**
	 * Get data source 
	 * @return
	 */
	public DataSource getDataSource() {
		return this.dataSource;
	}
	
	/**
	 * Create new pattern.
	 */
	public void create(String name, String group, String implementation) {
		String SQL = "insert into patterns (`name`, `group`, `implementation`) values (?, ?, ?)";
		System.out.println("Created Record Name = " + name + " group = " + group + " implementation = " + implementation);
		jdbcTemplateObject.update(SQL, name, group, implementation);
		return;
	}

	/**
	 * Create new pattern (Overloading)
	 * @param pattern
	 */
	public void create(Pattern pattern) {
		create(pattern.getName(), pattern.getGroup(), pattern.getImplementation());
	}

	/**
	 * Get pattern from id.
	 */
	public Pattern getPattern(Integer id) {
		String SQL = "select * from patterns where id = ?";
		Pattern pattern = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new PatternMapper());
		return pattern;
	}
	
	/**
	 * Get all patterns.
	 */
	public List<Pattern> listPatterns() {
		String SQL = "select * from patterns";
		List<Pattern> patterns = jdbcTemplateObject.query(SQL, new PatternMapper());
		return patterns;
	}

	/**
	 * Delete pattern from id
	 */
	public void delete(Integer id) {
		String SQL = "delete from patterns where id = ?";
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Record with ID = " + id);
		return;
	}
	
	/**
	 * Update pattern information.
	 */
	public void update(Integer id, Pattern pattern) {
		String SQL = "update patterns set `name` = ?, `group` = ?, `implementation` = ? where id = ?";
		jdbcTemplateObject.update(SQL, pattern.getName(), pattern.getGroup(), pattern.getImplementation(), id);
		System.out.println("Updated Record with ID = " + id);
		return;
	}
}
