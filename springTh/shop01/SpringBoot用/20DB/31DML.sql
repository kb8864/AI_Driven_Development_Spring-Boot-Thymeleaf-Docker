USE shop;

DELETE FROM item;

INSERT INTO item (id, name, price, stock) VALUES
	('I001', '牛カルビ弁当', 780, 15),
	('I002', '鮭弁当', 720, 12),
	('I003', '唐揚げ弁当', 680, 20),
	('I004', 'のり弁当', 500, 25),
	('I005', '幕の内弁当', 850, 10);