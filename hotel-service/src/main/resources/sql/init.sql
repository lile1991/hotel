# 初始化房间类型
INSERT INTO room_type (id, name, icon, state, charge, deposit, create_time, create_user_id, update_time, update_user_id)
VALUES (1, '标准单间', '', 'NORMAL', 78, 0, now(), 0, now(), 0);
INSERT INTO room_type (id, name, icon, state, charge, deposit, create_time, create_user_id, update_time, update_user_id)
VALUES (2, '豪华单间', '', 'NORMAL', 98, 0, now(), 0, now(), 0);
INSERT INTO room_type (id, name, icon, state, charge, deposit, create_time, create_user_id, update_time, update_user_id)
VALUES (3, '标准双间', '', 'NORMAL', 108, 0, now(), 0, now(), 0);
INSERT INTO room_type (id, name, icon, state, charge, deposit, create_time, create_user_id, update_time, update_user_id)
VALUES (4, '豪华双间', '', 'NORMAL', 128, 0, now(), 0, now(), 0);

# 初始化楼层
INSERT INTO room_floor (floor, alias) VALUES (1, '一楼');
INSERT INTO room_floor (floor, alias) VALUES (2, '二楼');
INSERT INTO room_floor (floor, alias) VALUES (3, '三楼');
INSERT INTO room_floor (floor, alias) VALUES (4, '四楼');
INSERT INTO room_floor (floor, alias) VALUES (5, '五楼');
INSERT INTO room_floor (floor, alias) VALUES (6, '六楼');
INSERT INTO room_floor (floor, alias) VALUES (7, '七楼');