package com.example.shop01.dao;

import java.util.List;

import com.example.shop01.entity.Item;

/**
 * 商品情報の DB アクセスを定義するインターフェース。
 * SQL の実行のみを担当し、業務判断は持たない。
 */
public interface ItemDao {

	/**
	 * 全商品を商品IDの昇順で取得する。
	 *
	 * @return 商品のリスト（0件の場合は空のリスト）
	 */
	List<Item> findAll();

	/**
	 * 商品IDで1件取得する。
	 *
	 * @param id 商品ID
	 * @return 該当する商品。存在しない場合は null
	 */
	Item findById(String id);

	/**
	 * 商品を1件登録する。
	 *
	 * @param item 登録する商品
	 * @return 登録件数
	 */
	int insert(Item item);

	/**
	 * 商品を1件更新する。商品IDは更新対象に含めない。
	 *
	 * @param item 更新する商品
	 * @return 更新件数（対象が存在しない場合は0）
	 */
	int update(Item item);

	/**
	 * 商品を1件削除する。
	 *
	 * @param id 商品ID
	 * @return 削除件数（対象が存在しない場合は0）
	 */
	int delete(String id);

	/**
	 * 指定した商品IDの件数を取得する（重複チェック用）。
	 *
	 * @param id 商品ID
	 * @return 件数
	 */
	int countById(String id);
}
