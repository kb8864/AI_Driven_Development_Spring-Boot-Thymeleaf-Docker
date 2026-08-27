package com.example.shop01.util;

/**
 * 画面名・リダイレクト先・メッセージ文言の定数を集約するクラス。
 * インスタンス化しない。
 */
public final class AppConst {

	// ===== 画面名（templates 配下のパスと一致させる） =====

	/** 商品一覧画面 */
	public static final String VIEW_ITEM_LIST = "item/list";

	/** 商品登録画面 */
	public static final String VIEW_ITEM_ADD = "item/add";

	/** 商品更新画面 */
	public static final String VIEW_ITEM_EDIT = "item/edit";

	// ===== リダイレクト先 =====

	/** 商品一覧画面へのリダイレクト */
	public static final String REDIRECT_ITEM_LIST = "redirect:/items";

	// ===== メッセージ =====

	/** 登録完了 */
	public static final String MSG_REGISTERED = "商品を登録しました。";

	/** 更新完了 */
	public static final String MSG_UPDATED = "商品を更新しました。";

	/** 削除完了 */
	public static final String MSG_DELETED = "商品を削除しました。";

	/** 商品IDの重複 */
	public static final String MSG_DUPLICATE_ID = "この商品IDは既に登録されています。";

	/** 対象が存在しない */
	public static final String MSG_NOT_FOUND = "対象の商品が見つかりませんでした。";

	// ===== 画面へ渡す属性名 =====

	/** 完了メッセージの属性名 */
	public static final String ATTR_SUCCESS = "successMessage";

	/** エラーメッセージの属性名 */
	public static final String ATTR_ERROR = "errorMessage";

	/** インスタンス化を禁止する */
	private AppConst() {
	}
}
