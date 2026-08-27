package com.example.shop01.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.shop01.dao.ItemDao;
import com.example.shop01.entity.Item;

/**
 * ItemDao の実装クラス。
 * SQLインジェクション防止のため、値は必ず名前付きパラメータでバインドする。
 */
@Repository
public class ItemDaoImpl implements ItemDao {

	/** 全件取得 */
	private static final String SQL_FIND_ALL = "SELECT id, name, price, stock FROM item ORDER BY id";

	/** 1件取得 */
	private static final String SQL_FIND_BY_ID = "SELECT id, name, price, stock FROM item WHERE id = :id";

	/** 1件登録 */
	private static final String SQL_INSERT = "INSERT INTO item (id, name, price, stock) VALUES (:id, :name, :price, :stock)";

	/** 1件更新（商品IDは更新対象に含めない） */
	private static final String SQL_UPDATE = "UPDATE item SET name = :name, price = :price, stock = :stock WHERE id = :id";

	/** 1件削除 */
	private static final String SQL_DELETE = "DELETE FROM item WHERE id = :id";

	/** 商品IDの件数取得 */
	private static final String SQL_COUNT_BY_ID = "SELECT COUNT(*) FROM item WHERE id = :id";

	private final NamedParameterJdbcTemplate jdbcTemplate;

	/** コンストラクタが1つのため、@Autowired なしで注入される */
	public ItemDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Item> findAll() {
		SqlParameterSource param = new MapSqlParameterSource();
		return jdbcTemplate.query(SQL_FIND_ALL, param, new BeanPropertyRowMapper<>(Item.class));
	}

	@Override
	public Item findById(String id) {
		SqlParameterSource param = new MapSqlParameterSource("id", id);
		// queryForObject は該当0件で例外を投げるため、query で受けて空判定する
		List<Item> list = jdbcTemplate.query(SQL_FIND_BY_ID, param, new BeanPropertyRowMapper<>(Item.class));
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public int insert(Item item) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		return jdbcTemplate.update(SQL_INSERT, param);
	}

	@Override
	public int update(Item item) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		return jdbcTemplate.update(SQL_UPDATE, param);
	}

	@Override
	public int delete(String id) {
		SqlParameterSource param = new MapSqlParameterSource("id", id);
		return jdbcTemplate.update(SQL_DELETE, param);
	}

	@Override
	public int countById(String id) {
		SqlParameterSource param = new MapSqlParameterSource("id", id);
		Integer count = jdbcTemplate.queryForObject(SQL_COUNT_BY_ID, param, Integer.class);
		return count == null ? 0 : count;
	}
}
