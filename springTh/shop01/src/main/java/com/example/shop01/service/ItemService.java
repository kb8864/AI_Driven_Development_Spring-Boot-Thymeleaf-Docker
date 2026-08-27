package com.example.shop01.service;

import java.util.List;

import com.example.shop01.entity.Item;
import com.example.shop01.form.ItemForm;

/**
 * 商品情報の業務ロジックを定義するインターフェース。
 * 画面から渡る ItemForm を受け取り、Entity への変換もこの層で行う。
 */
public interface ItemService {

	/**
	 * 一覧表示用に全商品を取得する。
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
	 * 商品IDが既に登録されているか判定する（登録時の重複チェック用）。
	 *
	 * @param id 商品ID
	 * @return 登録済みなら true
	 */
	boolean existsById(String id);

	/**
	 * 商品を新規登録する。
	 *
	 * @param form 入力値
	 * @return 登録できたら true
	 */
	boolean register(ItemForm form);

	/**
	 * 商品を更新する。
	 *
	 * @param form 入力値
	 * @return 更新できたら true。対象が存在しない場合は false
	 */
	boolean update(ItemForm form);

	/**
	 * 商品を削除する。
	 *
	 * @param id 商品ID
	 * @return 削除できたら true。対象が存在しない場合は false
	 */
	boolean delete(String id);
}
