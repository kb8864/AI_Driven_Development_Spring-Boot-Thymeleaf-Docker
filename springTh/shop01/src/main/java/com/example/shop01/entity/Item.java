package com.example.shop01.entity;

/**
 * item テーブルの1行を表すクラス。
 * BeanPropertyRowMapper でマッピングするため、引数なしコンストラクタと
 * 全項目の setter を持つ。ORM のアノテーションは付けない。
 */
public class Item {

	/** 商品ID */
	private String id;

	/** 商品名 */
	private String name;

	/** 値段（円） */
	private Integer price;

	/** 在庫数 */
	private Integer stock;

	/** BeanPropertyRowMapper が使用する引数なしコンストラクタ */
	public Item() {
	}

	public Item(String id, String name, Integer price, Integer stock) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
