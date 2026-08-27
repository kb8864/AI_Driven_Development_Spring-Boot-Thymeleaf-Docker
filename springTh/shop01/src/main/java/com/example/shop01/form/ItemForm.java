package com.example.shop01.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 商品登録画面・商品更新画面で共用する入力値のクラス。
 * 更新時、商品IDは hidden で保持し画面上は変更不可とする。
 * 商品IDの重複チェックは DB の参照が必要なため、ここではなく Service で判定する。
 */
public class ItemForm {

	/** 商品ID */
	@NotBlank(message = "商品IDを入力してください")
	@Size(max = 20, message = "商品IDは20文字以内で入力してください")
	private String id;

	/** 商品名 */
	@NotBlank(message = "商品名を入力してください")
	@Size(max = 100, message = "商品名は100文字以内で入力してください")
	private String name;

	/** 値段（円） */
	@NotNull(message = "値段を入力してください")
	@Min(value = 0, message = "値段は0以上で入力してください")
	@Max(value = 99999999, message = "値段が大きすぎます")
	private Integer price;

	/** 在庫数 */
	@NotNull(message = "在庫を入力してください")
	@Min(value = 0, message = "在庫は0以上で入力してください")
	@Max(value = 99999999, message = "在庫が大きすぎます")
	private Integer stock;

	public ItemForm() {
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
