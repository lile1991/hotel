# 初始化房间类型
INSERT INTO room_type (id, name, icon, state, charge, deposit, create_time, create_user_id, update_time, update_user_id)
VALUES (1, '单间', '', 'NORMAL', 10800, 0, now(), 1, now(), 1);
INSERT INTO room_type (id, name, icon, state, charge, deposit, create_time, create_user_id, update_time, update_user_id)
VALUES (2, '双间', '', 'NORMAL', 12800, 0, now(), 1, now(), 1);
INSERT INTO room_type (id, name, icon, state, charge, deposit, create_time, create_user_id, update_time, update_user_id)
VALUES (3, '套间', '', 'NORMAL', 18800, 0, now(), 1, now(), 1);

# 初始化楼层
INSERT INTO room_floor (floor, alias) VALUES (1, '一楼');
INSERT INTO room_floor (floor, alias) VALUES (2, '二楼');
INSERT INTO room_floor (floor, alias) VALUES (3, '三楼');
INSERT INTO room_floor (floor, alias) VALUES (4, '四楼');
INSERT INTO room_floor (floor, alias) VALUES (5, '五楼');
INSERT INTO room_floor (floor, alias) VALUES (6, '六楼');
INSERT INTO room_floor (floor, alias) VALUES (7, '七楼');

# 初始化系统管理员
insert into user (id, name, mobile, username, password, locked, create_time, create_user_id, update_time, update_user_id)
VALUES (1, '系统管理员', '18025480826', 'admin', 'd4c68548715b89ed719d4ae0e9e1ca09', 0, now(), 0, now(), 0);

-- 前台菜单
insert into resource (id, type, `group`, name, value) VALUES (1, 'Menu', 'Reception', '客房管理', '');