'use strict';

/**
 * 削除フォームの送信前に確認ダイアログを表示する。
 * 対象は data-confirm-delete 属性を持つフォームのみ。
 */
document.addEventListener('DOMContentLoaded', function () {

	var forms = document.querySelectorAll('form[data-confirm-delete]');

	forms.forEach(function (form) {
		form.addEventListener('submit', function (event) {
			if (!window.confirm('この商品を削除します。よろしいですか？')) {
				// キャンセル時は送信を中止する
				event.preventDefault();
			}
		});
	});
});
